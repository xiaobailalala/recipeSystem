$(function(){
    (function Editor(){
        var name = true,oid=true,cover=true,bg=true,color=true;
        $('body').on('input propertychange',"#fName",function(){
            if ($(this).val().length === 0 || $(this).val()===undefined || $(this).val()==='' || $(this).val()===null){
                $('input[type=submit]').attr("disabled","disabled");
            }else{
                $('input[type=submit]').removeAttr("disabled");
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
                    url:"/claTwo/info/"+formData.get("fId"),
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
                    success:function(res){
                        if(res.code===200){
                            setTimeout(function(){
                                window.location.href="/claTwo/info";
                            },3000);
                            swal({
                                title:"修改成功",
                                text:"系统将在3秒后返回列表，或者您可以点击“确认”手动跳转",
                                type:"success",
                                confirmButtonColor:"#5bc0de",
                                confirmButtonText:"确认",
                                closeOnConfirm:false
                            },function(){
                                window.location.href="/claTwo/info";
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