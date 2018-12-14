//选择图片
function selectImg(file) {
    if (!file.files || !file.files[0]){
        return;
    }
    if (file.files[0].size > 1*1024*1024){
       layer.alert("上传的图片的大于1M,请重新选择");
       return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var replaceSrc = evt.target.result;
        //更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc,false);//默认false，适应高度，不失真
    }
    reader.readAsDataURL(file.files[0]);
}
$(function () {
    var _id = $("input[name=userId]").val();
    var _codeNumber;
//cropper图片裁剪
    $('#tailoringImg').cropper({
        aspectRatio: 1/1,//默认比例
        preview: '.previewImg',//预览视图
        guides: false,  //裁剪框的虚线(九宫格)
        autoCropArea: 0.5,  //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true,  //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true,  //是否允许移动剪裁框
        resizable: true,  //是否允许改变裁剪框的大小
        zoomable: false,  //是否允许缩放图片大小
        mouseWheelZoom: false,  //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true,  //是否允许通过触摸移动来缩放图片
        rotatable: true,  //是否允许旋转图片
        crop: function(e) {
            // 输出结果数据裁剪图像。
        }
    });
//旋转
    $(".cropper-rotate-btn").on("click",function () {
        $('#tailoringImg').cropper("rotate", 45);
    });
//复位
    $(".cropper-reset-btn").on("click",function () {
        $('#tailoringImg').cropper("reset");
    });
//换向
    var flagX = true;
    $(".cropper-scaleX-btn").on("click",function () {
        if(flagX){
            $('#tailoringImg').cropper("scaleX", -1);
            flagX = false;
        }else{
            $('#tailoringImg').cropper("scaleX", 1);
            flagX = true;
        }
        flagX != flagX;
    });

//裁剪后的处理
    $("#sureCut").on("click",function () {
        if ($("#tailoringImg").attr("src") == null ){
            return false;
        }else{
            var cas = $('#tailoringImg').cropper('getCroppedCanvas');//获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
            $("#finalImg").prop("src",base64url);//显示为图片的形式
            cas.toBlob(function (blob) {
               console.log(blob);
               var formdata = new FormData();
               formdata.append("editorImage",blob, "abc.png");
               $.ajax({
                   type: "POST",
                   url:"/merchant/merchantUser/editorImage/" + _id,
                   data:formdata,
                   processData: false,
                   contentType: false,
                   success: res => {
                      if (res.code === 200) {
                         location.reload();
                      }
                   }
               });
            });
            layer.closeAll();
        }
    });
    //从文件获取省份信息
    $.getJSON("/src/res/merchant/address.json",
        function (jasondata) {
            jasondata.forEach(element => {
                var province = element.name;
                var op = $('<option/>');
                op.html(province);
                $('#province').append(op);
            })
            ;
            layui.use('form', function () {
                var form = layui.form;
                form.on('select(provincepick)', function (data) {
                    $('#city>option').remove();
                    $('#city').append('<option value="">请选择城市</option>');
                    $('#area>option').remove();
                    $('#area').append('<option value="">请选择地区</option>');
                    jasondata.forEach(element => {
                        if (data.value === element.name) {
                            element.city.forEach(element => {
                                var city = element.name;
                                // console.log(city);
                                var oc = $('<option/>');
                                oc.html(city);
                                $('#city').append(oc);
                            })
                            ;
                        }
                    })
                    ;
                    form.render();
                });
                form.on('select(citypick)', function (data) {
                    $('#area>option').remove();
                    $('#area').append('<option value="">请选择地区</option>');
                    jasondata.forEach(element => {
                        element.city.forEach(element => {
                            if (data.value === element.name) {
                                element.area.forEach(element => {
                                    var area = element;
                                    var oa = $('<option/>');
                                    oa.html(area);
                                    $('#area').append(oa);
                                })
                                ;
                            }
                        })
                        ;
                    })
                    ;
                    form.render();
                });
            });
        }
    );
   layui.use(["element", "util", "layer", "form"], function () {
       var element = layui.element,
           util = layui.util,
           layer = layui.layer,
           form = layui.form;
       //导航条滚动
       $("#edit_cover").click(function (e) {
          e.preventDefault();
          layer.open({
              type: 1,
              area:['800px', '620px'],
              shadeClose:true,
              scrollbar: false,
              content: $("#layer_show")
          });
       });
       $(window).scroll(function () {
           var topp = 50 - $(document).scrollTop();
           $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

       });
       //文本框字数限制
       $("#signature").on("input propertychange", function () {
           var $this = $(this),
               _val = $this.val(),
               count = "";
           if (_val.length > 50) {
               $this.val(_val.substring(0, 50));
           }
           count = $this.val().length;
           $("#text-count").text(count);
       });
       $(document).on("mouseenter", "#downloadApp", function () {
           $(this).siblings("#showQRCode").css("opacity",1).addClass("layui-anim").addClass("layui-anim-up");
       }).on("mouseleave", "#downloadApp", function () {
           $(this).siblings("#showQRCode").css("opacity",0).removeClass("layui-anim").removeClass("layui-anim-up");
       });

       util.fixbar({
           bar1: true,
           click: function (type) {
               console.log(type);
               if (type === 'bar1') {
                  layer.msg("点击了bar1");
               }
           }
       });
       form.on("submit(userDetails)", function (data) {
          console.log(data.field);
          console.log(_id);
          $.ajax({
              url: "/merchant/merchantUser/editorUserDetails/" + _id,
              type: "POST",
              data: data.field,
              success: res => {
                 if (res.code === 200) {
                    layer.msg("修改信息成功！");
                    location.reload();
                 }
              }
          });
           return false;
       });
       var codeTime = 60;
       var interval_id;
       //设置发送验证码按钮倒计时时间
       function setCodeTime(){
           if (codeTime === 0) {
               clearInterval(interval_id);
               $('#sendCode').removeClass("layui-btn-disabled")
                   .addClass("layui-btn-primary").html("重新发送验证码");
               codeTime = 60;
               // return false;
           }else{
               codeTime--;
               $('#sendCode').html(codeTime + "秒后可重新获取");
           }
       }
       $("body").on("click", "#sendCode", function () {
           var number = $("input[name=fPhoneNumber]").val();
           if (number === "") {
               layer.alert("请先填写手机号");
           } else if (number !== $("input[name=fAccount]").val()) {
               layer.alert("请输入注册时手机号或更改后的手机号！");
           } else {
               $(this).removeClass("layui-btn-primary").addClass("layui-btn-disabled");
               interval_id = setInterval(setCodeTime, 1000);
               $.ajax({
                   url: "/merchantCommon/verifyCode",
                   type: "POST",
                   data: {
                       phoneNumber: number
                   },
                   success: res => {
                       _codeNumber = res.code;
                       console.log(_codeNumber)
                   },
                   error: err => {
                       layer.alert("验证码发送失败！");
                   }
               });
           }

       });
       form.on("submit(userPassword)", function (data) {
          if (_codeNumber === $("input[name=fVerifyCode]").val())  {
              if ($("input[name=oldPassword]").val() !== $("input[name=password]").val()) {
                  var formdata = new FormData();
                  $.ajax({
                      url: "/merchant/merchantUser/editorUserPassword/" + _id,
                      type: "POST",
                      data: {
                          fPassword: $("input[name=password]").val(),
                          oldPassword: $("input[name=oldPassword]").val()
                      },
                      success: res => {
                          if (res.code === 200) {
                              layer.msg("密码修改成功，请用新密码重新登陆！");
                              setTimeout(function () {
                                  location.href="/merchantCommon/login";
                              },500)
                          } else if (res.code === 408){
                              layer.alert("原密码不正确！");
                          } else {
                              layer.alert("系统出错");
                          }
                      }
                  });
              } else {
                  layer.msg("原密码和新密码不得一致！");
              }
          } else {
              layer.alert("验证码不一致，请重新发送验证码！")
          }
           return false;
       });



       form.verify({
           account: function (value, item) {
               if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value)) {
                   return '请输入正确的手机号';
               }
           }
           , password: function (value, item) {
               //密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
               //123456Y&y123
               if (!/^(?![A-Za-z]+$)(?![A-Z\d]+$)(?![A-Z\W]+$)(?![a-z\d]+$)(?![a-z\\W]+$)(?![\d\W]+$)\S{6,12}$/.test(value)) {
                   return '密码6-12位，数字,大写字母,小写字母,特殊符,至少其中三种组成密码';
               }
           }
           , repassword: function (value, item) {
               if ($('#repassword').val() != $('#password').val()) {
                   return '密码不匹配';
               }
           }
       });

   });
});