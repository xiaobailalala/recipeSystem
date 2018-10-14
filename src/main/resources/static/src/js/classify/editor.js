$(function () {
    (function Select() {
        $('#fOid').change(function () {
            $.ajax({
                url: "/claTwo/getbyoid/" + $(this).val(),
                type: "post",
                success: function (res) {
                    var opt = '<option value="-1">请先选择一级分类</option>';
                    res.data.forEach(function (item, index) {
                        opt += '<option value="' + item.fid + '">' + item.fname + '</option>';
                    });
                    $('#fTid').html(opt);
                }
            });
        });
    }());
    (function Editor() {
        var name = true, select = true;
        $('#fOid').change(function(){
            select=false;
            isSub();
        });
        $('#fTid').change(function () {
            if ($(this).val() !== -1) {
                select = true;
            } else {
                select = false;
            }
            isSub();
        });
        $('body').on('input propertychange', "#fName", function () {
            if ($(this).val().length === 0 || $(this).val() === undefined || $(this).val() === '' || $(this).val() === null) {
                name = false;
            } else {
                name = true;
            }
            isSub();
        });

        function isSub() {
            if (select && name) {
                $('input[type=submit]').removeAttr("disabled");
            } else {
                $('input[type=submit]').attr("disabled", "disabled");
            }
        }

        $('form').submit(function (e) {
            e.preventDefault();
            if (name && select) {
                var formData = new FormData(this);
                $.ajax({
                    url: "/cla/info/" + formData.get("fId"),
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (res) {
                        if (res.code === 200) {
                            setTimeout(function () {
                                window.location.href = "/cla/info";
                            }, 3000);
                            swal({
                                title: "修改成功",
                                text: "系统将在3秒后返回列表，或者您可以点击“确认”手动跳转",
                                type: "success",
                                confirmButtonColor: "#5bc0de",
                                confirmButtonText: "确认",
                                closeOnConfirm: false
                            }, function () {
                                window.location.href = "/cla/info";
                            });
                        } else {
                            Tools.tip(res.msg);
                        }
                    }
                });
            } else {
                Tools.tip("请完善信息后再尝试提交。")
            }
        });
    }());
});