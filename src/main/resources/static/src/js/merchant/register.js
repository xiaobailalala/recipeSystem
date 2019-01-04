$(function () {
    var verify_code;
    // 从JSON文件中获取省市区信息
    $.getJSON("/src/res/merchant/address.json",
        function (jasondata) {
            jasondata.forEach(element => {
                var province = element.name;
                var op = $('<option/>');
                op.html(province);
                $('#province').append(op);
            })
            ;
            layui.use('form', function () {
                var form = layui.form;
                form.on('select(provincepick)', function (data) {
                    $('#city>option').remove();
                    $('#city').append('<option value="">请选择城市</option>');
                    $('#area>option').remove();
                    $('#area').append('<option value="">请选择地区</option>');
                    jasondata.forEach(element => {
                        if (data.value == element.name
                        ) {
                            element.city.forEach(element => {
                                var city = element.name;
                                // console.log(city);
                                var oc = $('<option/>');
                                oc.html(city);
                                $('#city').append(oc);
                            })
                            ;
                        }
                    })
                    ;
                    form.render();
                });
                form.on('select(citypick)', function (data) {
                    $('#area>option').remove();
                    $('#area').append('<option value="">请选择地区</option>');
                    jasondata.forEach(element => {
                        element.city.forEach(element => {
                            if (data.value == element.name
                            ) {
                                element.area.forEach(element => {
                                    var area = element;
                                    var oa = $('<option/>');
                                    oa.html(area);
                                    $('#area').append(oa);
                                })
                                ;
                            }
                        })
                        ;
                    })
                    ;
                    form.render();
                });
            });
        }
    );

    layui.use(['form', 'laydate', 'element'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var element = layui.element;
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1; //获取当前月
        var date = now.getDate();     //获取当前日
        var h = now.getHours();       //获取当前小时数(0-23)
        var m = now.getMinutes();     //获取当前分钟数(0-59)
        var s = now.getSeconds();
        var codeTime = 60;
        var interval_id;

        //设置发送验证码按钮倒计时时间
        function setCodeTime(){
            if (codeTime === 0) {
                clearInterval(interval_id);
                $('#send_code').removeClass("layui-btn-disabled")
                    .addClass("layui-btn-primary").html("重新发送验证码");
                codeTime = 60;
                verify_code  = "aaaaaa";
                // return false;
            }else{
                codeTime--;
                $('#send_code').html(codeTime + "秒后可重新获取");
            }
        }

        //发送验证码
        $('#send_code').click(function (e) {
            e.preventDefault();
            var phoneNum = $('input[name=fAccount]').val();
            if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(phoneNum)){
                layer.alert("请输入正确的手机号");
            }else{
                $(this).removeClass("layui-btn-primary").addClass("layui-btn-disabled");
                interval_id = setInterval(setCodeTime, 1000);
                $.ajax({
                    url: "/merchantCommon/verifyCode",
                    type: "POST",
                    data: {
                        phoneNumber: phoneNum
                    },
                    success: res => {
                        verify_code = res.code;
                    },
                    error: err => {
                        layer.alert("发送验证码失败，请重试！");
                    }
                });
            }
        });

        function p(s) {
            return s < 10 ? '0' + s : s;
        }

        var nowDate = year + '-' + p(month) + "-" + p(date) + " " + p(h) + ':' + p(m) + ":" + p(s);
        console.log(nowDate);
        laydate.render({
            elem: '#birth',
            theme: '#FFB800',
            min: '1900-1-1 00:00:00',
            max: year + '-' + p(month) + "-" + p(date) + " " + p(h) + ':' + p(m) + ":" + p(s),
            done: function (value, date, endDate) {
                // console.log(value); //得到日期生成的值，如：2017-08-18
                // console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                // console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
            }
        });

        form.on('submit(login)', function (data) {
            // layer.msg(JSON.stringify(data.field));
            if ($('#phone_number').val() === verify_code) {
                $.ajax({
                    url: "/merchant/merchantUser/register",
                    type: "POST",
                    data: data.field,
                    success: function (res) {
                        if (res.code === 200) {
                            layer.msg("注册成功");
                            location.href = "/merchantCommon/login";
                        }
                        else {
                            var msg = res.msg;
                            layer.msg(msg);
                            // Tools.tip(res.msg)
                        }
                    }
                });
            } else {
                layer.alert("验证码不正确");
            }
            return false;
        });

        form.verify({
            account: function (value, item) {
                if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value)) {
                    return '请输入正确的手机号';
                }
            }
            // account: function (value, item) {
            //     if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value)) {
            //         if (!/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/.test(value)) {
            //             return '请输入正确的邮箱或手机号';
            //         }
            //     }
            // }
            , password: function (value, item) {
                //密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
                //123456Y&y123
                if (!/^(?![A-Za-z]+$)(?![A-Z\d]+$)(?![A-Z\W]+$)(?![a-z\d]+$)(?![a-z\\W]+$)(?![\d\W]+$)\S{6,12}$/.test(value)) {
                    return '密码6-12位，数字,大写字母,小写字母,特殊符,至少其中三种组成密码';
                }
            }
            , repassword: function (value, item) {
                if ($('#repassword').val() != $('#password').val()) {
                    return '密码不匹配';
                }
            }
        });
        form.render();
    });
});
