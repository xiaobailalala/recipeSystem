$(function(){
    (function Delete(){
        $('body').on('click','.deleteInfo',function(){
            var id=$(this).data("id");
            swal({
                title: "确定删除吗？",
                text: "你将无法恢复该角色信息",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除",
                closeOnConfirm: false
            },function(){
                var formData=new FormData();
                formData.append("_method","delete");
                $.ajax({
                    url:"/adminRole/role/"+id,
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
    (function ShowPerm(){
        $('body').on('click','.showPerm',function(){
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
                                '<td>'+item.adminPermission.fpermissionname+'</td>' +
                                '<td>'+item.adminPermission.fintroduction+'</td>' +
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
});