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
        var proActive_id = $("input[name=activeReduction_id]").val();
        var pro_id;
        var tip_index = 0;
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

        $('body').on("click", ".del_reduce", function () {
            if ($('.reduce_div').length === 1) {
                layer.alert("至少有一个满减优惠！！");
            } else {
                if ($(this).parents(".reduce_div").find("input[name=conditionId]").length > 0) {
                    var fid = $(this).parents(".reduce_div").find("input[name=conditionId]").val();
                    var reduce_div = $(this).parents(".reduce_div");
                    layer.confirm('你确定要删除这个选项嘛？删除后无法恢复！', {
                        btn: ['确定', '取消'],
                        btn1: function (index, layero) {
                            $.ajax({
                                url: "/merchant/productActiveReductionCondition/deleteProductActiveReductionConditionById/" + fid,
                                type: "POST",
                                data:{
                                    _method:"DELETE"
                                },
                                success: function (res) {
                                    if (res.code === 200) {
                                        layer.msg("删除成功");
                                        reduce_div.remove();
                                    }
                                }
                            });
                        }
                    });
                } else {
                    $(this).parents(".reduce_div").remove();
                }
            }
        }).on("mouseenter", ".del_reduce", function () {
            $(this).css("color", "#FF0000");
        }).on("mouseleave", ".del_reduce", function () {
            $(this).css("color", "");
        });
        $('body').on("click", ".add_reduce", function () {
            var reduce_div =
                '<div class="layui-form-item reduce_div">\n' +
                '                <label class="layui-form-label" style="vertical-align: middle;">消费满：</label>\n' +
                '                <div class="layui-input-inline">\n' +
                '                    <input type="text" name="fullMoney" placeholder="请填写金额" class="layui-input" required lay-verify="price">\n' +
                '                </div>\n' +
                '                <label class="layui-form-label" style="vertical-align: middle;">减：</label>\n' +
                '                <div class="layui-input-inline">\n' +
                '                    <input type="text" name="reduceMoney" placeholder="请填写金额" class="layui-input" required lay-verify="price">\n' +
                '                </div>\n' +
                '                <div class="layui-inline" style="border: 1px solid #e6e6e6; border-radius: 2px;">\n' +
                '                    <i class="layui-icon layui-icon-delete del_reduce" style="line-height: 38px;width: 38px;display: inline-block;text-align: center;border-right: 1px solid #e6e6e6;float: left;background-color: #ffffff;"></i>\n' +
                '                    <i class="layui-icon layui-icon-add-circle-fine add_reduce" style="line-height: 38px;width: 38px;display: inline-block;text-align: center;float: left;background-color: #ffffff;"></i>\n' +
                '                </div>\n' +
                '            </div>';
            if ($('.reduce_div').length === 5) {
                layer.alert("最多只能有5个满减优惠！！");
            } else {
                $("#top_this").prepend(reduce_div);
            }
        }).on("mouseenter", ".add_reduce", function () {
            $(this).css("color", "#FF0000");
        }).on("mouseleave", ".add_reduce", function () {
            $(this).css("color", "");
        });

        form.on("submit(upload_reduction)", function () {
            var name = $("input[name=fName]").val();
            var startTime = $("input[name=fStartTime]").val();
            var endTime = $("input[name=fEndTime]").val();
            var startDate = new Date(startTime);
            var endDate = new Date(endTime);
            if (name === "" || startTime === "" || endTime === "") {
                layer.alert("请完善活动详情");
            } else {
                if (endDate.getTime() <= startDate.getTime()) {
                    layer.alert("结束日期必须大于开始日期");
                } else {
                    var newfullmoney = [], newreducemoney = [], fullmoney = [], reducemoney = [], conditionId = [];
                    var form = document.getElementById("reduction_form");
                    var formdata = new FormData(form);
                    formdata.append("_method", "PUT");
                    ($(".reduce_div").has("input[name=conditionId]")).each(function (index, element) {
                        fullmoney.push($(this).find("input[name=fullMoney]").val());
                        reducemoney.push($(this).find("input[name=reduceMoney]").val());
                    });
                    $(".reduce_div:not(:has(input[name=conditionId]))").each(function (index,element) {
                        newfullmoney.push($(this).find("input[name=fullMoney]").val());
                        newreducemoney.push($(this).find("input[name=reduceMoney]").val());
                    });
                    console.log($("input[name=conditionId]").length)
                    $("input[name=conditionId]").each(function (index,element) {
                        conditionId.push($(this).val());
                    });
                    console.log(conditionId)
                    formdata.append("conditionID",conditionId);
                    formdata.append("fullmoney",fullmoney);
                    formdata.append("reducemoney",reducemoney);
                    formdata.append("newfullmoney",newfullmoney);
                    formdata.append("newreducemoney",newreducemoney);
                    $.ajax({
                        url: "/merchant/productActiveReduction/editorProductActiveReductionById/" + proActive_id,
                        type: "POST",
                        data: formdata,
                        contentType: false,
                        processData: false,
                        success: res => {
                            if (res.code === 200) {
                                layer.msg("商品活动添加成功！");
                                location.reload()
                            }
                        }
                    });
                }
            }
        });
        form.verify({
            price: [
                /^[1-9]\d*|0$/,
                '只能为正整数'
            ]
        });
    });
})