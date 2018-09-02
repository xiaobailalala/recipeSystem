$(function(){
    (function Add(){
        $('#browser').click(function(){
            $('#fCover').click();
        });
        $('#fCover').change(function(){
            if (this.files[0]){
                onImg(this.files[0]);
            }
        });
        function onImg(data) {
            var fs = new FileReader();
            fs.readAsDataURL(data);
            fs.onload = function () {
                var image='<img src="'+fs.result+'" style="height: 100%;width: 100%;" alt="cover">';
                $('#imgCont').html(image);
            }
        }
    }());
    (function () {
        和客家话艰苦好看
    });
});