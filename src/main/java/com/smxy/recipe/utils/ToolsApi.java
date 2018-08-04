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
 *
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class ToolsApi {
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
	/**
	 * @author zpx
	 * @param page
	 * @param pageTotal
	 * @return
	 */
	public static int[] getPagesArr(int page, int pageTotal) {
		int pageLength = 9;
		int pagesArr[] = { page };
		int left = page - 1;
		int right = page + 1;
		while (page <= pageTotal && pagesArr.length < pageLength && (left > 0 || right <= pageTotal)) {
			if (left > 0) {
				pagesArr = Arrays.copyOf(pagesArr, pagesArr.length + 1);
				for (int i = pagesArr.length - 1; i > 0; i--) {
					pagesArr[i] = pagesArr[i - 1];
				}
				pagesArr[0] = left;
			}
			if (right <= pageTotal) {
				pagesArr = Arrays.copyOf(pagesArr, pagesArr.length + 1);
				pagesArr[pagesArr.length - 1] = right;
			}
			left--;
			right++;
		}
		return pagesArr;
	}
	public static boolean FileUpload(MultipartFile multipartFile,boolean fileLimit,String serverPathMkdir,String fileName,int isDelete,String preFile) {
		if (isDelete>0) {
			File file=new File(serverPathMkdir+getName(preFile));
			if (file.exists()&&file.isFile()) {
				file.delete();
			}
		}
		if (fileLimit) {
			try {
				multipartFile.transferTo(new File(serverPathMkdir+fileName));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	public static String reName(String file) {
		int index = file.lastIndexOf(".");
		String name = new Date().getTime() + "" + new Random().nextInt(199)
				+ file.substring(index);
		return name;
	}
	public static String suffixName(String file) {
		int index = file.lastIndexOf(".");
		return file.substring(index + 1);
	}
	public static boolean imgLimit(String suffix) {
		if(suffix.equalsIgnoreCase("gif") || suffix.equalsIgnoreCase("jpeg")
				|| suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
				|| suffix.equalsIgnoreCase("svg")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean excelLimit(String suffix) {
		if(suffix.equalsIgnoreCase("xls")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean fileLimit(String suffix) {
		if(!suffix.equalsIgnoreCase("php")) {
			return true;
		}else {
			return false;
		}
	}
	public static String getName(String name) {
		int index=name.lastIndexOf("/");
		return name.substring(index+1);
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
	public static void reImgSize(String path,String suffix,HttpServletRequest request) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
	        //字节流转图片对象
	        Image bi =ImageIO.read(in);
	        //获取图像的高度，宽度
//	        int height=bi.getHeight(null);
//	        int width =bi.getWidth(null);
	        //构建图片流
	        BufferedImage tag = new BufferedImage(548, 332, BufferedImage.TYPE_INT_RGB);
	        //绘制改变尺寸后的图
	        tag.getGraphics().drawImage(bi, 0, 0,548, 332, null);  
	        //输出流
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
	        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        //encoder.encode(tag);
	        ImageIO.write(tag, suffix.toUpperCase(),out);
	        in.close();
	        out.close();
			//转字节流
			//ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			//ImageIO.write(tag, "PNG",out);
			//InputStream is = new ByteArrayInputStream(out.toByteArray());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
