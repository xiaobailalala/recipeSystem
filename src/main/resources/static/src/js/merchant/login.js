$(function(){
    $('.showpwd').click(function (e) { 
        e.preventDefault();
        if($(this).hasClass('icon-normal')){
            $(this).removeClass('icon-normal');
            $(this).addClass('icon-yanjing');
            $('#password').attr('type', 'password');
        }else if($(this).hasClass('icon-yanjing')){
            $(this).removeClass('icon-yanjing');
            $(this).addClass('icon-normal');
            $('#password').attr('type', 'text');
        }
    });
});