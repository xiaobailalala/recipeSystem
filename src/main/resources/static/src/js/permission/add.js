$(function(){
    (function Add(){
        var name=false,introduction=false;
        $('body').on("input propertychange","#fPermissionname",function(){
            var reg = new RegExp("[A-z]:[A-z]");
            if (!reg.test($(this).val())||$(this).val()==null||$(this).val()===""||$(this).val()===undefined){
                name=false;
            }else{
                name=true;
            }
        });
        $('body').on("input propertychange","#fIntroduction",function(){
            if ($(this).val().length===0 || $(this).val()===undefined){
                introduction=false;
            }else{
                introduction=true;
            }
            if (isSubmit()){
                $('input[type=submit]').removeAttr("disabled");
            } else{
                $('input[type=submit]').attr("disabled","disabled");
            }
        });
        function isSubmit(){
            if (name&&introduction){
                return true;
            } else{
                return false;
            }
        }
        $('#verify').click(function(){
            if (name){
                $.ajax({
                    url:"/adminPermission/isName",
                    type:"post",
                    data:{fPermissionname:$('#fPermissionname').val()},
                    success:function(res){
                        if(res.code===200){
                            swal({
                                title:"验证通过",
                                text:"请输入您对该权限代号的描述...",
                                type:"success",
                                confirmButtonColor:"#5bc0de",
                                confirmButtonText:"确认"
                            });
                            $('#verify').attr("disabled","disabled").css("background-color","#5cb85c");
                            $('#fPermissionname').attr("readonly","readonly").addClass("text-center");
                            $('#subCont').collapse("show");
                        }else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("请输入正确的代号格式，请遵循（实体类英文名:行为英文描述）的格式进行命名。")
            }
        });
        $('form').submit(function(e){
            e.preventDefault();
            if (isSubmit()){
                var formData=new FormData(this);
                $.ajax({
                    url:"/adminPermission/perm",
                    type:'post',
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        Tools.successAddTimeoutTip(res,3);
                    }
                });
            } else{
                Tools.tip("完善信息后再尝试提交。")
            }
        });
    }());
});