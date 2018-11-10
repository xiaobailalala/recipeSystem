/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package: com.smxy.recipe.utils
 * @author: 雏实。
 * Build File @date: 2018年7月4日 下午1:39:29
 */
package com.smxy.recipe.utils;

/**
 * @author zpx
 */

import com.smxy.recipe.dao.SysResourceDao;
import com.smxy.recipe.entity.SysResource;
import com.smxy.recipe.service.SysResourceService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Component
@PropertySource("classpath:custom.properties")
public class ToolsApi {

    private static final Logger logger = LoggerFactory.getLogger(ToolsApi.class);

    public static final String MAILTYPE_VERIFY = "verify";
    public static final String MAILTYPE_RESET = "reset";

    private final static Base64.Decoder decoder = Base64.getDecoder();
    private final static Base64.Encoder encoder = Base64.getEncoder();

    private static JavaMailSenderImpl mailSender;

    @Autowired(required = true)
    public void setMailSender(JavaMailSenderImpl mailSender) {
        ToolsApi.mailSender = mailSender;
    }

    private static SysResourceService sysResourceService;

    @Autowired(required = true)
    public void setSysResourceService(SysResourceService sysResourceService) {
        ToolsApi.sysResourceService = sysResourceService;
    }

    private static String senderAddress;

    @Value("${userinfo.email.address}")
    public void setSenderAddress(String senderAddress) {
        ToolsApi.senderAddress = senderAddress;
    }

    public static String base64Encode(String str) {
        String charset = "UTF-8";
        String result = "";
        try {
            final byte[] bytes = str.getBytes(charset);
            result = encoder.encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String base64Decode(String str) {
        String charset = "UTF-8";
        String result;
        try {
            result = new String(decoder.decode(str.replace("\r\n", "")), charset);
        } catch (Exception e) {
            result = str;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(ToolsApi.base64Encode("测试评论"));
    }

    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (max < min || n > len) {
            return null;
        }
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            index = Math.abs(rd.nextInt() % len--);
            result[i] = source[index];
            source[index] = source[len];
        }
        return result;
    }

    public static String multipartFileUploadFile(MultipartFile file, NameValuePair[] pairs) {
        try {
            InputStream inputStream = file.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            byte[] bs = new byte[1024];
            while ((len = inputStream.read(bs)) != -1) {
                bos.write(bs);
            }
            inputStream.close();
            String result = FastDfsClient.uploadBinaryFile(bos.toByteArray(), ToolsApi.suffixName(file.getOriginalFilename()).toLowerCase(), pairs);
            sysResourceService.saveInfo(new SysResource(0, result, ToolsApi.suffixName(file.getOriginalFilename()).toLowerCase()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }

    public static String binaryFileUploadFile(byte[] file, String suffixName) {
        String result = "failed";
        try {
            result = FastDfsClient.uploadBinaryFile(file, suffixName.toLowerCase(), null);
            sysResourceService.saveInfo(new SysResource(0, result, suffixName.toLowerCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * FastDFS 成功返回值为1，失败不返回，故这里返回值为2以示失败
     */
    public static void multipartFileDeleteFile(String fileName) {
        try {
            sysResourceService.deleteInfo(fileName);
            FastDfsClient.deleteFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUndertint() {
        Random random = new Random();
        String r1 = Integer.toHexString(random.nextInt(128) + 128).toUpperCase();
        String g1 = Integer.toHexString(random.nextInt(128) + 128).toUpperCase();
        String b1 = Integer.toHexString(random.nextInt(128) + 128).toUpperCase();
        r1 = r1.length() == 1 ? "0" + r1 : r1;
        g1 = g1.length() == 1 ? "0" + g1 : g1;
        b1 = b1.length() == 1 ? "0" + b1 : b1;
        return "#" + r1 + g1 + b1;
    }

    public static String getDertint() {
        Random random = new Random();
        String r2 = Integer.toHexString(random.nextInt(128)).toUpperCase();
        String g2 = Integer.toHexString(random.nextInt(128)).toUpperCase();
        String b2 = Integer.toHexString(random.nextInt(128)).toUpperCase();
        r2 = r2.length() == 1 ? "0" + r2 : r2;
        g2 = g2.length() == 1 ? "0" + g2 : g2;
        b2 = b2.length() == 1 ? "0" + b2 : b2;
        return "#" + r2 + g2 + b2;
    }

    @Async
    public void sendMail(String type, String title, String reciver, String account, String code) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject(title);
            if (MAILTYPE_VERIFY.equals(type)) {
                helper.setText("<!DOCTYPE html><html><head>    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />    <title></title>    <meta charset=\"utf-8\" /></head><body>    <div class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=\"mailContentContainer\" style=\"\">        <style type=\"text/css\">            .qmbox body {                margin: 0;                padding: 0;                background: #fff;                font-family: \"Verdana, Arial, Helvetica, sans-serif\";                font-size: 14px;                line-height: 24px;            }            .qmbox div, .qmbox p, .qmbox span, .qmbox img {                margin: 0;                padding: 0;            }            .qmbox img {                border: none;            }            .qmbox .contaner {                margin: 0 auto;            }            .qmbox .title {                margin: 0 auto;                background: url() #CCC repeat-x;                height: 30px;                text-align: center;                font-weight: bold;                padding-top: 12px;                font-size: 16px;            }            .qmbox .content {                margin: 4px;            }            .qmbox .biaoti {                padding: 6px;                color: #000;            }            .qmbox .xtop, .qmbox .xbottom {                display: block;                font-size: 1px;            }            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {                display: block;                overflow: hidden;            }            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3 {                height: 1px;            }            .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {                border-left: 1px solid #BCBCBC;                border-right: 1px solid #BCBCBC;            }            .qmbox .xb1 {                margin: 0 5px;                background: #BCBCBC;            }            .qmbox .xb2 {                margin: 0 3px;                border-width: 0 2px;            }            .qmbox .xb3 {                margin: 0 2px;            }            .qmbox .xb4 {                height: 2px;                margin: 0 1px;            }            .qmbox .xboxcontent {                display: block;                border: 0 solid #BCBCBC;                border-width: 0 1px;            }            .qmbox .line {                margin-top: 6px;                border-top: 1px dashed #B9B9B9;                padding: 4px;            }            .qmbox .neirong {                padding: 6px;                color: #666666;            }            .qmbox .foot {                padding: 6px;                color: #777;            }            .qmbox .font_darkblue {                color: #006699;                font-weight: bold;            }            .qmbox .font_lightblue {                color: #008BD1;                font-weight: bold;            }            .qmbox .font_gray {                color: #888;                font-size: 12px;            }        </style>        <div class=\"contaner\">            <div class=\"title\">" + title + "</div>            <div class=\"content\">                <p class=\"biaoti\"><b>亲爱的用户，你好！</b></p>                <b class=\"xtop\"><b class=\"xb1\"></b><b class=\"xb2\"></b><b class=\"xb3\"></b><b class=\"xb4\"></b></b>                <div class=\"xboxcontent\">                    <div class=\"neirong\">                        <p><b>请核对你的账号：</b><span id=\"userName\" class=\"font_darkblue\">" + account + "</span></p>                        <p><b>邮箱绑定的验证码：</b><span class=\"font_lightblue\"><span id=\"yzm\" data=\"" + code + "\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">" + code + "</span></span><br><span class=\"font_gray\">(请输入该验证码完成邮箱绑定，验证码30分钟内有效！)</span></p>                        <div class=\"line\">如果你未申请邮箱绑定服务，请忽略该邮件。</div>                    </div>                </div>                <b class=\"xbottom\"><b class=\"xb4\"></b><b class=\"xb3\"></b><b class=\"xb2\"></b><b class=\"xb1\"></b></b>                <p class=\"foot\">如果仍有问题，请拨打我们的会员服务专线: <span data=\"800-820-5100\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">15080558157</span></p>            </div>        </div>        <style type=\"text/css\">            .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {                display: none !important;            }        </style>    </div></body></html>", true);
            }
            if (MAILTYPE_RESET.equals(type)) {
                helper.setText("<!DOCTYPE html><html><head>    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />    <title></title>    <meta charset=\"utf-8\" /></head><body>    <div class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=\"mailContentContainer\" style=\"\">        <style type=\"text/css\">            .qmbox body {                margin: 0;                padding: 0;                background: #fff;                font-family: \"Verdana, Arial, Helvetica, sans-serif\";                font-size: 14px;                line-height: 24px;            }            .qmbox div, .qmbox p, .qmbox span, .qmbox img {                margin: 0;                padding: 0;            }            .qmbox img {                border: none;            }            .qmbox .contaner {                margin: 0 auto;            }            .qmbox .title {                margin: 0 auto;                background: url() #CCC repeat-x;                height: 30px;                text-align: center;                font-weight: bold;                padding-top: 12px;                font-size: 16px;            }            .qmbox .content {                margin: 4px;            }            .qmbox .biaoti {                padding: 6px;                color: #000;            }            .qmbox .xtop, .qmbox .xbottom {                display: block;                font-size: 1px;            }            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {                display: block;                overflow: hidden;            }            .qmbox .xb1, .qmbox .xb2, .qmbox .xb3 {                height: 1px;            }            .qmbox .xb2, .qmbox .xb3, .qmbox .xb4 {                border-left: 1px solid #BCBCBC;                border-right: 1px solid #BCBCBC;            }            .qmbox .xb1 {                margin: 0 5px;                background: #BCBCBC;            }            .qmbox .xb2 {                margin: 0 3px;                border-width: 0 2px;            }            .qmbox .xb3 {                margin: 0 2px;            }            .qmbox .xb4 {                height: 2px;                margin: 0 1px;            }            .qmbox .xboxcontent {                display: block;                border: 0 solid #BCBCBC;                border-width: 0 1px;            }            .qmbox .line {                margin-top: 6px;                border-top: 1px dashed #B9B9B9;                padding: 4px;            }            .qmbox .neirong {                padding: 6px;                color: #666666;            }            .qmbox .foot {                padding: 6px;                color: #777;            }            .qmbox .font_darkblue {                color: #006699;                font-weight: bold;            }            .qmbox .font_lightblue {                color: #008BD1;                font-weight: bold;            }            .qmbox .font_gray {                color: #888;                font-size: 12px;            }        </style>        <div class=\"contaner\">            <div class=\"title\">" + title + "</div>            <div class=\"content\">                <p class=\"biaoti\"><b>亲爱的用户，你好！</b></p>                <b class=\"xtop\"><b class=\"xb1\"></b><b class=\"xb2\"></b><b class=\"xb3\"></b><b class=\"xb4\"></b></b>                <div class=\"xboxcontent\">                    <div class=\"neirong\">                        <p><b>请核对你的账号：</b><span id=\"userName\" class=\"font_darkblue\">" + account + "</span></p>                        <p><b>您的账号经由管理处处理，密码已重置成功，重置后的密码为：</b><span class=\"font_lightblue\"><span id=\"yzm\" data=\"" + code + "\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">" + code + "</span></span><br><span class=\"font_gray\">(该密码为临时密码，为了您的账号安全，请登录后及时进行密码修改。)</span></p>                        <div class=\"line\">如果你进行密码重置服务，请联系管理员。</div>                    </div>                </div>                <b class=\"xbottom\"><b class=\"xb4\"></b><b class=\"xb3\"></b><b class=\"xb2\"></b><b class=\"xb1\"></b></b>                <p class=\"foot\">如果仍有问题，请拨打我们的会员服务专线: <span data=\"800-820-5100\" onclick=\"return false;\" t=\"7\" style=\"border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;\">15080558157</span></p>            </div>        </div>        <style type=\"text/css\">            .qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {                display: none !important;            }        </style>    </div></body></html>", true);
            }
            helper.setTo(reciver);
            helper.setFrom("1051973784@qq.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
            if ((cookieName).equalsIgnoreCase(cookies[i].getName())) {
                Cookie cookie = new Cookie(cookieName, "");
                cookie.setPath("/");
                cookie.setMaxAge(0);
            }
        }
    }

    public static String randomPwd() {
        Object[] ch = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        };
        String resCode = "";
        int top = 16;
        for (int i = 0; i < top; i++) {
            resCode += ch[(int) Math.floor(Math.random() * 16)];
        }
        return resCode;
    }

    public static String entryptBySaltMd5(String password, String salt) {
        Object md5Password = new SimpleHash("MD5", password, ByteSource.Util.bytes(salt), 1024);
        return md5Password.toString();
    }

    public static String toMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean fileUpload(MultipartFile multipartFile, boolean fileLimit, String serverPathMkdir, String fileName, int isDelete, String preFile) {
        if (isDelete > 0) {
            File file = new File(serverPathMkdir + getName(preFile));
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
        if (fileLimit) {
            try {
                multipartFile.transferTo(new File(serverPathMkdir + fileName));
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public static String reName(String file) {
        int index = file.lastIndexOf(".");
        String name = System.currentTimeMillis() + "" + new Random().nextInt(1001)
                + file.substring(index);
        return name;
    }

    public static String suffixName(String file) {
        int index = file.lastIndexOf(".");
        return file.substring(index + 1);
    }

    public static boolean imgLimit(String suffix) {
        String[] limitFormat = {"gif", "jpeg", "jpg", "png", "svg"};
        for (String s : limitFormat) {
            if (s.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean excelLimit(String suffix) {
        String[] limitFormat = {"xls", "xlsx"};
        for (String s : limitFormat) {
            if (s.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean fileLimit(String suffix) {
        String forbid = "php";
        if (!forbid.equalsIgnoreCase(suffix)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getName(String name) {
        int index = name.lastIndexOf("/");
        return name.substring(index + 1);
    }

    public static String getTimeNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }

    public static String getDateToDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        return dateFormat.format(now);
    }

    public static void reImgSize(String path, String suffix, HttpServletRequest request) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
            Image bi = ImageIO.read(in);
            BufferedImage tag = new BufferedImage(548, 332, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, 548, 332, null);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
            ImageIO.write(tag, suffix.toUpperCase(), out);
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }
}
