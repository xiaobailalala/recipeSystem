$(function(){
    (function Add() {
        var name = false,oid=false,cover=false,bg=false,color=false;
        $('body').on('input propertychange', "#fName", function () {
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                name=false;
            }else{
                name=true;
            }
            isSub();
        });
        $('body').on('input propertychange', "#fBg", function () {
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                bg=false;
            }else{
                bg=true;
            }
            isSub();
        });
        $('body').on('input propertychange', "#fColor", function () {
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                color=false;
            }else{
                color=true;
            }
            isSub();
        });
        $('#fOid').change(function(){
            if ($(this).val()===-1){
                oid=false;
            } else{
                oid=true;
            }
            isSub();
        });
        $('#browser').click(function(){
            $('#fCover').click();
        });
        $('#fCover').change(function(){
            if (this.files[0]) {
                onImg(this.files[0]);
            }
        });
        function onImg(data) {
            var fs = new FileReader();
            fs.readAsDataURL(data);
            fs.onload = function () {
                var image='<img src="'+fs.result+'" style="height: 88px;max-width: 138px;" alt="cover">';
                $('#imgCont').html(image);
                cover = true;
                isSub();
            }
        }
        function isSub(){
            if (name&&oid&&cover&&bg&&color){
                $('input[type=submit]').removeAttr("disabled");
            } else{
                $('input[type=submit]').attr("disabled","disabled");
            }
        }
        $('form').submit(function(e){
            e.preventDefault();
            if (name&&oid&&cover&&bg&&color){
                var formData=new FormData(this);
                $.ajax({
                    url:"/claTwo/info",
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