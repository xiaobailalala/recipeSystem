$(function(){
    $('#subform').submit(function(e){
        e.preventDefault();
        if ($('#account').val()!==""&&$('#password').val()!==""){
            var formData=new FormData(this);
            $.ajax({
                url:"/manage/adm/adlogin",
                type:"POST",
                data:formData,
                processData:false,
                contentType:false,
                success:function(res){
                    if (res.code===200){
                        location.href="/manage/adm/admin/index";
                    } else{
                        Tools.tip(res.msg);
                    }
                }
            });
        }else{
            Tools.tip("请完善登陆信息");
        }
    });
    var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
    var theRequest = new Object();
    if ( url.indexOf( "?" ) !== -1 ) {
        var str = url.substr( 1 ); //substr()方法返回从参数值开始到结束的字符串；
        var strs = str.split( "&" );
        for ( var i = 0; i < strs.length; i++ ) {
            theRequest[ strs[ i ].split( "=" )[ 0 ] ] = ( strs[ i ].split( "=" )[ 1 ] );
        }
        if (theRequest.timeout=1){
            swal({
                title: "登录超时",
                text: "请重新登录...",
                type: "warning",
                confirmButtonColor: "#5bc0de",
                confirmButtonText: "确认"
            });
        }
    }

});