$(function () {
    (function Add() {
        var account = false, password = false, repassword = false;
        Tools.body.on('input propertychange', "#fAccount", function () {
            var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
            account = !($(this).val().length === 0 || $(this).val() === undefined || $(this).val() === '' || $(this).val() === null || !reg.test($(this).val()));
            isSubmit();
        });
        Tools.body.on('input propertychange', "#fPassword", function () {
            var reg = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}');
            password = !($(this).val().length === 0 || $(this).val() === undefined || $(this).val() === '' || $(this).val() === null || !reg.test($(this).val()) || $(this).val() !== $("#fPassword").val());
            isSubmit();
        });
        Tools.body.on('input propertychange', "#rePassword", function () {
            repassword = !($(this).val().length === 0 || $(this).val() === undefined || $(this).val() === '' || $(this).val() === null || $(this).val() !== $("#fPassword").val());
            isSubmit();
        });
        function isSubmit(){
            if (account && password && repassword) {
                $('input[type=submit]').removeAttr("disabled");
                return true;
            } else {
                $('input[type=submit]').attr("disabled", "disabled");
                return false;
            }
        }
        $('form').submit(function (e) {
            e.preventDefault();
            if (isSubmit()) {
                var formData = new FormData(this);
                $.ajax({
                    url: "/manage/commonUser/info",
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (res) {
                        Tools.successAddTimeoutTip(res, 3);
                    }
                });
            } else {
                Tools.tip("请完善信息后再尝试提交。")
            }
        });
    }());
});