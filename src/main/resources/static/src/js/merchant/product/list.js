$(function () {

    var tip_index = 0;
    var flag = true;

    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

    });

    layui.use(['element', 'table', 'laytpl'], function () {
        var element = layui.element;
        var table = layui.table;
        var util = layui.util;

        $('#haha').click(function (e) {
            e.preventDefault();
            var tabledata = table.checkStatus('produceAllList');
            console.log(tabledata.data)
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
            url: "/merchant/merchantProduct/list",
            text: {
                error: "现在还没有任何商品哦~快去添加吧！"
            },
            title: "商品目录",
            parseDate: function (res) {
                return {}
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
                }, {
                    field: 'fid',
                    title: 'ID',
                    align: 'center',
                    width: 80,
                    style: 'height:120px;'
                }, {
                    field: 'fcover',
                    title: '商品图片',
                    align: 'center',
                    width: 120,
                    templet: function (d) {
                        return "<img src=" + Tools.fileServerPath + d.fcover + " style=width:100px;height:100px;>"
                    },
                    style: 'height:120px;'
                }, {
                    field: 'fname',
                    title: '标题',
                    align: 'center',
                    minWidth: 150,
                    style: 'height:120px;'
                }, {
                    field: 'd.merchantProductMarques.fprice',
                    title: '价格',
                    align: 'center',
                    width: 80,
                    sort: true,
                    templet: function (d) {
                        return "<span>" + d.merchantProductMarques[0].fprice + "</span>";
                    },
                    style: 'height:120px;'
                }, {
                    field: 'd.merchantProductMarques.frepository',
                    title: '库存',
                    align: 'center',
                    width: 80,
                    sort: true,
                    templet: function (d) {
                        return "<span>" + d.merchantProductMarques[0].frepository + "</span>";
                    },
                    style: 'height:120px;'
                }, {
                    field: 'fstate',
                    title: '商品状态',
                    align: 'center',
                    width: 100
                }, {
                    field: 'fgood',
                    title: '获赞数',
                    align: 'center',
                    width: 80,
                    sort: true,
                    style: 'height:120px;'
                }, {
                    field: 'fcategory',
                    title: '商品分类',
                    align: 'center',
                    width: 110
                }, {
                    field: 'fsales',
                    title: '商品销售量',
                    align: 'center',
                    width: 100,
                    style: 'height:120px;'
                }, {
                    field: 'freview',
                    title: '商品审核状态',
                    align: 'center',
                    width: 100,
                    style: 'height:120px;'
                }, {
                    field: 'control',
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#product-control',
                    width: 200,
                    style: 'height:120px;text-align:center;'
                }
                ]
            ],
            done: function () {
                //取消layui表格默认样式
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
                        url: "/merchant/merchantProduct/update/" + data.fid + "/shelve",
                        type: "PUT",
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
                        url: "/merchant/merchantProduct/update/" + data.fid + "/unshelve",
                        type: "PUT",
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
                                url: "/merchant/merchantProduct/delete/" + data.fid,
                                type: "DELETE",
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
                    console.log('edit');
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
                                url: "/merchant/merchantProduct/update/" + item.fid + "/shelve",
                                type: "PUT",
                                success: function (res) {
                                    if (res.code === 200) {
                                        layer.msg("商品上架成功");
                                        table.reload('produceAllList');
                                    }
                                }
                            });
                        });
                    }
                    break;
                case 'more-unshelve':
                    if (checkdata.data.length === 0) {
                        layer.msg("请先选择数据!!!");
                    } else {
                        $.each(checkdata.data, function (index, item) {
                            $.ajax({
                                url: "/merchant/merchantProduct/update/" + item.fid + "/unshelve",
                                type: "PUT",
                                success: function (res) {
                                    if (res.code === 200) {
                                        layer.msg("商品上架成功");
                                        table.reload('produceAllList');
                                    }
                                }
                            });
                        });
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
                                        url: "/merchant/merchantProduct/delete/" + item.fid,
                                        type: "DELETE",
                                        success: function (res) {
                                            if (res.code === 200) {
                                                layer.msg("删除商品成功");
                                                table.reload('produceAllList');
                                            }
                                        }
                                    });
                                });
                            }
                        });
                    }
                    break;
            }
        })
    });
});