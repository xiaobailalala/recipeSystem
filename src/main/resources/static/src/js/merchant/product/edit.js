$(function () {
    layui.use(['element', 'util', 'upload','form','table'], function () {
        var BODY = $(document);
        var _productImages = new Array();
        var element = layui.element,
            util = layui.util,
            upload = layui.upload,
            form = layui.form,
            table = layui.table;
        var upload_i = 0;
        $(window).scroll(function () {
            var topp = 50 - $(document).scrollTop();
            $('.top-nav').css('top', topp > 0 ? topp + 'px' : '0');
        });

        $("#TEST_IMG").click(function (e) {
            e.preventDefault();
            console.log(_productImages);
        });

        //添加商品类型
        (function productClassify() {
            var select = $("select[name=fCategory]");
            var proclassify = '';
            $.ajax({
                url: "/merchant/merchantProductClassify/getAllProductCla",
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
            var marclassify = '';
            $.ajax({
                url: "/merchant/productMarqueClassify/getAllMarqueClassify",
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
                url: "/merchant/merchantProductFreight/getAllFreight",
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

        //图片预览方法
        function showImg(file, dom) {
            var fr = new FileReader();
            fr.readAsDataURL(file);
            fr.onload = function () {
                var image = "<img src='" + fr.result + "' style='max-width: 100px;height: 100px;' alt='商品图片'>";
                dom.html(image);
            }
        }

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

        //添加型号
        BODY.on('click', '.addMarque', function (e) {
            e.preventDefault();
            var index = $('.addMarque').index($(this));
            var text = $($('.div_select').get(index)).find('dl.layui-anim dd.layui-this').text();
            console.log(text);
            if (text == '请选择型号分类' || text == '') {
                layer.msg('请先选择型号分类');
            } else {
                $(this).siblings('.addModel-div').css('display', 'block');
            }
        });
        //添加型号输入框取消清空
        BODY.on('click', '.addmodel-name-btn-cencle', function (e) {
            e.preventDefault();
            $(this).siblings('.model-name').val('');
        });
        //添加型号取消关闭
        BODY.on('click', '.addModel-div-cencle', function (e) {
            e.preventDefault();
            $(this).parents('.addModel-div').css('display', 'none');
        });
        //添加型号遍历插入
        BODY.on('click', '.addmodel-name-btn-add', function (e) {
            e.preventDefault();
            var form = layui.form;
            //添加型号型号名内容
            var modelli = [
                '<div class="section-model-li">',
                '   <div class="model-name-div">',
                '       <input class="modelName" type="checkbox" name="" title="" lay-filter="modelName-filter">',
                '   </div>',
                '   <i class="layui-icon layui-icon-close model-li-del"></i>',
                '</div>'
            ].join('');
            var layer = layui.layer;
            var addflag = 'meiyou';
            var addflag1 = 'meiyou';
            var addindex = $('.addmodel-name-btn-add').index($(this));

            if (/^\s+$/.test($(this).siblings('.model-name').val()) || $(this).siblings('.model-name').val() == '') {
                layer.msg('类型名不能为空！');
            } else {
                var text = $(this).siblings('.model-name').val();
                $($('.addModel-div').get(addindex)).find('.modelName').each(function (indexInArray, valueOfElement) {
                    var title = $(this).attr('title');
                    if (title == text) {
                        layer.msg('不许添加同名类型');
                        addflag = 'youle';
                        return false;
                    }
                });
                $($('.addModelClassify-div').get(addindex)).children('ul').find('.li-span-name span').each(function (indexInArray, valueOfElement) {
                    var spantext = $(this).text();
                    if (spantext == text) {
                        layer.msg('不许添加同名类型');
                        addflag1 = 'youle';
                        return false;
                    }
                });
                if ($('.modelName').length == 0 && addflag1 == 'meiyou') {
                    $($('.addModel-div').get(addindex)).children('.addmodel-section').append(modelli);
                    $($('.addModel-div').get(addindex)).find('.addmodel-section .section-model-li:nth-last-child(1) input').attr('title', text);
                    $($('.addModel-div').get(addindex)).find('.model-name').val('');
                    form.render();
                } else {
                    if (addflag == 'meiyou' && addflag1 == 'meiyou') {
                        $($('.addModel-div').get(addindex)).children('.addmodel-section').append(modelli);
                        $($('.addModel-div').get(addindex)).find('.addmodel-section .section-model-li:nth-last-child(1) input').attr('title', text);
                        $($('.addModel-div').get(addindex)).find('.model-name').val('');
                        form.render();
                    }
                }
            }
        });

        //删除添加型号面板的型号
        BODY.on('click', '.model-li-del', function (e) {
            e.preventDefault();
            $(this).parent('.section-model-li').remove();
        });
        //添加型号插入型号面板
        BODY.on('click', '.addModel-div-confirm', function (e) {
            e.preventDefault();
            //类型名内容
            $('.noshowtable').addClass('showtable').removeClass('noshowtable');
            var addModelli = [
                '<li class="model-li">',
                '    <div class="li-div-modelname">',
                '       <span class="li-span-name">',
                '           <span></span>',
                '           <i class="layui-icon layui-icon-close model-li-close"></i>',
                '       </span>',
                '       <div class="nohave-img showimgpreview">',
                '           <div class="upload-img-preview" >',
                '               <i class="layui-icon layui-icon-add-1"></i>',
                '               <img src="" alt="" class="layui-upload-img">',
                '           </div>',
                '           <div class="upload-ops">',
                '               <a href="javascript:;" class="imgSingleDel">删除</a>',
                '           </div>',
                '       </div>',
                '   </div>',
                '</li>'
            ].join('');
            var index = $(".addModel-div-confirm").index($(this));
            $(this).parent('.addModel-footer').siblings('section').find('.layui-form-checked span').each(
                function (indexInArray, valueOfElement) {
                    if ($($('.product-div-model-title').get(index)).find('div[lay-skin="_switch"]').hasClass('layui-form-onswitch')) {
                        upload_i++;
                        $($('.addModelClassify-div').get(index)).children("ul").prepend(addModelli);
                        $($('.addModelClassify-div').get(index)).children("ul").children("li:first-child").find('.li-span-name span').text($(this).text());
                        $($('.addModelClassify-div').get(index)).children("ul").children("li:first-child").find('.showimgpreview').css('display', 'block');
                        $(this).parents('.section-model-li').remove();
                        $($('.addModelClassify-div').get(index)).children("ul").children("li:first-child").find('.upload-img-preview').attr('id', 'upload-img-preview' + upload_i);
                        onImgUpload('#upload-img-preview' + upload_i + '');
                    } else {
                        upload_i++;
                        $(addModelli).find('.upload-img-preview').attr('id', 'upload-img-preview' + upload_i + '');
                        $($('.addModelClassify-div').get(index)).children("ul").prepend(addModelli);
                        $($('.addModelClassify-div').get(index)).children("ul").children("li:first-child").find('.li-span-name span').text($(this).text());
                        $(this).parents('.section-model-li').remove();
                        $($('.addModelClassify-div').get(index)).children("ul").children("li:first-child").find('.upload-img-preview').attr('id', 'upload-img-preview' + upload_i);
                        onImgUpload('#upload-img-preview' + upload_i + '');
                    }
                }
            );
            //添加型号面板型号时创建table数据
            $('#table_data_body').empty(); //每次清空表格
            var text = $($('.div_select').get(index)).find('dl.layui-anim dd.layui-this').text();
            var flag = 'meiyou';
            $('#table_data_head tr th').each(function (index, item) {
                console.log($(this));
                if ($(this).attr('thisName') == undefined) {
                    var t_head = "<th thisName=" + text + " lay-data=" + "{field:'" + text + "',align:'center',title:'" + text + "'}></th>";
                    $('#thisTop').before(t_head);
                    flag = 'youle';
                    return false;
                }
                if ($(this).attr('thisName') == text) {
                    flag = 'youle';
                    return false;
                } else {
                    flag = 'meiyou';
                }
            });
            if (flag == 'meiyou') {
                var t_head = "<th thisName=" + text + " lay-data=" + "{field:'" + text + "',align:'center',title:'" + text + "'}></th>";
                $('#thisTop').before(t_head);
            }
            var rowspan = $($('.addModelClassify-div').get(index)).children('ul').find('li span.li-span-name span').length;
            if ($('#table_data_body tr').length == 0) {
                //判断初始表格为空时
                $($('.addModelClassify-div').get(index)).children('ul').find('li span.li-span-name span').each(function (index, element) {
                    var text = $(this).text();
                    var _data = [
                        '<tr>',
                        '<td>' + text + '</td>',
                        '</tr>'
                    ].join('');
                    $('#table_data_body').append(_data);
                    tableInit();
                });
            }
        });
        //删除型号面板的型号
        BODY.on('click', '.model-li-close', function (e) {
            e.preventDefault();
            var form = layui.form;
            var modelli = $([
                '<div class="section-model-li">',
                '   <div class="model-name-div">',
                '       <input class="modelName" type="checkbox" name="" title="" lay-filter="modelName-filter">',
                '   </div>',
                '   <i class="layui-icon layui-icon-close model-li-del"></i>',
                '</div>'
            ].join(''));
            var text = $(this).siblings('span').text();
            var $thisli = $(this).parents('.addModelClassify-div');
            var index = $('.addModelClassify-div').index($thisli);
            // var arr = t_data[index].model.filter(function (item) {
            //     return item.name == text;
            // });
            if ($('.model-li-close').length == 1) {
                $('.showtable').addClass('noshowtable').removeClass('showtable');
            }
            //删除型号面板元素同时修改table数据
            // t_data[index].model.splice(t_data[index].model.indexOf(arr[0]), 1);
            $(this).parents('ul').find('section').append(modelli);
            modelli.find('input').attr('title', text);
            $(this).parents('.model-li').remove();
            $('#table_data_body tr').each(function (index, item) {
                if ($(this).children('td').text() == text) {
                    $(this).remove();
                }
            });
            tableInit();
            form.render();
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
                    var len = getJsonLength(_productImages);
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
                        if (len > 5) {
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

        //商品类型图片预览
        function onImgUpload(element) {
            var uploadInst = upload.render({
                elem: element,
                acceptMime: "images",
                auto: false,
                field: "marqueImage",
                choose: function (obj) {
                    var files = obj.pushFile();
                    var thisItem = $(this.item);
                    // var delindex = $('.upload-img-preview').index(thisItem);
                    console.log('thisItem :', thisItem);
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        thisItem.find('.layui-upload-img').attr('src', result); //图片链接（base64）
                        setTimeout(() => {
                            thisItem.parent('.nohave-img').addClass('have-img').removeClass('nohave-img');
                        }, 100);
                        thisItem.children('i').css('display', 'none');
                        thisItem.siblings('.upload-ops').css('display', 'block');

                        //删除
                        thisItem.siblings('.upload-ops').find('.imgSingleDel').on('click', function (e) {
                            e.preventDefault();
                            setTimeout(() => {
                                thisItem.parent('.have-img').addClass('nohave-img').removeClass('have-img');
                            }, 100);
                            thisItem.siblings('.upload-ops').css('display', 'none');
                            thisItem.children('i').css('display', 'block');
                            thisItem.find('.layui-upload-img').attr('src', ''); //图片链接（base64）
                            delete files[index];
                            uploadInst.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });
                    });
                }
            });
        }

        //商品详情图片预览
        function onImgUpload_details(element) {
            var uploadInst = upload.render({
                elem: element,
                acceptMime: "images",
                field: "detailsImage",
                auto: false,
                choose: function (obj) {
                    var files = obj.pushFile();
                    var thisItem = $(this.item);
                    obj.preview(function (index, file, result) {
                        thisItem.find('.layui-upload-img').attr('src', result); //图片链接（base64）
                        setTimeout(() => {
                            thisItem.parent('.nohave-img').addClass('have-img').removeClass('nohave-img');
                        }, 100);
                        thisItem.children('i').css('display', 'none');
                        thisItem.siblings('.upload-ops').css('display', 'block');

                        //删除
                        thisItem.siblings('.upload-ops').find('.imgSingleDel').on('click', function (e) {
                            e.preventDefault();
                            setTimeout(() => {
                                thisItem.parent('.have-img').addClass('nohave-img').removeClass('have-img');
                            }, 100);
                            thisItem.siblings('.upload-ops').css('display', 'none');
                            thisItem.children('i').css('display', 'block');
                            thisItem.find('.layui-upload-img').attr('src', ''); //图片链接（base64）
                            delete files[index];
                            uploadInst.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                        });
                    });
                }
            });
        }

        //添加商品详情页面
        var pro_details =
            '<div class="layui-card pro_details">\n' +
            '    <div class="layui-card-header">\n' +
            '        <h3 style="display: inline-block;">商品详情</h3>\n' +
            '        <i class="layui-icon layui-icon-close"></i>\n' +
            '    </div>\n' +
            '    <div class="layui-card-body">\n' +
            '        <div style="display:inline-block;vertical-align: top;" class="pro_details_image">\n' +
            '            <h3 style="text-align: center;margin-bottom: 10px;">商品详情图片</h3>\n' +
            '            <div>\n' +
            '                <div class="nohave-img">\n' +
            '                    <div class="upload-img-preview">\n' +
            '                        <i class="layui-icon layui-icon-add-1"></i>\n' +
            '                        <img src="" alt="" class="layui-upload-img">\n' +
            '                    </div>\n' +
            '                    <div class="upload-ops">\n' +
            '                        <a href="javascript:;" class="imgSingleDel">删除</a>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div style="display:inline-block;" class="pro_details_content">\n' +
            '            <h3 style="text-align: center;margin-bottom: 10px;">商品详情文字</h3>\n' +
            '            <textarea name="pro_details_content" id="" cols="100" rows="10" style="border:1px solid #bebcbc;padding: 15px;"></textarea>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>';

        //添加商品详情页面
        BODY.on('click', '.add_product_details_btn', function (e) {
            e.preventDefault();
            $('#add_product_details_div').before(pro_details);
            upload_i++;
            // console.log(upload_i);
            $('.product_details_div .pro_details:last').find('.upload-img-preview').attr('id', 'upload-img-preview-product' + upload_i);
            onImgUpload_details('#upload-img-preview-product' + upload_i)
        });
        //删除商品详情面板
        BODY.on('click', '.pro_details .layui-card-header i', function (e) {
            e.preventDefault();
            $(this).parents('.pro_details').remove();
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
    });
});
