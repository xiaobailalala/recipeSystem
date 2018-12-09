$(function(){
    var name = false, cover = false;
    $('body').on("input propertychange","#fName",function(){
        if ($(this).val().length === 0 || $(this).val() === null || $(this).val() === undefined || $(this).val() === ""){
            name=false;
            $("input[type=submit]").attr("disabled","disabled");
        } else{
            name=true;
            $("input[type=submit]").removeAttr("disabled");
        }
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
        }
    }
    $('form').submit(function(e){
        e.preventDefault();
        var formData=new FormData(this);
        if (name&&cover){
            $.ajax({
                url:"/manage/material/info",
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
