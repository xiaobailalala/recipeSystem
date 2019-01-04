$(function () {
    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

    });
    layui.use(["form", "element", "layer", "util", "laydate"], function () {
        var form = layui.form,
            element = layui.element,
            layer = layui.layer,
            util = layui.util,
            laydate = layui.laydate;
        var userId = $("input[name=userId]").val();
            var proActive_id = $("input[name=activeDiscount_id]").val();
        var tip_index = 0;
        var startTime = "";

        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var hours = date.getHours();
            var minutes = date.getMinutes();
            var seconds = date.getSeconds();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            if (hours >= 0 && hours <= 9) {
                hours = "0" + hours;
            }
            if (minutes >= 0 && minutes <= 9) {
                minutes = "0" + minutes;
            }
            if (seconds >= 0 && seconds <= 9) {
                seconds = "0" + seconds;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + hours + seperator2 + minutes
                + seperator2 + seconds;
            return currentdate;
        }

        function getAfterThirtyMinutesTime() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            //后三十分钟时间
            var minutes = parseInt("30");

            var interTimes = minutes * 60 * 1000;

            var interTimes = parseInt(interTimes);
            date = new Date(Date.parse(startTime) + interTimes);

            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            var hour = date.getHours();
            var minutes = date.getMinutes();
            var seconds = date.getSeconds();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            if (hour >= 0 && hour <= 9) {
                hour = "0" + hour;
            }
            if (minutes >= 0 && minutes <= 9) {
                minutes = "0" + minutes;
            }
            if (seconds >= 0 && seconds <= 9) {
                seconds = "0" + seconds;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + hour + seperator2 + minutes
                + seperator2 + seconds;
            return currentdate;
        }

        //执行
        util.fixbar({
            bar1: true,
            click: function (type) {
                console.log(type);
                if (type === 'bar1') {
                    alert('点击了bar1')
                }
            }
        });
        lay('.start_time').each(function () {
            laydate.render({
                elem: this,
                type: "datetime",
                theme: "#FFB800",
                min: getNowFormatDate()
            });
        });
        lay('.end_time').each(function () {
            laydate.render({
                elem: this,
                type: "datetime",
                theme: "#FFB800"
            });
        });
        $('body').on("click", "#discount_price", function () {
            if ($("#discount").val() === "") {
                layer.alert("请先输入折扣！");
            } else if (parseFloat($("#discount").val()) > 9.9 || parseFloat($("#discount").val()) < 0.1) {
                layer.alert("请输入0.1~9.9的数字！");
                $("#discount").val("");
            } else {
                var discount = $("#discount").val();
                var num = parseFloat(discount);
                var res = "";
                num = num.toFixed(1);
                $("#discount").val(num);
                var price = $("#product_price").text();
                num = num / 10;
                if (price.indexOf("~") !== -1) {
                    var arr = price.split("~");
                    arr[0] = arr[0].slice(1);
                    res = (parseFloat(arr[0]) * num).toFixed(2) + " ~ " + (parseFloat(arr[1]) * num).toFixed(2);
                    $("#discount_price").val(res);
                } else {
                    $("#discount_price").val((parseFloat(price.slice(1)) * num).toFixed(2));
                }
            }
        });
        form.on("submit(upload_discount)", function (data) {
            var name = $("input[name=fName]").val();
            var startTime = $("input[name=fStartTime]").val();
            var endTime = $("input[name=fEndTime]").val();
            var discount = $("input[name=fDiscount]").val();
            var startDate = new Date(startTime);
            var endDate = new Date(endTime);
            if (endDate.getTime() <= startDate.getTime()) {
                layer.alert("结束日期必须大于开始日期");
            } else {
                if (name === "" || startTime === "" || endTime === "" || discount === "") {
                    layer.alert("请完善活动详情");
                } else {
                    var form = document.getElementById("discounts_form");
                    var formdata = new FormData(form);
                    formdata.append("_method", "PUT");
                    $.ajax({
                        url: "/merchant/productActiveDiscount/editorProductActiveDiscount/" + proActive_id,
                        type: "POST",
                        data: formdata,
                        contentType: false,
                        processData: false,
                        success: res => {
                            if (res.code === 200) {
                                layer.msg("商品活动添加成功！");
                            }
                        }
                    });
                }
            }
        });
    });
});