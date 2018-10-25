$(function(){
    (function Delete(){
        $('body').on("click",".deleteInfo",function(){
            var id=$(this).data("id");
            swal({
                title: "确定删除吗？",
                text: "你将无法恢复该分类信息",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除",
                closeOnConfirm: false
            },function(){
                var formData=new FormData();
                formData.append("_method","delete");
                $.ajax({
                    url:"/manage/cla/info/"+id,
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
    }());
});