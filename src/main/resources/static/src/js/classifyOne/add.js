$(function(){
    (function Add() {
        var name = false;
        $('body').on('input propertychange', "#fName", function () {
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
                    url:"/claOne/info",
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        Tools.successAddTimeoutTip(res,3);
                    }
                });
            } else{
                Tools.tip("请完善信息后再尝试提交。")
            }
        });
    }());
});