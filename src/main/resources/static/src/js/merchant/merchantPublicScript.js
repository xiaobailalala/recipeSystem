var Tools={
    // tip:tipFn,
    // successAddTimeoutTip:successAddTimeoutTip,
    path:window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1),
    // myModal:myModal,
    // fileServerPath:"http://172.20.10.10/",
    fileServerPath:"http://192.168.1.108/",
    body: $("body")
}
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    complete: function(XMLHttpRequest,status) {
        var sessionstatus=XMLHttpRequest.getResponseHeader("session-status");
        if(sessionstatus === "timeout" || XMLHttpRequest.status === 401){
            layer.closeAll();
            var top = getTopWinow();
            top.location.href = '/merchant/merchantUser/login?timeout=1';
        }
        if(XMLHttpRequest.status === 500){
            layer.open({
                title: "500"
                ,content: '系统出错,请重试！'
            });

            // swal({
            //     title:"500",
            //     text:"系统出错,请重试！",
            //     type:"warning",
            //     confirmButtonColor:"#dec85e",
            //     confirmButtonText:"确认"
            // });
        }
    }
});
function getTopWinow(){
    var p = window;
    while(p !== p.parent){
        p = p.parent;
    }
    return p;
}