$(function () {
    // $("img.lazy").lazyload({
    //     effect: "fadeIn",
    //     threshold: 50,
    //     load: function () {
    //         $('#imgSource').masonry({
    //             itemSelector: '.imgItem'
    //         });
    //     }
    // });
    $('#imgSource').masonry({
        itemSelector: '.imgItem'
    });
    $(".imgSource").click(function(){
        $(".imgSource,.voiceSource,.otherSource").removeClass("active");
        $("#imgSource,#voiceSource,#otherSource").removeClass("active");
        $(this).addClass("active");
        $('#imgSource').addClass("active").masonry({
            itemSelector: '.imgItem'
        });
    });
    $(".voiceSource").click(function(){
        $(".imgSource,.voiceSource,.otherSource").removeClass("active");
        $("#imgSource,#voiceSource,#otherSource").removeClass("active");
        $(this).addClass("active");
        $('#voiceSource').addClass("active").masonry({
            itemSelector: ".voiceItem"
        });
    });
    Tools.body.on('click','.deleteImg',function(){
        const name = $(this).data("name");
        swal({
            title: "确定删除吗？",
            text: "你将无法恢复该资源信息",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除",
            closeOnConfirm: false
        },function(){
            const formData = new FormData();
            formData.append("_method","delete");
            formData.append("name", name);
            $.ajax({
                url:"/manage/sysResource/fileManage",
                type:"post",
                processData:false,
                contentType:false,
                data:formData,
                success:function(res){
                    if (res.code===200){
                        swal({
                            title: "删除成功",
                            text: "系统将在3秒后刷新页面，或者可以点击“确认”手动刷新",
                            type: "success",
                            confirmButtonColor: "#5cb85c",
                            confirmButtonText: "确认",
                            closeOnConfirm: false
                        },function(){
                            location.reload();
                        });
                        setTimeout(function(){
                            location.reload();
                        },3000);
                    } else{
                        Tools.tip(res.msg);
                    }
                }
            });
        });
    });
});