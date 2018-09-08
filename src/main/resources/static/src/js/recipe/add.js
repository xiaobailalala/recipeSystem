$(function () {
    var tipArr = new Array();
    var claArr = "<option value='-1'>一级分类</option>";
    var threeArr = new Array(), twoArr = new Array();
    (function ClaOne() {
        $.ajax({
            url: "/claOne/getAllInfo",
            type: "get",
            success: function (res) {
                res.data.forEach(function (item, index) {
                    claArr += "<option value='" + item.fId + "'>" + item.fName + "</option>";
                });
            }
        });
    }());
    (function Operation() {
        $('#refresh').click(function () {
            $.ajax({
                url: "/recipe/add/refresh",
                type: "get",
                success: function (res) {
                    var tips = '';
                    res.data.tips.forEach(function (item, index) {
                        tips += '<span style="margin-bottom: 8px;display: inline-block;">\n' +
                            '       <button type="button" data-style="' + item.fStyle + '" data-id="' + item.fId + '" class="' + item.fStyle + ' btn btn-sm tipsBtn">' + item.fName + '</button>&emsp;\n' +
                            '  </span>';
                    });
                    $('#tipsCont').html(tips);
                }
            });
        });
        $('body').on("click", ".tipsBtn", function () {
            var id = $(this).data("id");
            var content = $(this).text();
            var style = $(this).data("style");
            var btn = '<span><button class="' + style + ' btn-sm btn selectedTips" data-id="' + id + '" type="button" style="margin-bottom: 5px;">' + content + '\n' +
                '           <span class="badge badge-warning btn-warning btn-icon deleteTips">\n' +
                '                <i class="fa fa-minus"></i>\n' +
                '           </span>\n' +
                '    </button>&emsp;</span>';
            $('#contTitle').remove();
            if ($('.deleteTips').length >= 2) {
                Tools.tip("最多可选两项标签");
            } else if (tipArr.indexOf(id) !== -1) {
                Tools.tip("该标签已选，请勿重复添加");
            } else {
                tipArr.push(id);
                $('#tipsSelectedCont').append(btn);
            }
        });
        $('body').on('click', '.deleteTips', function () {
            $(this).parent().parent().remove();
            tipArr.splice(tipArr.indexOf($(this).parent().data("id")), 1);
            if ($(".deleteTips").length === 0) {
                $('#tipsSelectedCont').append('<span id="contTitle">（无）最多可选两项</span>');
            }
        });
        $('#addnewTip').click(function () {
            var doc = $('#addTips');
            if (doc.hasClass("in")) {
                $(this).attr("class", "btn btn-info btn-rounded btn-icon")
                    .children("i").attr("class", "fa fa-plus");
                doc.collapse("hide");
            } else {
                $(this).attr("class", "btn btn-danger btn-rounded btn-icon")
                    .children("i").attr("class", "fa fa-mail-reply-all");
                doc.collapse("show");
            }
        });
        $('#addTipBtn').click(function () {
            if ($("#newTip").val().length === 0 || $("#newTip").val() === null || $("#newTip").val() === undefined) {
                Tools.tip("标签内容不能为空");
            }
            if ($('.deleteTips').length >= 2) {
                Tools.tip("最多可选两项标签");
            } else {
                $.ajax({
                    url: "/tips/saveInfo",
                    type: "post",
                    data: {
                        fName: $("#newTip").val()
                    },
                    success: function (res) {
                        if (res.code === 200) {
                            tipArr.push(res.data.fId);
                            var btn = '<span><button class="' + res.data.fStyle + ' btn-sm btn selectedTips" data-id="' + res.data.fId + '" type="button" style="margin-bottom: 5px;">' + res.data.fName + '\n' +
                                '           <span class="badge badge-warning btn-warning btn-icon deleteTips">\n' +
                                '                <i class="fa fa-minus"></i>\n' +
                                '           </span>\n' +
                                '    </button>&emsp;</span>';
                            $('#contTitle').remove();
                            $('#tipsSelectedCont').append(btn);
                        } else {
                            Tools.tip(res.msg);
                        }
                    }
                });
            }
        });
        $('#searchTipsBtn').click(function () {
            if ($('#searchTipsText').val().length === 0 || $('#searchTipsText').val() === null || $('#searchTipsText').val() === undefined) {
                Tools.tip("标签内容不能为空");
            } else {
                $.ajax({
                    url: "/tips/info/searchInfo",
                    type: "get",
                    data: {fName: $('#searchTipsText').val()},
                    success: function (res) {
                        if (res.code === 200) {
                            var tips = '';
                            res.data.forEach(function (item, index) {
                                tips += '<span style="margin-bottom: 8px;display: inline-block;">\n' +
                                    '       <button type="button" data-style="' + item.fStyle + '" data-id="' + item.fId + '" class="' + item.fStyle + ' btn btn-sm tipsBtn">' + item.fName + '</button>&emsp;\n' +
                                    '  </span>';
                            });
                            $('#tipsCont').html(tips);
                        } else if (res.code === 501) {
                            $('#tipsCont').html('<span id="contTitle">--' + res.msg + '--</span>');
                        } else {
                            Tools.tip(res.msg);
                        }
                    }
                });
            }
        });
        $('#searchClasBtn').click(function () {
            var doc = '<div class="padding-b-10" style="display: flex;justify-content: space-between;align-items: center;">' +
                '         <select class="form-control oneCont">\n' + claArr +
                '         </select>&nbsp;\n' +
                '         <select class="form-control twoCont">\n' +
                '                <option value="-1">二级分类</option>\n' +
                '         </select>&nbsp;\n' +
                '         <select class="form-control threeCont">\n' +
                '                <option value="-1">三级分类</option>\n' +
                '         </select>&nbsp;\n' +
                '         <button type="button" class="btn btn-success btn-icon btn-rounded deleteClas">\n' +
                '                <i class="fa fa-unlock" style="width: 13.92px;"></i>\n' +
                '         </button>' +
                '    </div>';
            $('#addCla>div').append(doc);
        });
        $("body").on("click", ".deleteClas", function () {
            if ($(this).hasClass("btn-success")){
                if (Number($(this).siblings(".twoCont").val())===-1){
                    Tools.tip("请至少选择一项二级分类");
                }else if (Number($(this).siblings(".threeCont").val())===-1) {
                    if (twoArr.indexOf($(this).siblings(".twoCont").val())===-1){
                        twoArr.push($(this).siblings(".twoCont").val());
                        $(this).removeClass("btn-success").addClass("btn-danger");
                        $(this).siblings(".twoCont").attr("disabled","disabled")
                            .siblings(".oneCont").attr("disabled","disabled")
                            .siblings(".threeCont").attr("disabled","disabled");
                        $(this).children("i").attr("class","fa fa-minus");
                    }else{
                        Tools.tip("该分类已选，请勿重复添加");
                    }
                }else{
                    if (threeArr.indexOf($(this).siblings(".threeCont").val())===-1){
                        threeArr.push($(this).siblings(".threeCont").val());
                        $(this).removeClass("btn-success").addClass("btn-danger");
                        $(this).siblings(".twoCont").attr("disabled","disabled")
                            .siblings(".oneCont").attr("disabled","disabled")
                            .siblings(".threeCont").attr("disabled","disabled");
                        $(this).children("i").attr("class","fa fa-minus");
                    }else{
                        Tools.tip("该分类已选，请勿重复添加");
                    }
                }
            } else{
                if (Number($(this).siblings(".threeCont").val())===-1){
                    var index=twoArr.indexOf($(this).siblings(".twoCont").val());
                    twoArr.splice(index, 1);
                } else {
                    var index=threeArr.indexOf($(this).siblings(".threeCont").val());
                    threeArr.splice(index, 1);
                }
                $(this).parent().remove();
            }
        });
        $('body').on("change", ".oneCont", function () {
            $(this).siblings(".threeCont").html('<option value="-1">三级分类</option>');
            if (Number($(this).val())===-1){
                $(this).siblings(".twoCont").html('<option value="-1">二级分类</option>');
            } else{
                $.ajax({
                    url: "/claTwo/getbyoid/" + $(this).val(),
                    type: "post",
                    success: res => {
                        var content='<option value="-1">二级分类</option>';
                        res.data.forEach(function (item, index) {
                            content+='<option value="' + item.fId + '">' + item.fName + '</option>';
                        });
                        $(this).siblings(".twoCont").html(content);
                    }
                });
            }
        });
        $("body").on("change", ".twoCont", function(){
            if (Number($(this).val())===-1){
                $(this).siblings(".threeCont").html('<option value="-1">三级分类</option>');
            } else{
                $.ajax({
                    url:"/cla/getByTid/"+$(this).val(),
                    type:"post",
                    success: res=>{
                        var content='<option value="-1">三级分类</option>';
                        res.data.forEach(function(item,index){
                            content+='<option value="' + item.fId + '">' + item.fName + '</option>';
                        });
                        $(this).siblings(".threeCont").html(content);
                    }
                });
            }
        });
    }());
    (function Add() {
        $('#browser').click(function () {
            $('#fCover').click();
        });
        $('#fCover').change(function () {
            if (this.files[0]) {
                onImg(this.files[0]);
            }
        });

        function onImg(data) {
            var fs = new FileReader();
            fs.readAsDataURL(data);
            fs.onload = function () {
                var image = '<img src="' + fs.result + '" style="height: 100%;width: 100%;" alt="cover">';
                $('#imgCont').html(image);
            }
        }
    }());
});