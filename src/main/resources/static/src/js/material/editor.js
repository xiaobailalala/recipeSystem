$(function(){
    (function Editor(){
        var name = true;
        $('body').on('input propertychange',"#fName",function(){
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                name=false;
                $('input[type=submit]').attr("disabled","disabled");
            }else{
                name=true;
                $('input[type=submit]').removeAttr("disabled");
            }
        });
        $('form').submit(function(e){
            e.preventDefault();
            if (name){
                var formData=new FormData(this);
                $.ajax({
                    url:"/manage/material/info/"+formData.get("fId"),
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        if(res.code===200){
                            setTimeout(function(){
                                window.location.href="/manage/material/info";
                            },3000);
                            swal({
                                title:"修改成功",
                                text:"系统将在3秒后返回列表，或者您可以点击“确认”手动跳转",
                                type:"success",
                                confirmButtonColor:"#5bc0de",
                                confirmButtonText:"确认",
                                closeOnConfirm:false
                            },function(){
                                window.location.href="/manage/material/info";
                            });
                        }else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else{
                Tools.tip("请完善信息后再尝试提交。")
            }
        });
    }());
});