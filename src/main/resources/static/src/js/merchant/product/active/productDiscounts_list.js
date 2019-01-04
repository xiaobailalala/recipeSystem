$(function () {
    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

    });
    layui.use(["table", "element", "layer", "util"], function () {
        var table = layui.table,
            element = layui.element,
            layer = layui.layer,
            util = layui.util;
        var userId = $("input[name=userId]").val();
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


        table.render({
            elem: '#productActiveList',
            toolbar: '#products-control',
            page: true,
            url: "/merchant/productActiveDiscount/getAllDiscountProductByMid/" + userId,
            title: "限时折扣商品",
            response: {
                statusCode: 200
            },
            cols: [[
                {
                    fixed: 'left', type: 'checkbox', style: 'height:120px;'
                },
                {
                    field: 'pro_cover',title: '商品图片', align: 'center', style: 'height:120px;',
                    templet: function (d) {
                        return "<img src=" + Tools.fileServerPath + d.merchantProduct.fcover + " style=width:100px;height:100px;>"
                    }
                },
                {
                    field: 'pro_name',title: '商品名称', align: 'center', style: 'height:120px;',
                    templet: function (d) {
                        return "<span>" + d.merchantProduct.fname + "</span>";
                    }
                },
                {
                    field: 'fname',title: '活动名称', align: 'center', style: 'height:120px;width:80px;',
                    templet: function (d) {
                        return "<span>"+ d.fname + "</span>";
                    }
                },
                {
                    field: 'ftime',title: '活动时间', align: 'center', style: 'height:120px;',
                    templet: function (d) {
                        return "<span>"+ "开始时间：" + d.fstartTime + "</span>" + "<br><span>"+ "结束时间：" + d.fendTime + "</span>";
                    }
                },
                {
                    field: 'fstatus',title: '活动状态', align: 'center', style: 'height:120px;',
                    templet: function (d) {
                        return "<span>"+ d.fstatus + "</span>";
                    }
                },
                {
                    fixed:'right', field: 'control',title: '操作', align: 'center', style: 'height:120px;',toolbar: '#product-control'
                }
            ]],
            done: function (res, curr, count) {
                //取消layui表格默认样式
                console.log(res);
                console.log(count);
                $("td[data-field='pro_cover']").each(function () {
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });
                $("td[data-field='ftime']").each(function () {
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
                });
                $("td[data-field='control']").each(function () {
                    $(this).find(".layui-table-cell").removeClass('layui-table-cell');
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
                $(document).on('mouseenter', '.more-delete', function () {
                    tip_index = layer.tips('<p style="color:#000000;">批量删除</p>', $(this), {
                        tips: [1, '#FFFFFF'],
                        time: 0
                    });
                }).on('mouseleave', '.more-delete', function () {
                    layer.close(tip_index);
                });
            }
        });

        table.on('tool(productList_table)', function (obj) {
            var data = obj.data;
            switch (obj.event) {
                case 'delete':
                    layer.confirm('你确定要删除这个活动嘛？', {
                        btn: ['确定', '取消'],
                        btn1: function (index, layero) {
                            $.ajax({
                                url: "/merchant/productActiveDiscount/deleteProductActiveReductionById/" + data.fid,
                                type: "POST",
                                data:{
                                    _method:"DELETE"
                                },
                                success: function (res) {
                                    if (res.code === 200) {
                                        layer.msg("删除商品成功");
                                        table.reload('productActiveList');
                                    }
                                }
                            });
                        }
                    });
                    break;
                case 'edit':
                    location.href = "/merchant/productActiveDiscount/editorProductActiveDiscount/" + data.fid;
                    break;
            }
        });
    });
});