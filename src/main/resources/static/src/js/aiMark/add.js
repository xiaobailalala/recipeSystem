$(function(){
    (function Add(){
        var name=false,introduction=false;
        $('body').on("input propertychange","#fMark",function(){
            var reg = new RegExp("[A-Z]");
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
        $('body').on("input propertychange","#fContent",function(){
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
            if (isSubmit()){
                var formData=new FormData(this);
                $.ajax({
                    url:"/manage/aiMark/info",
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