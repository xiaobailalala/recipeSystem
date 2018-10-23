$(function () {
    (function Add() {
        var account = false, username = false, password = false, repwd = false;
        Tools.body.on("input propertychange", "#fAccount", function () {
            var reg = new RegExp("^[a-zA-Z0-9_-]{4,16}$");
            account = !(!reg.test($(this).val()) || $(this).val() == null || $(this).val() === "" || $(this).val() === undefined);
        });
        Tools.body.on("input propertychange", "#fUsername", function () {
            username = !($(this).val().length === 0 || $(this).val() === undefined);
            if (isSubmit()) {
                $('input[type=submit]').removeAttr("disabled");
            } else {
                $('input[type=submit]').attr("disabled", "disabled");
            }
        });
        Tools.body.on("input propertychange", "#fPassword", function () {
            var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}');
            password = !(!regex.test($(this).val()) || $(this).val() == null || $(this).val() === "" || $(this).val() === undefined);
            if (isSubmit()) {
                $('input[type=submit]').removeAttr("disabled");
            } else {
                $('input[type=submit]').attr("disabled", "disabled");
            }
        });
        Tools.body.on("input propertychange", "#rePwd", function () {
            repwd = !($(this).val() !== $('#fPassword').val() || $(this).val() == null || $(this).val() === "" || $(this).val() === undefined);
            if (isSubmit()) {
                $('input[type=submit]').removeAttr("disabled");
            } else {
                $('input[type=submit]').attr("disabled", "disabled");
            }
        });

        function isSubmit() {
            return account && username && password && repwd;
        }

        $('#verify').click(function () {
            if (account) {
                $.ajax({
                    url: "/adminUser/isAcc",
                    type: "post",
                    data: {fAccount: $('#fAccount').val()},
                    success: function (res) {
                        if (res.code === 200) {
                            swal({
                                title: "验证通过",
                                text: "请完成以下信息...",
                                type: "success",
                                confirmButtonColor: "#5bc0de",
                                confirmButtonText: "确认"
                            });
                            $('#verify').attr("disabled", "disabled").css("background-color", "#5cb85c");
                            $('#fAccount').attr("readonly", "readonly").addClass("text-center");
                            $('#subCont').collapse("show");
                        } else {
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else {
                Tools.tip("请输入正确的账号格式：4到16位（字母，数字，下划线，减号）。")
            }
        });
        $('form').submit(function (e) {
            e.preventDefault();
            if (password) {
                if (repwd) {
                    if (isSubmit()) {
                        var formData = new FormData(this);
                        $.ajax({
                            url: "/adminUser/add",
                            type: 'post',
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (res) {
                                Tools.successAddTimeoutTip(res,3);
                            }
                        });
                    } else {
                        Tools.tip("完善信息后再尝试提交。")
                    }
                } else {
                    Tools.tip("您两次输入的密码不一致。");
                }
            } else {
                Tools.tip("您设置的密码强度不够，请输入正确的格式：6-16位，包括至少字母，数字，1个特殊字符");
            }
        });
    }())
});