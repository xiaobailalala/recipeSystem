$(function(){
    (function Editor(){
        var name=true,introduction=true;
        $('body').on("input propertychange","#fRolename",function(){
            var reg = new RegExp("[A-z]:[A-z]");
            if (!reg.test($(this).val())||$(this).val()==null||$(this).val()===""||$(this).val()===undefined){
                name=false;
            }else{
                name=true;
            }
            if (isSubmit()){
                $('input[type=submit]').removeAttr("disabled");
            } else{
                $('input[type=submit]').attr("disabled","disabled");
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
        $('form').submit(function(e){
            e.preventDefault();
            if (name&&introduction){
                var formData=new FormData(this);
                $.ajax({
                    url:"/manage/adminRole/role/"+formData.get("fId"),
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        if(res.code===200){
                            setTimeout(function(){
                                window.location.href="/manage/adminRole/role";
                            },3000);
                            swal({
                                title:"修改成功",
                                text:"系统将在3秒后返回列表，或者您可以点击“确认”手动跳转",
                                type:"success",
                                confirmButtonColor:"#5bc0de",
                                confirmButtonText:"确认",
                                closeOnConfirm:false
                            },function(){
                                window.location.href="/manage/adminRole/role";
                            });
                        }else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("完善信息后再尝试提交。");
            }
        });
    }());
});