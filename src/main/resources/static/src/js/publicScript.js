var Tools={
    tip:tipFn,
    successAddTimeoutTip:successAddTimeoutTip,
    path:window.document.location.pathname.substring(0,window.document.location.pathname.substr(1).indexOf('/')+1),
    myModal:myModal,
    // fileServerPath:"http://192.168.1.108/",
    fileServerPath:"http://47.107.179.70/",
    // fileServerPath:"http://172.20.10.10/",
    body: $("body")
}
function tipFn(msg){
    var doc="#tipModal";
    $("#tip_main").text(msg);
    $(doc).modal({
        backdrop: false
    });
    $(doc).modal('show');
}
function successAddTimeoutTip(res, time){
    if (res.code===200){
        setTimeout(function(){
            window.location.reload();
        },time*1000);
        swal({
            title:"新增成功",
            text:"系统将在"+time+"秒后刷新页面，或者您可以点击“确认”手动刷新",
            type:"success",
            confirmButtonColor:"#5bc0de",
            confirmButtonText:"确认",
            closeOnConfirm:false
        },function(){
            window.location.reload();
        });
    } else{
        Tools.tip(res.msg);
    }
}
/***obj{
 *  	iden:id,
 *  	title:title,
 *  	form:formContainer
 *  	sub:subId,
 *  	dataArr:[data-{key}={value}]
 *  }*/
function myModal(obj){
    var data="";
    if(obj.dataArr!=null){
        obj.dataArr.forEach(function(item,index){
            data+='data-'+item.key+'="'+item.value+'" ';
        });
    }
    var div = '<div class="modal fade" data-id="" id="'+obj.iden+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">                        <div class="modal-dialog" role="document">                        <div class="modal-content">                            <div class="modal-header">                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                            <h4 class="modal-title" id="myModalLabel">'+obj.title+'</h4>                            </div>                            <div class="modal-body">'+obj.form+'</div>                            <div class="modal-footer">                                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>                                <button type="button" class="btn btn-primary" id="'+obj.sub+'" '+data+'>提交</button>                            </div>                        </div>                        </div>                    </div>';
    $('#modal-container').html(div);
    var doc="#"+obj.iden;
    $(doc).modal({
        backdrop: false
    });
    $(doc).modal('show');
}
// document.addEventListener('visibilitychange',function() {
//     if(document.visibilityState=='visible') {
//         location.reload(true);
//     }
// });
// document.addEventListener('webkitvisibilitychange',function() {
//     if(document.webkitVisibilityState=='visible') {
//         location.reload(true);
//     }
// });
// document.addEventListener('mozvisibilitychange',function() {
//     if(document.mozVisibilityState=='visible') {
//         location.reload(true);
//     }
// });
// document.addEventListener('msvisibilitychange',function() {
//     if(document.msVisibilityState=='visible') {
//         location.reload(true);
//     }
// });
$(document).ready(function() {
    $('#example0').DataTable();
});
$(document).ready(function() {
    var table = $('#example').DataTable({
        "columnDefs": [
            { "visible": false, "targets": 2 }
        ],
        "order": [[ 2, 'asc' ]],
        "displayLength": 25,
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;

            api.column(2, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="5">'+group+'</td></tr>'
                    );

                    last = group;
                }
            });
        }
    });

    // Order by the grouping
    $('#example tbody').on( 'click', 'tr.group', function () {
        var currentOrder = table.order()[0];
        if ( currentOrder[0] === 2 && currentOrder[1] === 'asc' ) {
            table.order( [ 2, 'desc' ] ).draw();
        }
        else {
            table.order( [ 2, 'asc' ] ).draw();
        }
    });
} );
$.ajaxSetup({
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    complete: function(XMLHttpRequest,status) {
        var sessionstatus=XMLHttpRequest.getResponseHeader("session-status");
        if(sessionstatus === "timeout" || XMLHttpRequest.status === 401){
            swal.close();
            var top = getTopWinow();
            top.location.href = '/manage/adm/adLogin?timeout=1';
        }
        if(XMLHttpRequest.status === 500){
            swal({
                title:"500",
                text:"系统出错,请重试！",
                type:"warning",
                confirmButtonColor:"#dec85e",
                confirmButtonText:"确认"
            });
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