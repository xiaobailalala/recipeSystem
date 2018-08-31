$(function(){
    (function Select(){
        $('#fOid').change(function(){
            if ($(this).val()!==-1){
                $.ajax({
                    url:"/claTwo/getbyoid/"+$(this).val(),
                    type:"post",
                    success:function(res){
                        var opt='<option value="-1">请先选择一级分类</option>';
                        res.data.forEach(function(item,index){
                            opt+='<option value="'+item.fId+'">'+item.fName+'</option>';
                        });
                        $('#fTid').html(opt);
                    }
                });
            } else{
                var opt='<option value="-1">请先选择一级分类</option>';
                $('#fTid').html(opt);
            }
        });
    }());
    (function Add() {
        var name = false,select=false;
        $('#fTid').change(function(){
            if($(this).val()!==-1){
                select=true;
            }else{
                select=false;
            }
            isSub();
        });
        $('body').on('input propertychange', "#fName", function () {
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                name=false;
            }else{
                name=true;
            }
            isSub();
        });
        function isSub(){
            if (select&&name){
                $('input[type=submit]').removeAttr("disabled");
            } else{
                $('input[type=submit]').attr("disabled","disabled");
            }
        }
        $('form').submit(function(e){
            e.preventDefault();
            if (name&&select){
                var formData=new FormData(this);
                $.ajax({
                    url:"/cla/info",
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