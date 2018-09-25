$(function () {
    $(".showDetail").click(function () {
        var id = $(this).data("id");
        $.ajax({
            url: "/recipe/detailInfo/" + id,
            type: "get",
            success: function (res) {
                var fire = res.data.fFire === '0' ? '无' : res.data.fFire;
                var tips = '', cla = '', material = '', process = '';
                res.data.recipeTips.forEach(function (item, index) {
                    tips += '<button class="btn btn-sm ' + item.tips.fStyle + '" disabled>' + item.tips.fName + '</button>&emsp;';
                });
                res.data.recipeClassifies.forEach(function (item, index) {
                    if (item.fTwoId === 0) {
                        cla += '<li class="list-group-item">' + item.classify.classifyTwo.classifyOne.fName + ' <i class="fa fa-long-arrow-right"></i> ' + item.classify.classifyTwo.fName + ' <i class="fa fa-long-arrow-right"></i> ' + item.classify.fName + '</li>';
                    } else {
                        cla += '<li class="list-group-item">' + item.classifyTwo.classifyOne.fName + ' <i class="fa fa-long-arrow-right"></i> ' + item.classifyTwo.fName + '</li>';
                    }
                });
                res.data.recipeMaterials.forEach(function (item, index) {
                    material += '<tr><td>' + item.fName + '</td><td> ' + item.fNumber + ' </td></tr>';
                });
                res.data.processes.forEach(function (item, index) {
                    process += '<li class="list-group-item">\n' +
                        '          <div style="height: 180px;position: relative;">\n' +
                        '              <div style="padding-right: 280px;">\n' +
                        '                   <p><strong>步骤' + (index + 1) + '、</strong></p>\n' +
                        '                   <p><strong>流程所需时间：</strong></p>\n' +
                        '                   <p>' + item.fRequest + '<strong>&nbsp;秒</strong></p>\n' +
                        '              </div>\n' +
                        '              <img src="'+Tools.fileServerPath+item.fCover+'" width="260" height="180" style="right: 0;top: 0;position: absolute;" alt="">\n' +
                        '          </div>\n' +
                        '           <div class="margin-t-10"><strong>流程内容：</strong>'+item.fContent+'</div>\n' +
                        '       </li>\n';
                });
                var div = '<div class="modal fade" id="detailInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">\n' +
                    '        <div class="modal-dialog" role="document">\n' +
                    '            <div class="modal-content">\n' +
                    '                <div class="modal-header">\n' +
                    '                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                    '                    <h4 class="modal-title" id="myModalLabel">食谱信息详情</h4>\n' +
                    '                </div>\n' +
                    '                <div class="modal-body">\n' +
                    '                    <form class="fieldset-form">\n' +
                    '                        <div class="form-group text-center">\n' +
                    '                            <img src="' + Tools.fileServerPath + res.data.fCover + '" alt="cover" width="300" height="200">\n' +
                    '                        </div>\n' +
                    '                        <div class="margin-b-10">\n' +
                    '                            <fieldset>\n' +
                    '                                <legend>基本信息</legend>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">食谱名</label>\n' +
                    '                                    <input type="text" class="form-control" value="' + res.data.fName + '" readonly>\n' +
                    '                                </div>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">食谱介绍</label>\n' +
                    '                                    <textarea class="form-control" rows="6" readonly>' + res.data.fIntroduction + '</textarea>\n' +
                    '                                </div>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">火候监控</label>\n' +
                    '                                    <input type="text" class="form-control" value="' + fire + '" readonly>\n' +
                    '                                </div>\n' +
                    '                            </fieldset>\n' +
                    '                        </div>\n' +
                    '                        <div class="margin-b-10">\n' +
                    '                            <fieldset>\n' +
                    '                                <legend>分类信息</legend>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">食谱标签</label>\n' +
                    '                                    <div>\n' + tips +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">食谱分类</label>\n' +
                    '                                    <ul class="list-group">\n' + cla +
                    '                                    </ul>\n' +
                    '                                </div>\n' +
                    '                            </fieldset>\n' +
                    '                        </div>\n' +
                    '                        <div class="margin-b-10">\n' +
                    '                            <fieldset>\n' +
                    '                                <legend>流程信息</legend>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">食材列表</label>\n' +
                    '                                    <table class="table table-bordered">\n' +
                    '                                        <thead>\n' +
                    '                                            <tr>\n' +
                    '                                                <th>食材名</th>\n' +
                    '                                                <th>剂量/备注</th>\n' +
                    '                                            </tr>\n' +
                    '                                        </thead>\n' +
                    '                                        <tbody>\n' + material +
                    '                                        </tbody>\n' +
                    '                                    </table>\n' +
                    '                                </div>\n' +
                    '                                <div class="form-group">\n' +
                    '                                    <label class="form-label">制作流程</label>\n' +
                    '                                    <ul class="list-group">\n' + process +
                    '                                    </ul>\n' +
                    '                                </div>\n' +
                    '                            </fieldset>\n' +
                    '                        </div>\n' +
                    '                    </form>\n' +
                    '                </div>\n' +
                    '                <div class="modal-footer">\n' +
                    '                    <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>';
                $("#temporaryContainer").html(div);
                $("#detailInfoModal").modal("show");
            }
        });
    });
    $('body').on('click','.deleteInfo',function(){
        var id=$(this).data("id");
        swal({
            title: "确定删除吗？",
            text: "你将无法恢复该食谱信息",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定删除",
            closeOnConfirm: false
        },function(){
            var formData=new FormData();
            formData.append("_method","delete");
            $.ajax({
                url:"/recipe/info/"+id,
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