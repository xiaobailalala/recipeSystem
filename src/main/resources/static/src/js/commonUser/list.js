$(function () {
    (function Delete() {
        Tools.body.on('click', '.deleteInfo', function () {
            var id = $(this).data("id");
            swal({
                title: "确定删除吗？",
                text: "你将无法恢复该职业信息",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除",
                closeOnConfirm: false
            }, function () {
                var formData = new FormData();
                formData.append("_method", "delete");
                $.ajax({
                    url: "/manage/commonUser/info/" + id,
                    type: "post",
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (res) {
                        if (res.code === 200) {
                            swal({
                                title: "删除成功",
                                text: "系统将在3秒后刷新页面，或者可以点击“确认”手动刷新",
                                type: "success",
                                confirmButtonColor: "#5cb85c",
                                confirmButtonText: "确认",
                                closeOnConfirm: false
                            }, function () {
                                location.reload();
                            });
                            setTimeout(function () {
                                location.reload();
                            }, 3000);
                        } else {
                            Tools.tip(res.msg);
                        }
                    }
                });
            });
        });
    }());
    var stompClient = null;
    (function SensorsMonitor() {
        var socket = new SockJS('/endpoint-websocket-webClient');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/sensorData/sendFireNumber', function (result) {
                var objArr = JSON.parse(result.body);
                $(".fireMonitor").each(function () {
                    var id = $(this).data("id");
                    var isExist = objArr.find(({uid}) => id === uid);
                    if (isExist) {
                        if ($(this).hasClass("label-custom-bg")) {
                            $(this).removeClass("label-custom-bg");
                        }
                    } else {
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
            stompClient.subscribe('/sensorData/sendSmogNumber', function (result) {
                const objArr = JSON.parse(result.body);
                $(".smogMonitor").each(function () {
                    const id = $(this).data("id");
                    const isExist = objArr.find(({uid}) => id === uid);
                    if (isExist) {
                        if ($(this).hasClass("label-custom-bg")) {
                            $(this).removeClass("label-custom-bg");
                        }
                    } else {
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
            stompClient.subscribe("/sensorData/sendInfraredNumber", function (result) {
                const objArr = JSON.parse(result.body);
                $(".infraredMonitor").each(function () {
                    const id = $(this).data("id");
                    const isExist = objArr.find(({uid}) => id === uid);
                    if (isExist) {
                        if ($(this).hasClass("label-custom-bg")) {
                            $(this).removeClass("label-custom-bg");
                        }
                    } else {
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
            stompClient.subscribe("/sensorData/sendDistanceNumber", function (result) {
                const objArr = JSON.parse(result.body);
                $(".distanceMonitor").each(function () {
                    const id = $(this).data("id");
                    const isExist = objArr.find(({uid}) => id === uid);
                    if (isExist) {
                        if ($(this).hasClass("label-custom-bg")) {
                            $(this).removeClass("label-custom-bg");
                        }
                    } else {
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
        });
    }());
    (function ResetPwd() {
        Tools.body.on("click", ".resetPwd", function () {
            var account = $(this).data("id");
            swal({
                title: "重置密码",
                text: "系统将会把重置后的密码发送至" + account,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#5bc0de",
                confirmButtonText: "确认发送",
                closeOnConfirm: false
            }, function () {
                $.ajax({
                    url: "/manage/commonUser/repwd",
                    type: "POST",
                    data: {
                        fAccount: account
                    },
                    success: function (res) {
                        swal("发送成功", "用户密码重置成功", "success");
                    }
                });
            });
        });
    }());
    (function MessageNotification() {
        var content = false, cover = false;
        Tools.body.on('input propertychange', "#content", function () {
            content = !($(this).val().length === 0 || $(this).val() === undefined || $(this).val() === '' || $(this).val() === null);
        });
        Tools.body.on("click", ".showNotificationModal", function () {
            var id = $(this).data("id");
            var name = $(this).data("name");
            var div = '<div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">\n' +
                '        <div class="modal-dialog" role="document">\n' +
                '            <div class="modal-content">\n' +
                '                <div class="modal-header">\n' +
                '                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                '                    <h4 class="modal-title" id="myModalLabel">用户消息推送</h4>\n' +
                '                </div>\n' +
                '                <div class="modal-body">\n' +
                '                    <form class="form-horizontal sendMessageForm">\n' +
                '                        <div class="form-group">\n' +
                '                            <label class="col-sm-2 control-label form-label"></label>\n' +
                '                            <div class="col-sm-10">您正在向 <strong>' + name + '</strong> 发送消息\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="form-group">\n' +
                '                            <label class="col-sm-2 control-label form-label" for="cover">封面</label>\n' +
                '                            <div class="col-sm-10">\n' +
                '                                <div class="notifiImgContainer" id="cover" style="text-align: center;width: 160px;height: 160px;border-radius: 8px;display: inline-block;vertical-align: bottom;overflow: hidden;">\n' +
                '                                    <div style="width: 100%;height: 100%;background-color: #eeeeee;display: flex;align-items: center;justify-content: center;"><i style="font-size: 50px;font-weight: bold;color: #888888;" class="fa fa-plus"></i></div>\n' +
                '                                </div>\n' +
                '                                <input class="imgInput" type="file" style="display: none;"/>' +
                '                                <button class="btn btn-info margin-l-20 imgBrowser" type="button">浏览</button>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                        <div class="form-group">\n' +
                '                            <label class="col-sm-2 control-label form-label" for="content">内容</label>\n' +
                '                            <div class="col-sm-10">\n' +
                '                                <textarea name="content" class="form-control" id="content" rows="10"></textarea>\n' +
                '                            </div>\n' +
                '                        </div>\n' +
                '                    </form>\n' +
                '                </div>\n' +
                '                <div class="modal-footer">\n' +
                '                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>\n' +
                '                    <button type="button" class="btn btn-primary sendMessage" data-id="' + id + '">发送</button>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>';
            $("#temporaryContainer").html(div);
            $("#notificationModal").modal("show");
        });
        Tools.body.on("click", ".imgBrowser", function () {
            $(".imgInput").click();
        });
        Tools.body.on("change", ".imgInput", function () {
            if (this.files[0]) {
                onImg(this.files[0]);
            }
        });

        function onImg(data) {
            var fs = new FileReader();
            fs.readAsDataURL(data);
            fs.onload = function () {
                var image = '<img src="' + fs.result + '" style="height: 100%;max-width: 100%;" alt="cover">';
                $('.notifiImgContainer').html(image);
                cover = true;
            }
        }

        Tools.body.on("click", ".sendMessage", function () {
            if (content) {
                $(".sendMessageForm").submit();
            } else {
                Tools.tip("请填写推送内容。")
            }
        });

        Tools.body.on("submit", ".sendMessageForm", function(e) {
            e.preventDefault();
            var formData = new FormData(this);
            $.ajax({
                url: "",
                type: "POST",
                contentType: false,
                processData: false,
                data: formData,
                success: res => {

                }
            });
        });
    }());
});