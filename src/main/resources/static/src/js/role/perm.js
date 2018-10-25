$(function(){
    (function DeletePerm(){
        $('#deletePerm').click(function(){
            var rid=$(this).data("id");
            var includeArr=new Array();
            $("input[name=include]:checked").each(function(){
                includeArr.push($(this).val());
            });
            if (includeArr.length===0){
                Tools.tip("请至少选择一项，再进行该操作");
            }else{
                swal({
                    title: "温馨提示",
                    text: "该操作将会移除该角色的该项权限，是否继续操作？",
                    type: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#d9534f",
                    confirmButtonText: "删除",
                    closeOnConfirm: false
                },function(){
                    var formData=new FormData();
                    formData.append("includeArr",includeArr);
                    formData.append("rid",rid);
                    $.ajax({
                        url:"/manage/adminRole/role/perm/delete",
                        data:formData,
                        processData:false,
                        contentType:false,
                        type:"post",
                        success:function(res){
                            if (res.code===200){
                                swal({
                                    title: "移除成功",
                                    text: "系统将在3秒后刷新页面，或者可以点击“确认”手动刷新",
                                    type: "success",
                                    confirmButtonColor: "#5bc0de",
                                    confirmButtonText: "确认",
                                    closeOnConfirm: false
                                },function(){
                                    location.reload();
                                });
                                setTimeout(function(){
                                    location.reload();
                                },3000);
                            } else{
                                swal({
                                    title: "删除失败",
                                    text: res.msg,
                                    type: "error",
                                    confirmButtonColor: "#f0ad4e",
                                    confirmButtonText: "刷新",
                                    closeOnConfirm: false
                                },function(){
                                    location.reload();
                                });
                            }
                        }
                    });
                });
            }
        });
    }());
    (function AddPerm(){
        $('#addPerm').click(function(){
            var rid=$(this).data('id');
            var excludeArr=new Array();
            $("input[name=exclude]:checked").each(function(){
                excludeArr.push($(this).val());
            });
            if (excludeArr.length===0){
                Tools.tip("请至少选择一项，再进行该操作");
            }else{
                swal({
                    title: "温馨提示",
                    text: "该操作将会令用户拥有所选中权限，是否继续操作？",
                    type: "info",
                    showCancelButton: true,
                    confirmButtonColor: "#5bc0de",
                    confirmButtonText: "确认",
                    closeOnConfirm: false
                },function(){
                    var formData=new FormData();
                    formData.append("excludeArr",excludeArr);
                    formData.append("rid",rid);
                    $.ajax({
                        url:"/manage/adminRole/role/perm/add",
                        data:formData,
                        processData:false,
                        contentType:false,
                        type:"post",
                        success:function(res){
                            if (res.code===200){
                                swal({
                                    title: "添加成功",
                                    text: "系统将在3秒后刷新页面，或者可以点击“确认”手动刷新",
                                    type: "success",
                                    confirmButtonColor: "#5bc0de",
                                    confirmButtonText: "确认",
                                    closeOnConfirm: false
                                },function(){
                                    location.reload();
                                });
                                setTimeout(function(){
                                    location.reload();
                                },3000);
                            } else{
                                swal({
                                    title: "添加失败",
                                    text: res.msg,
                                    type: "error",
                                    confirmButtonColor: "#f0ad4e",
                                    confirmButtonText: "刷新",
                                    closeOnConfirm: false
                                },function(){
                                    location.reload();
                                });
                            }
                        }
                    });
                });
            }
        });
    }());
});