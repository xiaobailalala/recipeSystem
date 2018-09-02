$(function(){
    var name = false;
    $('body').on("input propertychange","#fName",function(){
        if ($(this).val().length === 0 || $(this).val() === null || $(this).val() === undefined || $(this).val() === ""){
            name=false;
            $("input[type=submit]").attr("disabled","disabled");
        } else{
            name=true;
            $("input[type=submit]").removeAttr("disabled");
        }
    });
    $('form').submit(function(e){
        e.preventDefault();
        var formData=new FormData(this);
        if (name){
            $.ajax({
                url:"/tips/saveInfo",
                type:"post",
                data:formData,
                processData:false,
                contentType:false,
                success:function(res){
                    Tools.successAddTimeoutTip(res,3);
                }
            });
        } else{
            Tools.tip("请完善资料后再尝试提交");
        }
    });
   (function asd(){}()); 
});
