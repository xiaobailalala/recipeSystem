$(function () {
    layui.use(['element', 'carousel'], function () {
        var element = layui.element;
        var carousel = layui.carousel;
        carousel.render({
            elem: '#produce-carousel'
            , width: '1015px' //设置容器宽度
            , arrow: 'hover' //始终显示箭头
            , indicator: 'inside'
            , interval: 800
            // ,anim: 'updown' //切换动画方式
        });
    });
    var _id = $("input[name = userId]").val();
    $.ajax({
        url: "/merchantMob/getIndexData/" + _id,
        type: "GET",
        data: {},
        success: function (res) {
            if (res.code === 200) {
                console.log(res.data)
                $("#money").html(res.data.revenue);
                $("#productCount").html(res.data.productCount);
                $("#orderCount").html(res.data.orderCount);
                $("#userCount").html(res.data.userCount);
            }
        }
    })

    $.ajax({
        url: "/merchant/merchantProduct/getFourProduct/" + _id,
        type: "GET",
        data: {},
        success: function (res) {
            console.log(res)
            for (let i = 0; i < res.data.length; i++) {
                $("#pro_img_" + (i + 1)).attr('src', Tools.fileServerPath + res.data[i].fcover)
                $("#pro_name_" + (i + 1)).text(res.data[i].fname)
            }

        }
    })
});