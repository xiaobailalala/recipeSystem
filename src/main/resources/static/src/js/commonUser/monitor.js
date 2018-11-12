$(function () {
    var stompClient = null;
    var fireTimer, smogTimer, distanceTimer, isFireListening = false, isSmogListening = false, isFireInit = false, isSmogInit = false;
    var temTop = 0, pmTop = 0, disTop = 0, currentTem = 0, currentPm = 0, currentInfrared = 0, currentDistance = 0;
    var id = Number($("#itemId").val());
    (function SocketConnect() {
        var socket = new SockJS('/endpoint-websocket-webClient');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            var isFireOpen = false, isSmogOpen = false;
            stompClient.subscribe('/sensorData/sendFireNumber', function (result) {
                var objArr = JSON.parse(result.body);
                var isExist = objArr.find(({uid}) => id === uid);
                if (isExist) {
                    isFireOpen = true;
                    temTop = objArr[0].top;
                    if (!isFireListening) {
                        $(".isFireMonitoring").addClass("btn-success").text("数据监控中");
                        $(".isDistanceMonitoring").addClass("btn-success").text("数据监控中");
                        isFireListening = true;
                        isFireInit = true;
                        $(".fireMonitorContent").css("display", "inline");
                        $(".distanceMonitorContent").css("display", "inline");
                        initFireMonitor(isFireOpen, temTop);
                    }
                } else {
                    if (isFireListening) {
                        $(".isFireMonitoring").removeClass("btn-success").text("用户未开启数据监控");
                        $(".isDistanceMonitoring").removeClass("btn-success").text("用户未开启数据监控");
                        isFireListening = false;
                        $(".fireMonitorContent").css("display", "none");
                        $(".distanceMonitorContent").css("display", "none");
                        $(".isBodyMonitoring").css("display", "none");
                        clearInterval(fireTimer);
                        clearInterval(distanceTimer);
                    }
                }
                if (!isFireInit) {
                    if (isFireOpen) {
                        $(".fireMonitorContent").css("display", "inline");
                        $(".distanceMonitorContent").css("display", "inline");
                    }
                    initFireMonitor(isFireOpen, temTop);
                    isFireInit = true;
                }
            });
            stompClient.subscribe("/sensorData/sendInfraredNumber", function (result) {
                var objArr = JSON.parse(result.body);
                var isExist = objArr.find(({uid}) => id === uid);
                if (isExist) {
                    disTop = objArr[0].top;
                }
            });
            stompClient.subscribe('/sensorData/fire', function (result) {
                currentTem = JSON.parse(result.body).tmp;
            });
            stompClient.subscribe("/sensorData/infrared", function (result) {
                currentInfrared = JSON.parse(result.body).body;
            });
            stompClient.subscribe("/sensorData/distance", function (result) {
                currentDistance = Number(JSON.parse(result.body).distance) * 100;
            });
            stompClient.subscribe('/sensorData/sendSmogNumber', function (result) {
                var objArr = JSON.parse(result.body);
                var isExist = objArr.find(({uid}) => id === uid);
                if (isExist) {
                    isSmogOpen = true;
                    pmTop = objArr[0].top;
                    if (!isSmogListening) {
                        $(".isSmogMonitoring").addClass("btn-success").text("数据监控中");
                        isSmogListening = true;
                        isSmogInit = true;
                        $(".smogMonitorContent").css("display", "inline");
                        initSmogMonitor(isSmogOpen, pmTop);
                    }
                } else {
                    if (isSmogListening) {
                        $(".isSmogMonitoring").removeClass("btn-success").text("用户未开启数据监控");
                        isSmogListening = false;
                        $(".smogMonitorContent").css("display", "none");
                        clearInterval(smogTimer);
                    }
                }
                if (!isSmogInit) {
                    if (isSmogOpen) {
                        $(".smogMonitorContent").css("display", "inline");
                    }
                    initSmogMonitor(isSmogOpen, pmTop);
                    isSmogInit = true;
                }
            });
            stompClient.subscribe('/sensorData/smog', function (result) {
                currentPm = JSON.parse(result.body).pm;
            });
        });
    }());

    function activeLastPointToolip(chart) {
        var points = chart.series[0].points;
        chart.tooltip.refresh(points[points.length - 1]);
    }

    function initFireMonitor(isOpen, top) {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        Highcharts.chart('fireMonitor', {
            chart: {
                type: 'spline',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function () {
                        $(".highcharts-credits").remove();
                        var series = this.series[0];
                        var chart = this;
                        activeLastPointToolip(chart);
                        if (isOpen) {
                            fireTimer = setInterval(function () {
                                var x = (new Date()).getTime(),
                                    y = Number(currentTem);
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart);
                                $(".fireMonitorContent .normalContent").text(temTop + "℃");
                                $(".fireMonitorContent .currentDateTime").text(currentDate() + " " + currentTime());
                                if (y > temTop) {
                                    sensorUnusual(0, temTop, y);
                                    $(".fireMonitorContent .currentContent").removeClass("label-default").addClass("label-danger").text(currentTem + "℃");
                                } else {
                                    $(".fireMonitorContent .currentContent").removeClass("label-danger").addClass("label-default").text(currentTem + "℃");
                                }
                            }, 1000);
                        }
                    }
                }
            },
            title: {
                text: '火力传感器监控数据'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: '温度'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2) + ' ℃';
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: '火力温度',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
                    if (isOpen) {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: top
                            });
                        }
                    } else {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 0
                            });
                        }
                    }
                    return data;
                }())
            }]
        });
        Highcharts.chart('distanceMonitor', {
            chart: {
                type: 'spline',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function () {
                        $(".highcharts-credits").remove();
                        var series = this.series[0];
                        var chart = this;
                        activeLastPointToolip(chart);
                        if (isOpen) {
                            distanceTimer = setInterval(function () {
                                var x = (new Date()).getTime(),
                                    y = Number(currentDistance);
                                if (Number(currentInfrared) === 1) {
                                    series.addPoint([x, y], true, true);
                                    activeLastPointToolip(chart);
                                    $(".distanceMonitorContent .normalContent").text(disTop + "cm");
                                    $(".distanceMonitorContent .currentDateTime").text(currentDate() + " " + currentTime());
                                    if (y > disTop) {
                                        sensorUnusual(1, disTop, y);
                                        $(".distanceMonitorContent .currentContent").removeClass("label-default").addClass("label-danger").text(currentDistance + "cm");
                                    } else {
                                        $(".distanceMonitorContent .currentContent").removeClass("label-danger").addClass("label-default").text(currentDistance + "cm");
                                    }
                                    $(".isBodyMonitoring").css("display", "none");
                                } else {
                                    $(".isBodyMonitoring").css("display", "inline-block");
                                }
                            }, 1000);
                        }
                    }
                }
            },
            title: {
                text: '红外-超声波距离传感器监控数据'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: '距离'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2) + ' cm';
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: '人体距离',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
                    if (isOpen) {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 50
                            });
                        }
                    } else {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 0
                            });
                        }
                    }
                    return data;
                }())
            }]
        });
    }

    function initSmogMonitor(isOpen, top) {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        Highcharts.chart('smogMonitor', {
            chart: {
                type: 'spline',
                animation: Highcharts.svg,
                marginRight: 10,
                events: {
                    load: function () {
                        $(".highcharts-credits").remove();
                        var series = this.series[0];
                        var chart = this;
                        activeLastPointToolip(chart);
                        if (isOpen) {
                            smogTimer = setInterval(function () {
                                var x = (new Date()).getTime(),
                                    y = Number(currentPm);
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart);
                                $(".smogMonitorContent .normalContent").text(pmTop + "μg/m³");
                                $(".smogMonitorContent .currentDateTime").text(currentDate() + " " + currentTime());
                                if (y > pmTop) {
                                    sensorUnusual(2, pmTop, y);
                                    $(".smogMonitorContent .currentContent").removeClass("label-default").addClass("label-danger").text(currentPm + "μg/m³");
                                } else {
                                    $(".smogMonitorContent .currentContent").removeClass("label-danger").addClass("label-default").text(currentPm + "μg/m³");
                                }
                            }, 1000);
                        }
                    }
                }
            },
            title: {
                text: '油烟传感器监控数据'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: '浓度'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                        Highcharts.numberFormat(this.y, 2) + 'μg/m³';
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: '油烟浓度',
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
                    if (isOpen) {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: top
                            });
                        }
                    } else {
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 0
                            });
                        }
                    }
                    return data;
                }())
            }]
        });
    }

    function currentDate() {
        var currentDate = new Date();
        var year = currentDate.getFullYear();
        var month = currentDate.getMonth() + 1 < 10 ? "0" + (currentDate.getMonth() + 1) : (currentDate.getMonth() + 1);
        var day = currentDate.getDate() < 10 ? "0" + currentDate.getDate() : currentDate.getDate();
        return year + "-" + month + "-" + day;
    }

    function currentTime() {
        var currentDate = new Date();
        var hours = currentDate.getHours() < 10 ? "0" + currentDate.getHours() : currentDate.getHours();
        var minutes = currentDate.getMinutes() < 10 ? "0" + currentDate.getMinutes() : currentDate.getMinutes();
        var seconds = currentDate.getSeconds() < 10 ? "0" + currentDate.getSeconds() : currentDate.getSeconds();
        return hours + ":" + minutes + ":" + seconds;
    }

    function sensorUnusual(isFire, normal, unusual) {
        var date_now = currentDate(), time_now = currentTime();
        var showProcess = (((unusual - normal) / normal) * 100).toFixed(2);
        var process = showProcess > 100 ? 100.00 : showProcess;
        var item = '<tr>\n' +
            '           <td>\n' +
            '               <section>' + date_now + '</section>\n' +
            '               <section>' + time_now + '</section>\n' +
            '           </td>\n' +
            '           <td class="text-primary">\n' +
            '               ' + normal + (isFire === 0 ? "℃" : (isFire === 1 ? "cm" : "μg/m³")) + '\n' +
            '           </td>\n' +
            '           <td class="text-danger">\n' +
            '               ' + unusual + (isFire === 0 ? "℃" : (isFire === 1 ? "cm" : "μg/m³")) + '\n' +
            '           </td>\n' +
            '           <td>\n' +
            '               <div class="progress progress-striped active" style="margin: 0;" data-toggle="tooltip" data-placement="top" title="异常率：' + showProcess + '%">\n' +
            '                   <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="' + process + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + process + '%;">\n' +
            '                       ' + showProcess + '%\n' +
            '                   </div>\n' +
            '               </div>\n' +
            '           </td>\n' +
            '       </tr>';
        $.ajax({
            url: "/manage/sensorUnusual/info",
            type: "POST",
            data: {
                fUid: id,
                fDate: date_now,
                fTime: time_now,
                fNormal: normal,
                fUnusual: unusual,
                fProcess: showProcess,
                fType: isFire === 0 ? "fire" : (isFire === 1 ? "distance" : "smog")
            },
            success: res=> {
                if ($("." + (isFire === 0 ? "fireUnusualCont" : (isFire === 1 ? "distanceUnusualCont" : "smogUnusualCont")) + ">tr").length >= 4) {
                    $("." + (isFire === 0 ? "fireUnusualCont" : (isFire === 1 ? "distanceUnusualCont" : "smogUnusualCont")) + ">tr").get(0).remove();
                }
                $("." + (isFire === 0 ? "fireUnusualCont" : (isFire === 1 ? "distanceUnusualCont" : "smogUnusualCont"))).append(item);
                $(function () {
                    $('[data-toggle="tooltip"]').tooltip()
                })
            }
        });
    }

    (function otherOperation() {
        $(".clearFireData").click(function () {
            $(".fireUnusualCont").html("");
        });
        $(".clearSmogData").click(function () {
            $(".smogUnusualCont").html("");
        });
        $(".historyFireData").click(function () {
            historyDateRender(true);
        });
        $(".historySmogData").click(function () {
            historyDateRender(false);
        });
        Tools.body.on("change", ".fireDateSelect", function () {
            const date = $(this).val();
            if (date !== "0") {
                historyDataRender(true, date);
            }
        });
        Tools.body.on("change", ".smogDateSelect", function () {
            const date = $(this).val();
            if (date !== "0") {
                historyDataRender(false, date);
            }
        });
        // Tools.body.on("change", ".");
    }());

    function historyDateRender(isFire) {
        $.ajax({
            url: "/manage/sensorUnusual/showDate",
            type: "GET",
            data: {
                fType: isFire ? "fire" : "smog",
                fUid: id,
            },
            success: function (res) {
                var option = '<option value="0">请选择报表日期</option>';
                res.data.dateList.forEach(function (item, index) {
                    option += '<option value="' + item.fdate + '">' + item.fdate + '</option>';
                });
                var div = '<div class="modal fade" id="detailInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">\n' +
                    '        <div class="modal-dialog" role="document">\n' +
                    '            <div class="modal-content">\n' +
                    '                <div class="modal-header">\n' +
                    '                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                    '                    <h4 class="modal-title" id="myModalLabel">' + (isFire ? '火力' : '烟雾') + '传感器监控异常数据</h4>\n' +
                    '                </div>\n' +
                    '                <div class="modal-body">\n ' +
                    '                     <form class="form-inline margin-b-10">' +
                    '                         <div class="form-group">' +
                    '                             <label for="">异常时间&emsp;</label>' +
                    '                             <select class="form-control ' + (isFire ? 'fireDateSelect' : 'smogDateSelect') + '">' + option +
                    '                             </select>' +
                    '                             <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#detailMonitorData"' +
                    '                             aria-expanded="false" aria-controls="detailMonitorData">详细数据</button>' +
                    '                         </div>' +
                    '                     </form>' +
                    '                     <div id="reportContainer" style="min-width:400px;height:400px"></div>' +
                    '                     <div class="collapse" id="detailMonitorData">' +
                    '                         <table class="table">' +
                    '                               <thead>' +
                    '                                   <tr>' +
                    '                                       <th>时间</th>' +
                    '                                       <th>正常值</th>' +
                    '                                       <th>异常值</th>' +
                    '                                       <th>异常率</th>' +
                    '                                   </tr>' +
                    '                               </thead>' +
                    '                               <tbody class="' + (isFire ? 'historyFireCont' : 'historySmogCont') + '"></tbody>' +
                    '                         </table>' +
                    '                     </div>' +
                    '                </div>\n' +
                    '                <div class="modal-footer">\n' +
                    '                    <button type="button" class="btn btn-light" data-dismiss="modal">关闭</button>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>\n' +
                    '    </div>';
                $("#temporaryContainer").html(div);
                $("#detailInfoModal").modal("show");
            }
        });
    }

    function historyDataRender(isFire, date) {
        var list = '';
        $.ajax({
            url: "/manage/sensorUnusual/getDataByUidAndTypeAndDate",
            type: "POST",
            data: {
                fType: isFire ? "fire" : "smog",
                fUid: id,
                fDate: date
            },
            success: function (res) {
                var arrData = [{name: "0~3%", y: 0}, {name: "4%~8%", y: 0}, {name: "9~15%", y: 0}, {
                    name: "16%~30%",
                    y: 0
                }, {name: "31%~50%", y: 0}, {name: "50%以上", y: 0}];
                var scope1 = 0, scope2 = 0, scope3 = 0, scope4 = 0, scope5 = 0, scope6 = 0;
                res.data.list.forEach(function (item, index) {
                    if (item.fprocess >= 0 && item.fprocess < 4) {
                        scope1++;
                    } else if (item.fprocess >= 4 && item.fprocess < 9) {
                        scope2++;
                    } else if (item.fprocess >= 9 && item.fprocess < 16) {
                        scope3++;
                    } else if (item.fprocess >= 16 && item.fprocess < 31) {
                        scope4++;
                    } else if (item.fprocess >= 31 && item.fprocess < 51) {
                        scope5++;
                    } else {
                        scope6++;
                    }
                    var showProcess = (((item.funusual - item.fnormal) / item.fnormal) * 100).toFixed(2);
                    list += '<tr>\n' +
                        '           <td>\n' +
                        '               <section>' + item.fdate + '</section>\n' +
                        '               <section>' + item.ftime + '</section>\n' +
                        '           </td>\n' +
                        '           <td class="text-primary">\n' +
                        '               ' + item.fnormal + (isFire ? "℃" : "μg/m³") + '\n' +
                        '           </td>\n' +
                        '           <td class="text-danger">\n' +
                        '               ' + item.funusual + (isFire ? "℃" : "μg/m³") + '\n' +
                        '           </td>\n' +
                        '           <td>\n' +
                        '               <div class="progress progress-striped active" style="margin: 0;" data-toggle="tooltip" data-placement="top" title="异常率：' + showProcess + '%">\n' +
                        '                   <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="' + item.fprocess + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + item.fprocess + '%;">\n' +
                        '                       ' + showProcess + '%\n' +
                        '                   </div>\n' +
                        '               </div>\n' +
                        '           </td>\n' +
                        '       </tr>';
                });
                var total = scope1 + scope2 + scope3 + scope4 + scope5 + scope6;
                arrData[0].y = (scope1 / total) * 100;
                arrData[1].y = (scope2 / total) * 100;
                arrData[2].y = (scope3 / total) * 100;
                arrData[3].y = (scope4 / total) * 100;
                arrData[4].y = (scope5 / total) * 100;
                arrData[5].y = (scope6 / total) * 100;
                Highcharts.chart('reportContainer', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                    },
                    title: {
                        text: isFire ? '火力监控异常比' : '油烟监控异常比'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: true
                        }
                    },
                    series: [{
                        name: 'Brands',
                        colorByPoint: true,
                        data: arrData
                    }]
                });
                $(".highcharts-credits,.highcharts-exporting-group").remove();
                $("." + (isFire ? "historyFireCont" : "historySmogCont")).html(list);
            }
        });
    }
});