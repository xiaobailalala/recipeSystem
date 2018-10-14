$(function () {
    var stompClient = null;
    var fireTimer, smogTimer, isFireListening = false, isSmogListening = false, isFireInit = false, isSmogInit = false;
    var temTop = 0, pmTop = 0, currentTem = 0, currentPm = 0;
    var id = Number($("#itemId").val());
    (function SocketConnect(){
        var socket = new SockJS('/endpoint-websocket-webClient');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            var isFireOpen = false, isSmogOpen = false;
            stompClient.subscribe('/sensorData/sendFireNumber', function (result) {
                var objArr = JSON.parse(result.body);
                var isExist = objArr.find(({uid}) => id === uid);
                if (isExist){
                    isFireOpen = true;
                    temTop = objArr[0].top;
                    if (!isFireListening){
                        $(".isMonitoring").addClass("btn-success").text("数据监控中");
                        isFireListening = true;
                        initFireMonitor(isFireOpen, temTop);
                    }
                }else{
                    if (isFireListening){
                        $(".isMonitoring").removeClass("btn-success").text("用户未开启数据监控");
                        isFireListening = false;
                        clearInterval(fireTimer);
                    }
                }
                if (!isFireInit){
                    initFireMonitor(isFireOpen, temTop);
                    isFireInit = true;
                }
            });
            stompClient.subscribe('/sensorData/fire', function (result) {
                currentTem = JSON.parse(result.body).tmp;
            });
            stompClient.subscribe('/sensorData/sendSmogNumber', function (result) {
                var objArr = JSON.parse(result.body);
                var isExist = objArr.find(({uid}) => id === uid);
                if (isExist){
                    isSmogOpen = true;
                    pmTop = objArr[0].top;
                    if (!isSmogListening){
                        $(".isMonitoring").addClass("btn-success").text("数据监控中");
                        isSmogListening = true;
                        initSmogMonitor(isSmogOpen, pmTop);
                    }
                }else{
                    if (isSmogListening){
                        $(".isMonitoring").removeClass("btn-success").text("用户未开启数据监控");
                        isSmogListening = false;
                        clearInterval(smogTimer);
                    }
                }
                if (!isSmogInit){
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
        chart.tooltip.refresh(points[points.length -1]);
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
                        if (isOpen){
                            fireTimer = setInterval(function () {
                                var x = (new Date()).getTime(),
                                    y = Number(currentTem);
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart);
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
                        Highcharts.numberFormat(this.y, 2)+' ℃';
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
                    if (isOpen){
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: top
                            });
                        }
                    } else{
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
                        if (isOpen){
                            smogTimer = setInterval(function () {
                                var x = (new Date()).getTime(),
                                    y = Number(currentPm);
                                series.addPoint([x, y], true, true);
                                activeLastPointToolip(chart);
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
                        Highcharts.numberFormat(this.y, 2)+'μg/m³';
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
                    if (isOpen){
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: top
                            });
                        }
                    } else{
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
});