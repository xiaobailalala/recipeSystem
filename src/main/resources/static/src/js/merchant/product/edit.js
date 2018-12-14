layui.use(['element', 'util', 'upload', 'form', 'table', 'layer'], function () {
    var BODY = $(document);
    var _productImages = new Array();
    var element = layui.element,
        util = layui.util,
        upload = layui.upload,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    var upload_i = 0;
    $(window).scroll(function () {
        var topp = 50 - $(document).scrollTop();
        $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');
    });

    $("#TEST_IMG").click(function (e) {
        e.preventDefault();
        console.log(_productImages);
    });

    //文本框字数限制
    $("#productTitle").on("input propertychange", function () {
        var $this = $(this),
            _val = $this.val(),
            count = "";
        if (_val.length > 60) {
            $this.val(_val.substring(0, 60));
        }
        count = $this.val().length;
        $("#text-count").text(count);
    });

    //添加商品类型
    (function productClassify() {
        var select = $("select[name=fCategory]");
        // var proclassify = "<option value=''>请选择商品类型</option>";
        var proclassify = "";
        $.ajax({
            url: "/merchantCommon/getAllProductCla",
            type: "GET",
            success: res => {
                res.data.forEach(function (item, index) {
                    proclassify += "<option value='" + item.fid + "'>" + item.fname + "</option>";
                });
                select.append(proclassify);
                form.render();
            }
        });
    }());

    //添加商品型号类型
    (function marqueClassify() {
        var select = $("select[name=fMarqueclaid]");
        // var marclassify = '<option value="">请选择型号分类</option>';
        var marclassify = '';
        $.ajax({
            url: "/merchantCommon/getAllMarqueClassify",
            type: "GET",
            success: res => {
                res.data.forEach(function (item, index) {
                    marclassify += "<option value='" + item.fid + "'>" + item.fmarquename + "</option>";
                });
                select.append(marclassify);
                form.render();
            }
        });
    }());

    //添加运费模板
    (function productFreight() {
        var select = $("select[name=fFreightid]");
        var productfreight = '';
        $.ajax({
            url: "/merchantCommon/getAllFreight",
            type: "GET",
            success: res => {
                res.data.forEach(function (item, index) {
                    productfreight += "<option value='" + item.fid + "'>" + item.ffreightname + "</option>";
                });
                select.append(productfreight);
                form.render();
            }
        });
    }());


    // 获取JSON对象的长度
    function getJsonLength(jsonData) {
        var jsonLength = 0;
        for (var item in jsonData) {
            jsonLength++;
        }
        return jsonLength;
    }

    util.fixbar({
        bar1: true,
        click: function (type) {
            console.log(type);
            if (type === 'bar1') {
                alert('点击了bar1')
            }
        }
    });

    //图片预览方法
    function showImg(file, dom) {
        var fr = new FileReader();
        fr.readAsDataURL(file);
        fr.onload = function () {
            var image = "<img src='" + fr.result + "' style='max-width: 100%;height: 100%;z-index: 10;' alt='商品类型图片'>";
            // var upload_img_ops = "<div class='upload_img_ops'>" +
            //     "<a href='javascript:;' style='color: #ffffff;' class='upload_img_delete'>删除</a>" +
            //     "</div>";
            dom.html(image);
        }
    }

    //商品类型图片上传预览
    BODY.on("click", ".Marqueimage", function () {
        $(this).siblings("input[name=proMarqueImg]").click();
    });
    BODY.on("change", "input[name=proMarqueImg]", function () {
        showImg($(this).context.files[0], $(this).siblings(".Marqueimage"));
        $(this).siblings(".upload_img_ops").css("display", "block");
    });
    //商品类型图片删除
    BODY.on("click", ".upload_img_delete", function (e) {
        e.stopPropagation();
        var test = $(this).parent(".upload_img_ops").siblings("input[name=proMarqueImg]");
        test[0].value = "";
        var span = "<span style='position: absolute;' class='layui-icon layui-icon-add-1'></span>";
        $(this).parent(".upload_img_ops").siblings(".upload_img").html(span);
        $(this).parent(".upload_img_ops").css("display", "none");
    });
    //商品类型删除
    BODY.on("click", ".product_marque_delete", function () {
        var _this = $(this);
        var index = layer.alert("确定要删除该商品类型吗？(删除后无法撤回)",
            {icon: 0, skin: 'layui-layer-lan'}, function () {
                _this.parents(".product_marque_div").remove();
                layer.msg("类型删除成功！");
                layer.close(index);
            });
    });

    //商品类型添加
    BODY.on("click", "#product_marque_add", function () {
        var product_marque =
            '<div class="product_marque_div">\n' +
            '<div class="product_marque" style="padding-left: 15px; min-height: 80px;">\n' +
            '    <div class="product_marque_item">\n' +
            '        <input type="text" class="layui-input" style="width: 150px; margin: 0 auto;">\n' +
            '    </div>\n' +
            '    <div class="product_marque_item">\n' +
            '        <input type="text" class="layui-input" style="width: 150px; margin: 0 auto;">\n' +
            '    </div>\n' +
            '    <div class="product_marque_item">\n' +
            '        <input type="text" class="layui-input" style="width: 150px; margin: 0 auto;">\n' +
            '    </div>\n' +
            '    <div class="product_marque_item">\n' +
            '        <div class="upload_img Marqueimage" style="z-index: 8">\n' +
            '            <span style="position: absolute;"\n' +
            '                  class="layui-icon layui-icon-add-1"></span>\n' +
            '        </div>\n' +
            '        <div class="upload_img_ops">\n' +
            '            <a href="javascript:;" style="color: #ffffff;" class="upload_img_delete">删除</a>\n' +
            '        </div>\n' +
            '        <input type="file" style="display: none;" accept="images"\n' +
            '               name="proMarqueImg">\n' +
            '    </div>\n' +
            '    <div class="product_marque_item" style="text-align: center;">\n' +
            '        <a href="javascript:;" class="product_marque_delete">删除</a>\n' +
            '    </div>\n' +
            '</div>\n' +
            '<hr>' +
            '</div>';
        $(this).parent("#product_marque_add_div").before(product_marque);
    });
    //商品主图删除（商品编辑独有）
    BODY.on("click", ".demo-delete", function () {
        $(this).parents(".editimg").remove();
    });
    //商品主图预览
    var ImageListView = $('#product_images'),
        uploadListIns = upload.render({
            elem: "#moreImgupload",
            multiple: true,
            accept: "images",
            auto: false,
            choose: function (obj) {
                //将每次选择的文件追加到文件队列
                _productImages = obj.pushFile();
                // var len = getJsonLength(_productImages);
                var len = $('.editimg').length;
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    var tr = $(['<div id="upload-' + index + '" class="editimg">',
                        '<div class="imgs">',
                        '<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" width="90px" height="90px">',
                        '<a class="demo-delete layui-btn"><i class="layui-icon layui-icon-close "></i></a>',
                        '</div>', '</div>'
                    ].join(''));
                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete _productImages[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    //张数限制
                    if (len >= 5) {
                        //但是layUI的before方法，不管返回什么，还是会进行上传
                        delete _productImages[index];
                        layer.msg("最多上传5张图片！");
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        return false;
                    } else {
                        ImageListView.append(tr);
                    }
                });
            }
        });

    //添加商品详情页面
    var pro_details =
        '<div class="layui-card pro_details">\n' +
        '<div class="layui-card-header">\n' +
        '    <h3 style="display: inline-block;">商品详情</h3>\n' +
        '    <i class="layui-icon layui-icon-close"></i>\n' +
        '</div>\n' +
        '<div class="layui-card-body" style="padding: 10px 50px;">\n' +
        '    <div style="display:inline-block;vertical-align: top;" class="pro_details_image">\n' +
        '        <h3 style="text-align: center;margin-bottom: 10px;">商品详情图片</h3>\n' +
        '        <div>\n' +
        '            <div class="upload_img DetailsImage" style="z-index: 8; width: 222px;height: 222px;">\n' +
        '                 <span style="position: absolute;" class="layui-icon layui-icon-add-1"></span>\n' +
        '            </div>\n' +
        '            <div class="upload_img_ops" style="height: 224px;;width: 224px;;top: 43px;left: 49px;">\n' +
        '                <a href="javascript:;" style="color: #ffffff;line-height: 224px;"\n' +
        '                   class="upload_img_details_delete">删除</a>\n' +
        '            </div>\n' +
        '            <input type="file" style="display: none;" accept="images"\n' +
        '                   name="proDetailsImg">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div style="display:inline-block;margin-left: 25px;" class="pro_details_content">\n' +
        '        <h3 style="text-align: center;margin-bottom: 10px;">商品详情文字</h3>\n' +
        '        <textarea name="pro_details_content" id="" cols="100" rows="10"\n' +
        '                  style="border:1px solid #bebcbc;padding: 15px;"></textarea>\n' +
        '    </div>\n' +
        '</div>\n' +
        '</div>';
    //商品详情图片上传预览
    BODY.on("click", ".DetailsImage", function () {
        $(this).siblings("input[name=proDetailsImg]").click();
    });
    BODY.on("change", "input[name=proDetailsImg]", function () {
        showImg($(this).context.files[0], $(this).siblings(".DetailsImage"));
        $(this).siblings(".upload_img_ops").css("display", "block");
    });
    //商品详情图片删除
    BODY.on("click", ".upload_img_details_delete", function (e) {
        e.stopPropagation();
        var test = $(this).parent(".upload_img_ops").siblings("input[name=proDetailsImg]");
        test[0].value = "";
        var span = "<span style='position: absolute;' class='layui-icon layui-icon-add-1'></span>";
        $(this).parent(".upload_img_ops").siblings(".upload_img").html(span);
        $(this).parent(".upload_img_ops").css("display", "none");
    });

    //添加商品详情页面
    BODY.on('click', '.add_product_details_btn', function (e) {
        e.preventDefault();
        $('#add_product_details_div').before(pro_details);
        upload_i++;
        // console.log(upload_i);
        $('.product_details_div .pro_details:last').find('.upload-img-preview').attr('id', 'upload-img-preview-product' + upload_i);
    });
    //删除商品详情面板
    BODY.on('click', '.pro_details .layui-card-header i', function (e) {
        e.preventDefault();
        var _this = $(this);
        var index = layer.alert("确定要删除该商品详情吗？(删除后无法撤回)",
            {icon: 0, skin: 'layui-layer-lan'}, function () {
                _this.parents('.pro_details').remove();
                layer.msg("删除成功！");
                layer.close(index);
            });
    });

    form.render();
    //表单验证
    form.verify({
        price: [
            /^[0-9]+([.]{1}[0-9]{1,2})?$/,
            '价格必须为大于等于0的金额，精确到小数点后两位'
        ],
        repertory: [
            /^[1-9]\d*|0$/,
            '库存只能为正整数'
        ]
    });
    //开关监听
    form.on('switch(appendImg)', function (data) {
        var index = $("input[lay-filter=appendImg]").index($(this));
        if (data.elem.checked) {
            $($(".addModelClassify-div").get(index)).find(".showimgpreview").css('display', 'block');
        } else {
            $($(".addModelClassify-div").get(index)).find(".showimgpreview").css('display', 'none');
        }
    });
    table.render();

    //表格重载
    function tableInit() {
        table.init('parse-table', {});
    }

    form.on('submit(submit_btn)', function (data) {
        var form = document.getElementById('product_form');
        var formdata = new FormData(form);
        var marquePrice = [],
            marqueRepository = [],
            productDetailsText = [],
            marqueName = [],
            imgsId = [],
            marqueId = [],
            pro_details_Id = [],
            deleteMarqueImageFlag = [],
            deleteDetailImageFlag = [],
            marqueImageFlag = [],
            detailImageFlag = [];
        var len = $('.editimg').length;
        var marqueLen = $(".product_marque_div").length;
        var index = window.location.href.lastIndexOf("/");
        var id = window.location.href.substr(index + 1, window.location.href.length);
        if (len === 0 || marqueLen === 0) {
            layer.alert("请完善商品信息");
        } else {
            $.each(_productImages, function (index, item) {
                formdata.append("productImage", item);
            });

            $.each($(".Marqueimage"),function () {
                if ($(this).has("img").length > 0){
                    marqueImageFlag.push(1);
                    return true;
                } else {
                    marqueImageFlag.push(0);
                    if ($(this).siblings("input[name=marqueId]").val() !== undefined){
                        deleteMarqueImageFlag.push($(this).siblings("input[name=marqueId]").val());
                    }
                }
            });
            formdata.append("deleteMarqueImageFlag",deleteMarqueImageFlag);
            formdata.append("marqueImageFlag",marqueImageFlag);

            $.each($(".DetailsImage"),function () {
                if ($(this).has("img").length > 0){
                    detailImageFlag.push(1);
                    return true;
                } else {
                    detailImageFlag.push(0);
                    if ($(this).siblings("input[name=pro_details_Id]").val() !== undefined){
                        deleteDetailImageFlag.push($(this).siblings("input[name=pro_details_Id]").val());
                    }
                }
            });
            formdata.append("deleteDetailImageFlag",deleteDetailImageFlag);
            formdata.append("detailImageFlag",detailImageFlag);

            $.each($("input[name=fMarquename]"),function (index, item) {
                marqueName.push($(this).val());
            });
            formdata.append("marqueName", marqueName);

            $.each($("input[name=fPrice]"), function () {
               marquePrice.push($(this).val());
            });
            formdata.append("marquePrice", marquePrice);

            $.each($("input[name=fRepository]"), function () {
               marqueRepository.push($(this).val());
            });
            formdata.append("marqueRepository", marqueRepository);

            $('textarea[name=pro_details_content]').each(function (index, item) {
                productDetailsText.push($(this).val());
            });
            formdata.append("productDetailsContent", productDetailsText);
            if ($('input[name=manager_hot]').is(':checked')) {
                formdata.append("pro_title", "#店长推荐#" + $('textarea[name=fName]').val());
            } else {
                formdata.append("pro_title", $('textarea[name=fName]').val());
            }
            $.ajax({
                url: "/merchant/merchantProduct/editor/" + id,
                type: "PUT",
                data: formdata,
                Accept: 'text/html;charset=UTF-8',
                processData: false,
                contentType: false,
                success: res => {
                    if (res.code === 200) {
                        layer.msg("商品编辑成功！");
                    } else {
                        layer.msg("吃屎吧你！");
                    }
                }
            })
        }
        return false;
    })
});
