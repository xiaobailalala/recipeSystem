$(function () {

    var tip_index = 0;
    var flag = true;

    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');

    });

    layui.use(['element', 'table'], function () {
        var element = layui.element;
        var table = layui.table;

        var util = layui.util;

        //执行
        util.fixbar({
            bar1: true,
            click: function (type) {
                console.log(type);
                if (type === 'bar1') {
                    alert('点击了bar1')
                }
            }
        })

        table.render({
            elem: '#produceAllList',
            toolbar: '#products-control',
            page: true,
            cols: [
                [{
                    field: 'id',
                    title: 'ID',
                    align: 'center',
                    width: 80,
                },
                {
                    field: 'f_cover',
                    title: '商品图片',
                    align: 'center',
                    width: 120,
                    templet: '#showimg'
                },
                {
                    field: 'f_name',
                    title: '标题',
                    align: 'center',
                    minWidth: 150
                },
                {
                    field: 'f_price',
                    title: '价格',
                    align: 'center',
                    width: 100,
                    sort: true
                },
                {
                    field: 'f_repertory',
                    title: '库存',
                    align: 'center',
                    width: 80,
                    sort: true
                },
                {
                    field: 'f_state',
                    title: '商品状态',
                    align: 'center',
                    width: 100
                },
                {
                    field: 'f_good',
                    title: '获赞数',
                    align: 'center',
                    width: 80,
                    sort: true
                }, {
                    field: 'f_category',
                    title: '商品分类',
                    align: 'center',
                    width: 110
                }, {
                    field: 'f_sales',
                    title: '商品销售量',
                    align: 'center',
                    width: 100
                }, {
                    field: 'control',
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#product-control',
                    width: 180,
                    style: 'height:120px;text-align:center;'
                }
                ]
            ],
            data: [{
                "id": "10001",
                "f_cover": "杜甫",
                "f_name": "销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中销售中",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "9999.00",
                "f_good": "116",
                "f_category": "厨具",
                "f_sales": "108",
                "joinTime": "2016-10-14"
            }, {
                "id": "10002",
                "f_cover": "李白",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "12",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10003",
                "f_cover": "王勃",
                "f_name": "xianxin@layui.comxianxin@layui.comxianxin@layui.comxianxin@layui.comxianxin@layui.comxianxin@layui.comxianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "65",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10004",
                "f_cover": "贤心",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "666",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10005",
                "f_cover": "贤心",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "86",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10006",
                "f_cover": "贤心",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "12",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10007",
                "f_cover": "贤心",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "16",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }, {
                "id": "10008",
                "f_cover": "贤心",
                "f_name": "xianxin@layui.com",
                "f_repertory": "11",
                "f_state": "销售中",
                "f_price": "80",
                "f_good": "106",
                "f_category": "厨具",
                "f_sales": "106",
                "joinTime": "2016-10-14"
            }],
            done: function () {
                $("td[data-field='f_cover']").each(function () {
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
    });
});