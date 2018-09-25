$(function () {
    var tipArr = new Array();
    var claArr = "<option value='-1'>一级分类</option>";
    var threeArr = new Array(), twoArr = new Array();
    var materialName = new Array(), materialId = new Array(), materialNumber = new Array();

    var name = true, cover = true, introduction = true, fire = false;

    function isSub() {
        if (name && cover && introduction) {
            $('input[type=submit]').removeAttr("disabled");
            return true;
        } else {
            $('input[type=submit]').attr("disabled", "disabled");
            return false;
        }
    }

    (function LoadGetData(){
        var index = window.location.href.lastIndexOf("/");
        var id = window.location.href.substr(index+1, window.location.href.length);
        $.ajax({
            url:"/load/getData/"+id,
            type:"get",
            success:function(res){

            }
        });
    }());

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
            if ($(this).hasClass("btn-success")) {
                if (Number($(this).siblings(".twoCont").val()) === -1) {
                    Tools.tip("请至少选择一项二级分类");
                } else if (Number($(this).siblings(".threeCont").val()) === -1) {
                    if (twoArr.indexOf($(this).siblings(".twoCont").val()) === -1) {
                        twoArr.push($(this).siblings(".twoCont").val());
                        $(this).removeClass("btn-success").addClass("btn-danger");
                        $(this).siblings(".twoCont").attr("disabled", "disabled")
                            .siblings(".oneCont").attr("disabled", "disabled")
                            .siblings(".threeCont").attr("disabled", "disabled");
                        $(this).children("i").attr("class", "fa fa-minus");
                    } else {
                        Tools.tip("该分类已选，请勿重复添加");
                    }
                } else {
                    if (threeArr.indexOf($(this).siblings(".threeCont").val()) === -1) {
                        threeArr.push($(this).siblings(".threeCont").val());
                        $(this).removeClass("btn-success").addClass("btn-danger");
                        $(this).siblings(".twoCont").attr("disabled", "disabled")
                            .siblings(".oneCont").attr("disabled", "disabled")
                            .siblings(".threeCont").attr("disabled", "disabled");
                        $(this).children("i").attr("class", "fa fa-minus");
                    } else {
                        Tools.tip("该分类已选，请勿重复添加");
                    }
                }
            } else {
                if (Number($(this).siblings(".threeCont").val()) === -1) {
                    var index = twoArr.indexOf($(this).siblings(".twoCont").val());
                    twoArr.splice(index, 1);
                } else {
                    var index = threeArr.indexOf($(this).siblings(".threeCont").val());
                    threeArr.splice(index, 1);
                }
                $(this).parent().remove();
            }
        });
        $('body').on("change", ".oneCont", function () {
            $(this).siblings(".threeCont").html('<option value="-1">三级分类</option>');
            if (Number($(this).val()) === -1) {
                $(this).siblings(".twoCont").html('<option value="-1">二级分类</option>');
            } else {
                $.ajax({
                    url: "/claTwo/getbyoid/" + $(this).val(),
                    type: "post",
                    success: res => {
                        var content = '<option value="-1">二级分类</option>';
                        res.data.forEach(function (item, index) {
                            content += '<option value="' + item.fId + '">' + item.fName + '</option>';
                        });
                        $(this).siblings(".twoCont").html(content);
                    }
                });
            }
        });
        $("body").on("change", ".twoCont", function () {
            if (Number($(this).val()) === -1) {
                $(this).siblings(".threeCont").html('<option value="-1">三级分类</option>');
            } else {
                $.ajax({
                    url: "/cla/getByTid/" + $(this).val(),
                    type: "post",
                    success: res => {
                        var content = '<option value="-1">三级分类</option>';
                        res.data.forEach(function (item, index) {
                            content += '<option value="' + item.fId + '">' + item.fName + '</option>';
                        });
                        $(this).siblings(".threeCont").html(content);
                    }
                });
            }
        });
        $('#addMaterialTip').click(function () {
            if ($("#materialName").val().length === 0 || $("#materialName").val() === null || $("#materialName").val() === "" || $("#materialName").val() === undefined ||
                $("#materialNumber").val().length === 0 || $("#materialNumber").val() === null || $("#materialNumber").val() === "" || $("#materialNumber").val() === undefined) {
                Tools.tip("请输入正确的食材名称和剂量/备注内容");
            } else {
                var name = $('#materialName').val();
                if (materialName.indexOf(name) !== -1) {
                    Tools.tip("该食材已添加，请勿重复操作")
                } else {
                    $.ajax({
                        url: "/material/getByName",
                        type: "get",
                        data: {fName: $("#materialName").val()},
                        success: function (res) {
                            var name, id;
                            if (res.code === 200) {
                                name = res.data.fName;
                                id = res.data.fId;
                            } else {
                                name = $("#materialName").val();
                                id = 0;
                            }
                            materialName.push(name);
                            materialId.push(id);
                            materialNumber.push($("#materialNumber").val());
                            var content = '<tr>\n' +
                                '              <td>' + name + '</td>\n' +
                                '              <td>' + $("#materialNumber").val() + '</td>\n' +
                                '              <td>\n' +
                                '                 <button type="button" class="btn btn-icon btn-rounded btn-danger deleteMaterial">\n' +
                                '                        <i class="fa fa-minus"></i>\n' +
                                '                 </button>\n' +
                                '              </td>\n' +
                                '          </tr>';
                            $('#materialCont').append(content);
                            $("#materialName").val("");
                            $("#materialNumber").val("");
                        }
                    });
                }
            }
        });
        $("body").on("click", ".deleteMaterial", function () {
            $(this).parent().parent().remove();
            var name = $(this).parent().siblings("td:nth-of-type(1)").text();
            var index = materialName.indexOf(name);
            materialName.splice(index, 1);
            materialId.splice(index, 1);
            materialNumber.splice(index, 1);
        });
        $('#fireMonitor').change(function () {
            if ($(this).is(':checked')) {
                $('#fireMonitorCont').collapse("show");
            } else {
                $('#fireMonitorCont').collapse("hide");
            }
        });
        $('#addProcess').click(function () {
            var count = $("#processCont>div").length;
            var div = '<div class="panel panel-dark collapse">\n' +
                '        <div class="panel-title">\n' +
                '            <span>步骤 ' + (count + 1) + '</span>、\n' +
                '            <ul class="panel-tools">\n' +
                '               <li>\n' +
                '                  <button class="btn btn-danger btn-rounded btn-icon deleteProcess" type="button">\n' +
                '                         <i class="fa fa-minus"></i>\n' +
                '                  </button>\n' +
                '               </li>\n' +
                '            </ul>\n' +
                '       </div>\n' +
                '       <div class="panel-body">\n' +
                '           <div class="col-sm-6">\n' +
                '               <label>步骤内容（尽量详细）</label>\n' +
                '                     <textarea class="form-control processContent" name="recipeContent" cols="30" style="height: 160px;" placeholder="Please Input Process..."></textarea>\n' +
                '           </div>\n' +
                '           <div class="col-sm-4">\n' +
                '               <label>步骤配图（最多只能添加一张图解）</label>\n' +
                '                     <div>\n' +
                '                         <input type="file" style="display: none;" name="processImg">\n' +
                '                         <div class="selectProcessImg text-center" style="background-color: #eeeeee;cursor:pointer;border-radius: 5px;height: 150px;box-sizing: content-box;padding: 5px;">\n' +
                '                             <span style="display: flex;color: #999;font-weight: bold;\n' +
                '                                  align-items: center;justify-content: center;width: 100%;height: 100%;font-size: 50px;">\n' +
                '                                  <i class="fa fa-plus"></i>\n' +
                '                             </span>\n' +
                '                         </div>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '               <div class="col-sm-2">\n' +
                '                   <label>至下一步骤所需时间</label>\n' +
                '                   <input type="number" name="recipeTime" class="form-control processTime" placeholder="单位/s" min="0">' +
                '               </div>\n' +
                '           </div>\n' +
                '      </div>';
            $('#processCont').append(div);
            $("#processCont>div:last-child").collapse("show");
        });
        $("body").on("click", ".deleteProcess", function () {
            $(this).parent().parent().parent().parent().remove();
            $("#processCont>div").each(function (index, item) {
                $(this).children(".panel-title").children("span").text("步骤 " + (index + 1) + "、");
            });
        });
        $("body").on("click", ".selectProcessImg", function () {
            $(this).siblings("input[name=processImg]").click();
        });
        $('body').on("change", "input[name=processImg]", function () {
            if (this.files[0]) {
                onImg(this.files[0], $(this).siblings(".selectProcessImg"));
            }
        });

        function onImg(data, doc) {
            var fs = new FileReader();
            fs.readAsDataURL(data);
            fs.onload = function () {
                var image = '<img src="' + fs.result + '" style="height: 150px;max-width: 230px;" alt="cover">';
                doc.html(image);
            }
        }
    }());
    (function Add() {
        $('body').on('input propertychange', "#fName", function () {
            if ($(this).val().length === 0 || $(this).val() === null || $(this).val() === "" || $(this).val() === undefined) {
                name = false;
            } else {
                name = true;
            }
            isSub();
        });
        $("body").on("input propertychange", "#fIntroduction", function () {
            if ($(this).val().length === 0 || $(this).val() === null || $(this).val() === "" || $(this).val() === undefined) {
                introduction = false;
            } else {
                introduction = true;
            }
            isSub();
        });
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
                cover = true;
                isSub();
            }
        }
        $("#fireMonitor").change(function(){
            if($("#fireMonitor:checked").val()){
                fire=true;
            }else{
                fire=false;
            }
        });
        $("#subForm").submit(function(e){
            e.preventDefault();
            if (isSub()){
                if (threeArr.length!==0 || twoArr.length!==0){
                     if (materialNumber.length!==0 && materialId.length!==0 && materialName.length!==0 && materialNumber.length===materialName.length){
                         var fireFlag=0;
                         if (fire){
                             if ($(".fireSelect:checked").val()){
                                 fireFlag=$(".fireSelect:checked").val();
                             }else{
                                 Tools.tip("请选择监控火候");
                                 return false;
                             }
                         } else{
                             fireFlag=0;
                         }
                         var stepContent=new Array(),stepTime=new Array();
                         $("textarea[name=recipeContent]").each(function(){
                             if ($(this).val()){
                                 stepContent.push($(this).val());
                             }
                         });
                         $("input[name=recipeTime]").each(function(){
                             if ($(this).val() && /^[0-9]+.?[0-9]*$/.test($(this).val())){
                                 stepTime.push($(this).val());
                             }
                         });
                         if (stepContent.length===0 || stepTime.length===0 ||
                             $("textarea[name=recipeContent]").length!==$("input[name=recipeTime]").length ||
                             $("textarea[name=recipeContent]").length!==stepContent.length ||
                             $("input[name=recipeTime]").length!==stepTime.length){
                             Tools.tip("请完善烹饪流程的内容");
                             return false;
                         }else{
                             $('input[type=submit]').attr("disabled","disabled");
                             $('#progressBar').attr("aria-valuenow","0").css("width","0%");
                             $('#progressCont').show();
                             var formData=new FormData(this);
                             formData.append("fIntroduction",((formData.get("fIntroduction").replace(/<(.+?)>/gi,"&lt;$1&gt;")).replace(/ /gi,"&nbsp;")).replace(/\n/gi,"|"));
                             formData.append("twoArr", twoArr);
                             formData.append("threeArr", threeArr);
                             formData.append("tipArr",tipArr);
                             formData.append("materialNumber",materialNumber);
                             formData.append("materialId",materialId);
                             formData.append("materialName",materialName);
                             formData.append("fFire",fireFlag);
                             formData.append("stepContent",stepContent);
                             formData.append("stepTime",stepTime);
                             $.ajax({
                                 url:"/recipe/info",
                                 type:"post",
                                 Accept:'text/html;charset=UTF-8',
                                 processData:false,
                                 contentType:false,
                                 data:formData,
                                 xhr:function(e){
                                     var xhr = $.ajaxSettings.xhr();
                                     if(xhr.upload){ // check if upload property exists
                                         xhr.upload.addEventListener('progress',function(e){
                                             var loaded = e.loaded;
                                             var total = e.total;
                                             var percent = Math.floor(100*loaded/total);
                                             if (percent === 100){
                                                 $('input[type=submit]').val("资源已上传，保存中...").
                                                 removeClass("btn-default").addClass("btn-success");
                                                 $('#progressBar').attr("aria-valuenow",percent).css("width",percent + "%").
                                                 addClass("progress-bar-success");
                                             } else{
                                                 $('#progressBar').attr("aria-valuenow",percent).css("width",percent + "%");
                                             }
                                         }, false);
                                     }
                                     return xhr;
                                 },
                                 success:function(res){
                                     console.log(123);
                                     if (res.code===200){
                                         Tools.successAddTimeoutTip(res,3);
                                     }else{
                                         Tools.tip(res.msg);
                                     }
                                 }
                             });
                         }
                     }else{
                         Tools.tip("请至少添加一项食材");
                     }
                }else{
                    Tools.tip("请至少添加一项二级分类");
                }
            } else{
                Tools.tip("请完善食谱基本信息后尝试");
            }
        });
    }());
});