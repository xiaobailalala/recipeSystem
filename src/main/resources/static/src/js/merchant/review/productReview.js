(function ($, window) {
    $(function () {
        let stompClient = null;
        const socket = new SockJS('/endpoint-websocket-webClient');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/merchantChat/sendFireNumber', function (result) {
            });
        });
        $("#test").click(function () {
            stompClient.send("/merchantSwitch/toMerchantSwitch", {}, JSON.stringify({"uid": 123, "rid": 456}));
        });
        $(".receive").click(function () {
            changeState($(this).data("id"), $(this).data("state"));
        });
        $(".refuse").click(function () {
            changeState($(this).data("id"), $(this).data("state"));
        });

        function changeState(id, state) {
            $.ajax({
                url: "/manage/adminMerchant/changeState",
                type: "POST",
                data: {
                    id: id,
                    state: state
                },
                success: function () {
                    if (state === '审核成功') {
                        $(".state" + id).attr("class", "label label-success state" + id).text(state);
                    } else {
                        $(".state" + id).attr("class", "label label-danger state" + id).text(state);
                    }
                }
            });
        }
    });
})(jQuery, window);