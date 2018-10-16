$(function(){
    (function Delete(){
        $('body').on('click','.deleteInfo',function(){
            var id=$(this).data("id");
            swal({
                title: "确定删除吗？",
                text: "你将无法恢复该职业信息",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定删除",
                closeOnConfirm: false
            },function(){
                var formData=new FormData();
                formData.append("_method","delete");
                $.ajax({
                    url:"/profession/info/"+id,
                    type:"post",
                    processData:false,
                    contentType:false,
                    data:formData,
                    success:function(res){
                        if (res.code===200){
                            swal({
                                title: "删除成功",
                                text: "系统将在3秒后刷新页面，或者可以点击“确认”手动刷新",
                                type: "success",
                                confirmButtonColor: "#5cb85c",
                                confirmButtonText: "确认",
                                closeOnConfirm: false
                            },function(){
                                location.reload();
                            });
                            setTimeout(function(){
                                location.reload();
                            },3000);
                        } else{
                            Tools.tip(res.msg);
                        }
                    }
                });
            });
        });
    }());
    var stompClient = null;
    (function SensorsMonitor(){
        var socket = new SockJS('/endpoint-websocket-webClient');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/sensorData/sendFireNumber', function (result) {
                var objArr = JSON.parse(result.body);
                $(".fireMonitor").each(function(){
                    var id = $(this).data("id");
                    var isExist = objArr.find(({uid}) => id === uid);
                    if (isExist){
                        if ($(this).hasClass("label-custom-bg")){
                            $(this).removeClass("label-custom-bg");
                        }
                    }else{
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
            stompClient.subscribe('/sensorData/sendSmogNumber', function (result) {
                var objArr = JSON.parse(result.body);
                $(".smogMonitor").each(function(){
                    var id = $(this).data("id");
                    var isExist = objArr.find(({uid}) => id === uid);
                    if (isExist){
                        if ($(this).hasClass("label-custom-bg")){
                            $(this).removeClass("label-custom-bg");
                        }
                    }else{
                        $(this).addClass("label-custom-bg");
                    }
                });
            });
        });
    }());
});