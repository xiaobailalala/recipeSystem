$(function(){
    (function resetPwd(){
        $('.resetPwd').click(function(){
            var id=$(this).data('id');
            var email=$(this).data("email");
            var acc=$(this).data("acc");
            if (email===null || email==="" || email===undefined){
                Tools.tip("该用户未绑定邮箱，无法进行密码重置操作。")
            } else{
                swal({
                    title: "是否进行密码重置？",
                    text: "系统将发送新密码到该用户所绑定的邮箱",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#f0ad4e",
                    confirmButtonText: "发送",
                    closeOnConfirm: false
                },function(){
                    var formData=new FormData();
                    formData.append("fId",id);
                    formData.append("_method","put");
                    formData.append("fEmail",email);
                    formData.append("fAccount",acc);
                    $.ajax({
                        url:"/adminUser/list/"+id,
                        type:"post",
                        data:formData,
                        processData:false,
                        contentType:false,
                        success:function(res){
                            if (res.code===200){
                                swal({
                                    title: "重置成功",
                                    text: "点击“确认”刷新页面",
                                    type: "success",
                                    confirmButtonColor: "#5cb85c",
                                    confirmButtonText: "确认",
                                    closeOnConfirm: false
                                },function(){
                                    location.reload();
                                });
                            } else{
                                Tools.tip(res.msg);
                            }
                        }
                    });
                });
            }
        });
    }());
    (function deleteInfo(){
        $('.deleteInfo').click(function(){
            var id=$(this).data('id');
            swal({
                title: "确定删除吗？",
                text: "你将无法恢复该账号及其资料",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除",
                closeOnConfirm: false
            },function(){
                var formData=new FormData();
                formData.append("fId",id);
                formData.append("_method","delete");
                $.ajax({
                    url:"/adminUser/list/"+id,
                    type:"post",
                    data:formData,
                    processData:false,
                    contentType:false,
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