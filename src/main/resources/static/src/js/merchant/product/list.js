$(function () {

    var tip_index = 0;
    var flag = true;

    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

    });

    layui.use(['element', 'table', 'laytpl', "laydate", "form"], function () {
        var element = layui.element;
        var table = layui.table;
        var util = layui.util;
        var laydate = layui.laydate;
        var form = layui.form;
        var pagecount;
        var id = $("input[name=userId]").val();
        var pro_id;

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

        $('body').on("click", ".del_reduce", function () {
            if ($('.reduce_div').length === 1) {
                layer.alert("至少有一个满减优惠！！");
            } else {
                $(this).parents(".reduce_div").remove();
            }
        }).on("mouseenter", ".del_reduce", function () {
            $(this).css("color", "#FF0000");
        }).on("mouseleave", ".del_reduce", function () {
            $(this).css("color", "");
        });

        $('body').on("click", ".add_reduce", function () {
            var reduce_div =
                ' <div class="layui-form-item reduce_div">\n' +
                '            <div class="layui-inline">\n' +
                '                <span style="position: absolute;display: inline-block;width: 80px;left: 0;line-height: 38px;padding-left: 10px;">消费满：</span>\n' +
                '                <input type="text" name="fullMoney" placeholder="请填写金额" class="layui-input" style="padding-left: 60px;" required lay-verify="price">\n' +
                '            </div>\n' +
                '            <div class="layui-inline">\n' +
                '                <span style="position: absolute;display: inline-block;width: 30px;left: 0;line-height: 38px;padding-left: 10px;">减：</span>\n' +
                '                <input type="text" name="reduceMoney" placeholder="请填写金额" class="layui-input"style="padding-left: 32px;width: 115px;" required lay-verify="price">\n' +
                '            </div>\n' +
                '            <div class="layui-inline" style="border: 1px solid #e6e6e6; border-radius: 2px;">\n' +
                '                <i class="layui-icon layui-icon-delete del_reduce" style="line-height: 38px;width: 38px;display: inline-block;text-align: center;border-right: 1px solid #e6e6e6;float: left;"></i>\n' +
                '                <i class="layui-icon layui-icon-add-circle-fine add_reduce" style="line-height: 38px;width: 38px;display: inline-block;text-align: center;float: left;"></i>\n' +
                '            </div>\n' +
                '        </div>';
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
        table.render({
            elem: '#produceAllList',
            toolbar: '#products-control',
            page: true,
            url: "/merchant/merchantProduct/list/" + id,
            text: {
                error: "现在还没有任何商品哦~快去添加吧！"
            },
            title: "商品目录",
            parseDate: function (res) {
                return {
                    "item": res.item
                }
            },
            response: {
                statusCode: 200,
                dataName: 'item'
            },
            cols: [
                [{
                    fixed: 'left',
                    type: 'checkbox',
                    style: 'height:120px;'
                },
                    //     {
                    //     field: 'fid',
                    //     title: 'ID',
                    //     align: 'center',
                    //     width: 80,
                    //     style: 'height:120px;',
                    //     templet: function (d) {
                    //         return "<span>"+d.merchantProduct.fid+"</span>";
                    //     }
                    // },
                    {
                        field: 'fcover',
                        title: '商品图片',
                        align: 'center',
                        width: 120,
                        templet: function (d) {
                            return "<img src=" + Tools.fileServerPath + d.merchantProduct.fcover + " style=width:100px;height:100px;>"
                        },
                        style: 'height:120px;'
                    }, {
                    field: 'd.merchantProduct.fname',
                    title: '标题',
                    align: 'center',
                    minWidth: 150,
                    style: 'height:120px;',
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fname + "</span>";
                    }
                }, {
                    field: 'fprice',
                    title: '价格',
                    align: 'center',
                    width: 80,
                    sort: true,
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.merchantProductMarques[0].fprice + "</span>";
                    },
                    style: 'height:120px;'
                }, {
                    field: 'frepository',
                    title: '库存',
                    align: 'center',
                    width: 80,
                    sort: true,
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.merchantProductMarques[0].frepository + "</span>";
                    },
                    style: 'height:120px;'
                }, {
                    field: 'fstate',
                    title: '商品状态',
                    align: 'center',
                    width: 100,
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fstate + "</span>";
                    }
                }, {
                    field: 'fgood',
                    title: '获赞数',
                    align: 'center',
                    width: 80,
                    sort: true,
                    style: 'height:120px;',
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fgood + "</span>";
                    }
                }, {
                    field: 'fcategory',
                    title: '商品分类',
                    align: 'center',
                    width: 110,
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fcategory + "</span>";
                    }
                }, {
                    field: 'fsales',
                    title: '商品销售量',
                    align: 'center',
                    width: 100,
                    style: 'height:120px;',
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fsales + "</span>";
                    }
                }, {
                    field: 'freview',
                    title: '商品审核状态',
                    align: 'center',
                    width: 100,
                    style: 'height:120px;',
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.freview + "</span>";
                    }
                }, {
                    field: 'control',
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#product-control',
                    width: 250,
                    style: 'height:120px;text-align:center;'
                }
                ]
            ],
            done: function (res, curr, count) {
                //取消layui表格默认样式
                console.log(res);
                console.log(count);
                $("td[data-field='fcover']").each(function () {
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });
                $("td[data-field='control']").each(function () {
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });

                $(document).on('mouseenter', '.shelve', function () {
                    tip_index = layer.tips('<p style="color:#000000;">上架</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.shelve', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.unshelve', function () {
                    tip_index = layer.tips('<p style="color:#000000;">下架</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.unshelve', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.delete', function () {
                    tip_index = layer.tips('<p style="color:#000000;">删除</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.delete', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.edit', function () {
                    tip_index = layer.tips('<p style="color:#000000;">编辑</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.edit', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.active', function () {
                    tip_index = layer.tips('<p style="color:#000000;">活动</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.active', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.more-shelve', function () {
                    tip_index = layer.tips('<p style="color:#000000;">批量上架所选商品</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.more-shelve', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.more-unshelve', function () {
                    tip_index = layer.tips('<p style="color:#000000;">批量下架所选商品</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.more-unshelve', function () {
                    layer.close(tip_index);
                });

                $(document).on('mouseenter', '.more-delete', function () {
                    tip_index = layer.tips('<p style="color:#000000;">批量删除所选商品</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.more-delete', function () {
                    layer.close(tip_index);
                });
            }
        });
        table.on('tool(produceList_table)', function (obj) {
            var data = obj.data;
            var event = obj.event;
            switch (event) {
                case 'shelve':
                    console.log('shelve');
                    console.log(data.fid);
                    $.ajax({
                        url: "/merchant/merchantProduct/update/" + data.merchantProduct.fid + "/shelve",
                        type: "PUT",
                        data:{
                            _method:"PUT"
                        },
                        success: function (res) {
                            if (res.code === 200) {
                                layer.msg("商品上架成功");
                                table.reload('produceAllList');
                            }
                        }
                    });
                    break;
                case 'unshelve':
                    console.log('unshelve');
                    $.ajax({
                        url: "/merchant/merchantProduct/update/" + data.merchantProduct.fid + "/unshelve",
                        type: "PUT",
                        data:{
                            _method:"PUT"
                        },
                        success: function (res) {
                            if (res.code === 200) {
                                layer.msg("商品下架成功");
                                table.reload('produceAllList');
                            }
                        }
                    });
                    break;
                case 'delete':
                    layer.confirm('你确定要删除这件商品嘛？', {
                        btn: ['确定', '取消'],
                        btn1: function (index, layero) {
                            $.ajax({
                                url: "/merchant/merchantProduct/delete/" + data.merchantProduct.fid,
                                type: "DELETE",
                                data:{
                                    _method:"DELETE"
                                },
                                success: function (res) {
                                    if (res.code === 200) {
                                        layer.msg("删除商品成功");
                                        table.reload('produceAllList');
                                    }
                                }
                            });
                        }
                    });
                    break;
                case 'edit':
                    location.href = "/merchant/merchantProduct/editor/" + data.merchantProduct.fid;
                    break;
                case 'active':
                    var LAY_active = layer.open({
                        type: 1,
                        title: "活动类型选择",
                        closeBtn: false,
                        area: '500px;',
                        shade: 0.8,
                        shadeClose: true,
                        id: 'LAY_active',
                        resize: false,
                        btn: ['限时折扣', '满减优惠'],
                        btn1: function (index, layero) {
                            if (data.merchantProduct.fdiscount !== "没有活动") {
                                layer.close(LAY_active);
                                layer.msg("该商品已存在限时折扣活动！");
                            } else {
                                layer.close(LAY_active);
                                layer.open({
                                    type: 1,
                                    title: "添加限时折扣活动",
                                    shadeClose: true,
                                    scrollbar: false,
                                    skin: 'layui-layer-rim', //加上边框
                                    area: ['420px', '620px'], //宽高
                                    content: $("#product_discounts"),
                                    success: function (layero, index) {
                                        pro_id = data.merchantProduct.fid;
                                        var img = layero.find("img");
                                        var name = layero.find("#product_name");
                                        var price = layero.find("#product_price");
                                        var marque_len = data.merchantProduct.merchantProductMarques.length;
                                        img.attr("src", Tools.fileServerPath + data.merchantProduct.fcover);
                                        name.html(data.merchantProduct.fname);
                                        if (data.merchantProduct.merchantProductMarques[0].fprice === data.merchantProduct.merchantProductMarques[marque_len - 1].fprice) {
                                            price.html("￥" + data.merchantProduct.merchantProductMarques[0].fprice);
                                        } else {
                                            price.html("￥" + data.merchantProduct.merchantProductMarques[0].fprice + " ~ " + data.merchantProduct.merchantProductMarques[marque_len - 1].fprice);
                                        }
                                    }
                                });
                            }
                        },
                        btn2: function (index, layero) {
                            if (data.merchantProduct.freduction !== "没有活动") {
                                layer.close(LAY_active);
                                layer.msg("该商品已存在满减优惠活动！");
                            } else {
                                layer.close(LAY_active);
                                layer.open({
                                    type: 1,
                                    title: "添加满减优惠活动",
                                    shadeClose: true,
                                    scrollbar: false,
                                    skin: 'layui-layer-rim', //加上边框
                                    area: ['520px', '600px'], //宽高
                                    content: $("#product_reduction"),
                                    success: function (layero, index) {
                                        pro_id = data.merchantProduct.fid;
                                    }
                                });
                            }
                        },
                        btnAlign: 'c',
                        content: '<div style="padding: 50px 10px 50px 10px;background-color: #e2e2e2;color: #000000;height: 200px;"><h3 style="margin-bottom: 25px;">一个好的活动可以吸引更多的顾客来购买您的商品！</h3><span style="padding: 15px;">现为您提供如下两个活动供您选择</span></div>',
                        success: function (layero) {
                            var btn = layero.find('.layui-layer-btn');
                            btn.find('.layui-layer-btn0').css("background-color", "#FFB800").css("border", "none").css("color", "#ffffff");
                            btn.find('.layui-layer-btn1').css("background-color", "#FFB800").css("border", "none").css("color", "#ffffff");
                        }
                    });
                    break;
            }
        });
        table.on('toolbar(produceList_table)', function (obj) {
            var checkdata = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'more-shelve':
                    if (checkdata.data.length === 0) {
                        layer.msg("请先选择数据!!!");
                    } else {
                        $.each(checkdata.data, function (index, item) {
                            $.ajax({
                                url: "/merchant/merchantProduct/update/" + item.merchantProduct.fid + "/shelve",
                                type: "PUT",
                                success: function (res) {
                                    if (res.code === 200) {
                                    }
                                }
                            });
                        });
                        layer.msg("商品全部上架成功");
                        table.reload('produceAllList');
                    }
                    break;
                case 'more-unshelve':
                    if (checkdata.data.length === 0) {
                        layer.msg("请先选择数据!!!");
                    } else {
                        $.each(checkdata.data, function (index, item) {
                            console.log(item.merchantProduct.fid)
                            $.ajax({
                                url: "/merchant/merchantProduct/update/" + item.merchantProduct.fid + "/unshelve",
                                type: "PUT",
                                success: function (res) {
                                    if (res.code === 200) {
                                    }
                                }
                            });
                        });
                        layer.msg("商品下架成功");
                        table.reload('produceAllList');
                    }
                    break;
                case 'more-delete':
                    if (checkdata.data.length === 0) {
                        layer.msg("请先选择数据!!!");
                    } else {
                        layer.confirm('你确定要删除这件商品嘛？', {
                            btn: ['确定', '取消'],
                            btn1: function (index, layero) {
                                $.each(checkdata.data, function (index, item) {
                                    $.ajax({
                                        url: "/merchant/merchantProduct/delete/" + item.merchantProduct.fid,
                                        type: "DELETE",
                                        success: function (res) {
                                            if (res.code === 200) {
                                            }
                                        }
                                    });
                                });
                                layer.msg("删除商品成功");
                                table.reload('produceAllList');
                            }
                        });
                    }
                    break;
            }
        });

        form.on("submit(upload_discount)", function (data) {
            var name = $("input[name=fName]").val();
            var startTime = $("input[name=fStartTime]").val();
            var endTime = $("input[name=fEndTime]").val();
            var discount = $("input[name=fDiscount]").val();
            var startDate = new Date(startTime);
            var endDate = new Date(endTime);
            if (name === "" || startTime === "" || endTime === "" || discount === "") {
                layer.alert("请完善活动详情");
            } else {
                if (startDate.getTime() >= endDate.getTime()) {
                    layer.alert("结束日期必须大于开始日期");
                } else {
                    $.ajax({
                        url: "/merchant/productActiveDiscount/uploadProductActiveDiscount/" + pro_id + "/" + id,
                        type: "POST",
                        data: data.field,
                        success: res => {
                            if (res.code === 200) {
                                layer.msg("商品活动添加成功！");
                                table.reload('produceAllList');
                                layer.closeAll();
                            }
                        }
                    });
                }
            }
        });

        form.on("submit(upload_reduction)", function (data) {
            var name = $(this).parents("#reduction_form").find("input[name=fName]").val();
            var startTime = $(this).parents("#reduction_form").find("input[name=fStartTime]").val();
            var endTime = $(this).parents("#reduction_form").find("input[name=fEndTime]").val();
            console.log(name)
            console.log(startTime)
            console.log(endTime)
            var startDate = new Date(startTime);
            var endDate = new Date(endTime);
            if (name === "" || startTime === "" || endTime === "") {
                layer.alert("请完善活动详情");
            } else {
                if (startDate.getTime() >= endDate.getTime()) {
                    layer.alert("结束日期必须大于开始日期");
                } else {
                    console.log(pro_id)
                    console.log(id)
                    var fullmoney = [], reducemoney = [];
                    var form = document.getElementById("reduction_form");
                    var formdata = new FormData(form);
                    $(".reduce_div").each(function (index, element) {
                        fullmoney.push($(this).find("input[name=fullMoney]").val());
                        reducemoney.push($(this).find("input[name=reduceMoney]").val());
                    });
                    formdata.append("fullmoney",fullmoney);
                    formdata.append("reducemoney",reducemoney);
                    $.ajax({
                        url: "/merchant/productActiveReduction/uploadProductActiveReduction/" + pro_id + "/" + id,
                        type: "POST",
                        data: formdata,
                        contentType:false,
                        processData:false,
                        success: res => {
                            if (res.code === 200) {
                                layer.msg("商品活动添加成功！");
                                table.reload('produceAllList');
                            } else {
                                layer.msg("系统出错")
                            }
                        }
                    });
                }
            }
        })

        form.verify({
            price: [
                /^[1-9]\d*|0$/,
                '只能为正整数'
            ]
        });
    });
});