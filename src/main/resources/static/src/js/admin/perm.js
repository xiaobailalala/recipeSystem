$(function(){
    (function showPerm(){
        $('.showPerm').click(function(){
            var id=$(this).data("id");
            $.ajax({
                url:"/adminRole/role/getPerm/"+id,
                type:"get",
                success:function(res){
                    var content='';
                    if (res.adminRolePermissions.length===0){
                        content='该角色尚未赋予任何权限';
                    }else{
                        content='<table class="table table-bordered">' +
                            '<thead><tr><th>序号</th><th>权限代号</th><th>权限描述</th></tr></thead><tbody>';
                        res.adminRolePermissions.forEach(function(item,index){
                            content+='<tr>' +
                                '<td>'+(index+1)+'</td>' +
                                '<td>'+item.adminPermission.fPermissionname+'</td>' +
                                '<td>'+item.adminPermission.fIntroduction+'</td>' +
                                '</tr>';
                        });
                        content+='</tbody></table>';
                    }
                    var cont='<div class="modal fade bs-example-modal-lg" id="permModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
                        '  <div class="modal-dialog modal-lg" role="document">' +
                        '    <div class="modal-content">' +
                        '      <div class="modal-header">' +
                        '        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                        '        <h4 class="modal-title" id="myModalLabel">查看角色权限</h4>' +
                        '      </div>' +
                        '      <div class="modal-body">' +
                        '        ' +content+
                        '      </div>' +
                        '      <div class="modal-footer">' +
                        '        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>' +
                        '      </div>' +
                        '    </div>' +
                        '  </div>' +
                        '</div>';
                    $('#temporaryContainer').html(cont);
                    $('#permModal').modal("show");
                }
            });
        });
    }());
    (function deleteRole(){
        $('#deleteRole').click(function(){
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
                    text: "该操作将会移除该管理员的选中角色，是否继续操作？",
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
                        url:"/adminUser/user/perm/delete",
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
    (function AddRole(){
        $('#addRole').click(function(){
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
                    text: "该操作将会令管理员拥有所选中角色，是否继续操作？",
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
                        url:"/adminUser/user/perm/add",
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