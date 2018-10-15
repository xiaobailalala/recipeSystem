$(function(){
    layui.use(['element','carousel'], function(){
        var element = layui.element;
        var carousel = layui.carousel;
        carousel.render({
            elem: '#produce-carousel'
            ,width: '1015px' //设置容器宽度
            ,arrow: 'hover' //始终显示箭头
            ,indicator: 'inside'
            ,interval: 800
            // ,anim: 'updown' //切换动画方式
          });
      });
});