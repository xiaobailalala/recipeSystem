$(function(){
    (function editorInfo(){
        var username=true,isSelectImg=false,isImg=false;
        $("body").on("input propertychange","#fUsername",function(){
            if ($(this).val().length===0||$(this).val().length>16||$(this).val().length<3){
                $('#usernameBtn').attr("disabled","disabled");
                username=false;
            }else{
                $('#usernameBtn').removeAttr("disabled");
                username=true;
            }
        });
        $("#usernameForm").submit(function(e){
            e.preventDefault();
            var formData=new FormData(this);
            if (isSelectImg){
                if (isImg&&username){
                    var file = dataURLtoBlob($('#previewContainer').attr("src"));
                    var newImg = new Date().getTime() + Math.floor(Math.random() * 1000) + '.png';
                    formData.append('file', file, newImg);
                    subAjax(formData);
                } else{
                    Tools.tip("请完善信息，再进行操作。");
                }
            } else{
                if (username){
                    subAjax(formData);
                } else{
                    Tools.tip("请完善信息，再进行操作。");
                }
            }
        });
        function subAjax(formdata){
            $.ajax({
                url:"/adm/editor/name/"+formdata.get("fId"),
                type:"post",
                data:formdata,
                processData:false,
                contentType:false,
                success:function(res){
                    if (res.code===200){
                        swal({
                            title:"用户名修改成功",
                            text:"点击“确定”返回首页",
                            type:"success",
                            confirmButtonColor:"#5cb85c",
                            confirmButtonText:"返回首页",
                            closeOnConfirm:false
                        },function(){
                            location.href="/adm/admin/index";
                        });
                    }else{
                        Tools.tip(res.msg);
                    }
                }
            });
        }
        $('#changeImgPre').change(function(){
            if ($(this).is(':checked')) {
                $('#changeImgContainer').collapse("show");
                isSelectImg=true;
            }else{
                $('#changeImgContainer').collapse("hide");
                isSelectImg=false;
            }
        });
        $('#selectFileBtn').click(function(){
            $('#imgFile').click();
        });
        var image = document.getElementById('imageContainer');
        var cropper, canvas,isFirst=true;
        $('#imgFile').change(function(e){
            var file;
            var files = e.target.files;
            if (files && files.length > 0) {
                file = URL.createObjectURL(files[0]);
                $('#imageContainer').attr({'src': file})
            }
            isImg=true;
            $('#usernameBtn').removeAttr("disabled");
            if (isFirst){
                cropper = new Cropper(image, {
                    aspectRatio: 1,
                    viewMode: 1,
                    background: true,  //是否显示网格背景
                    zoomable: true,   //是否允许放大图像
                    guides: true,   //是否显示裁剪框虚线
                    crop: function (event) { //剪裁框发生变化执行的函数。
                        canvas = cropper.getCroppedCanvas({  //使用canvas绘制一个宽和高200的图片
                            width: 220,
                            height: 220
                        });
                        $('#previewContainer').attr("src", canvas.toDataURL("image/png", 0.3))  //使用canvas toDataURL方法把图片转换为base64格式
                    }
                });
                isFirst=false;
            } else{
                cropper.replace(image.src);
            }
        });
        function dataURLtoBlob(dataurl) {
            var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new Blob([u8arr], { type: mime });
        }
    }());
    (function editorEmail(){
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        var email=false,verify=new Date().getTime(),isVerify=false;
        $("body").on("input propertychange","#fEmail",function(){
            if ($(this).val().length===0||!reg.test($(this).val())){
                email=false;
            }else{
                email=true;
            }
        });
        $('#verifyBtn').on('click', function () {
            var $btn = $(this).button('loading');
            if (email){
                setTimeout(function(){
                    $.ajax({
                        url:"/adm/verify/email",
                        type:"post",
                        data:{
                            fEmail:$("#fEmail").val()
                        },
                        success:function(res){
                            if(res.code==200){
                                $("#verifyBtn").text("验证通过");
                                $('#fEmail').attr("readonly","readonly");
                                $("#verifyBtn").attr("class","btn btn-success btn-block");
                                $('#verifyContainer').collapse("show");
                            }else{
                                Tools.tip(res.msg);
                                $btn.button('reset');
                            }
                        }
                    });
                },1000);
            } else{
                Tools.tip("请输入正确的邮箱格式。");
                $btn.button('reset')
            }
        });
        $('body').on("input propertychange","#verifyNum",function(){
            if($(this).val().length===6){
                $('#emailBtn').removeAttr("disabled");
                isVerify=true;
            }else{
                $('#emailBtn').attr("disabled","disabled");
                isVerify=false;
            }
        });
        $('#emailForm').submit(function(e){
            e.preventDefault();
            if (isVerify&&$("#verifyNum").val()===verify){
                var formData=new FormData(this);
                $.ajax({
                    url:"/adm/verify/email/"+formData.get("fId"),
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        if (res.code===200){
                            swal({
                                title:"绑定邮箱成功",
                                text:"点击“确定”返回首页",
                                type:"success",
                                confirmButtonColor:"#5cb85c",
                                confirmButtonText:"返回首页",
                                closeOnConfirm:false
                            },function(){
                                location.href="/adm/admin/index";
                            });
                        } else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("请输入正确的验证码。");
            }
        });
        jigsaw.init(document.getElementById('captcha'), function () {
            $('.refreshIcon').css("display","none");
            Tools.tip("验证通过，已向邮箱："+$('#fEmail').val()+"发送邮件，请在以下输入框输入邮件中的验证码以完成验证。");
            $('#verifyInput').collapse("show");
            $.ajax({
                url:"/adm/verify/email/send",
                type:"get",
                data:{
                    email:$('#fEmail').val(),
                    account:$('#fAccount').data("acc")
                },
                success:function(res){
                    if (res.code===200){
                        verify=res.data;
                    } else{
                        Tools.tip(res.msg);
                    }
                },
                error:function(err){
                    Tools.tip("网络出现错误，邮件未发送，请检查您的网络。");
                }
            });
        });
    }());
    (function editorPassword(){
        var password=false;
        $('body').on("input propertychange","#prePassword",function(){
            if ($(this).val().length===0){
                password=false;
            } else{
                password=true;
            }
        });
        $('#isPwd').click(function(){
            if (password){
                var formData=new FormData(document.getElementById("passwordForm"));
                formData.append("isRe",true);
                $.ajax({
                    url:"/adm/reset/"+formData.get("fId"),
                    type:"post",
                    processData:false,
                    contentType:false,
                    data:formData,
                    success:function(res){
                        if (res.code===200){
                            $('#newPwdInput').collapse("show");
                            $('#isPwd').text("验证通过").attr({
                                "class":"btn btn-success btn-block",
                                "disabled":"disabled"
                            });
                            $('#prePassword').attr("readonly","readonly");
                        }else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("原密码不能为空");
            }
        });
        var newPassword=false,rePassword=false;
        var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}');
        $('body').on("blur","#newPassword",function(){
            if($(this).val().length===0 || $(this).val().length<6 || $(this).val().length>16 || !regex.test($(this).val())){
                newPassword=false;
                Tools.tip("输入的密码不能为空，或输入的密码过于简单，请输入6至16位包含英文，字符，数字的密码。")
            }else{
                newPassword=true;
            }
        });
        $('body').on("input propertychange","#reNewPassword",function(){
            if ($(this).val().length===0 || $(this).val()!==$('#newPassword').val()){
                rePassword=false;
            } else{
                rePassword=true;
            }
        });
        $('#passwordForm').submit(function(e){
            e.preventDefault();
            var formData=new FormData(this);
            formData.append("isRe",false);
            if (newPassword && rePassword){
                $.ajax({
                    url:"/adm/reset/"+formData.get("fId"),
                    type:"post",
                    processData:false,
                    contentType:false,
                    data:formData,
                    success:function(res){
                        if (res.code===200){
                            swal({
                                title:"密码修改成功",
                                text:"点击“确定”返回首页",
                                type:"success",
                                confirmButtonColor:"#5cb85c",
                                confirmButtonText:"返回首页",
                                closeOnConfirm:false
                            },function(){
                                location.href="/adm/admin/index";
                            });
                        }else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("请完善密码信息再进行提交。");
            }
        });
    }());
});