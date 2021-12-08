<#include '../../frame/functions.ftlh'>

<#macro PageHeader>
    <meta name="author" content="${gSiteName!'singte'}">
    <meta name="copyright" content="${gSiteName!'singte'}">
    <link type="text/css" rel="stylesheet" href="/static/css/list.css">
    <script src="/static/js/echarts/echarts.min.js"></script>
</#macro>

<@PageFrame title="SEO 流量统计结果" keywords="SEO 流量统计结果" description="SEO 流量统计结果">
    <div class="column_market w1200 pt20 clearfix">
        <div id="ip-charts" style="width: 1200px;height:800px;">1</div>
    </div>

    <script type="text/javascript">
        function sunburst(dataMap, userList, index) {
            var data = [];
            for (const user of userList) {
                var children = [];

                for (const dataMapKey in dataMap) {
                    if(dataMapKey.indexOf(user + "-") === 0){
                        children.push({
                            name:dataMapKey,
                            value:dataMap[dataMapKey][index]
                        })
                    }
                }

                data.push({
                    name:user,
                    children:children
                })
            }

            return data;
        }

        $(function (){
            var ipChartsDom = document.getElementById('ip-charts');

            // 基于准备好的dom，初始化echarts实例
            var ipCharts = echarts.init(ipChartsDom);

            function renderEcharts(data) {
                var dateList = data.dateList;
                var lineList = data.lineList;
                var userList = data.userList;
                var dataMap = data.dataMap;

                var source = [
                    ['date']
                ];
                for (const dateItem of dateList) {
                    source[0].push(dateItem);
                }

                var lastDate = dateList[6];
                var lastTotal = 0;
                var series = [];
                for (const userItem of lineList) {
                    let userDataList = dataMap[userItem];

                    lastTotal += userDataList[6];

                    series.push({
                        type: 'line',
                        seriesLayoutBy: 'row',
                        emphasis: {focus: 'series'},
                        itemStyle: {normal: {label: {show: true}}}
                    });

                    let line = [userItem];
                    for (const userData of userDataList) {
                        line.push(userData);
                    }
                    source.push(line);
                }

                var sunburstData = sunburst(dataMap, userList, 6);

                // 指定图表的配置项和数据
                var option = {
                    title: [
                        {
                            text: 'SEO IP 统计',
                            left: 'center',
                            top: '0%'
                        },
                        {
                            text: lastDate + '总 IP 数: '+lastTotal,
                            left: 'center',
                            top: '3%'
                        }
                    ],
                    legend: {
                        top: 'center'
                    },
                    color: [
                        "#fac858",
                        "#91cc75",
                        "#ee6666",
                        "#5470c6",
                        "#73c0de",
                        "#3ba272",
                        "#fc8452",
                        "#9a60b4",
                        "#ea7ccc",
                    ],
                    tooltip: {
                        trigger: 'axis',
                        showContent: false
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    dataset: {
                        source: source
                    },
                    xAxis: {type: 'category'},
                    yAxis: {gridIndex: 0},
                    grid: {top: '55%'},
                    series: [
                        ...series,
                        {
                            type: 'sunburst',
                            id: 'sunburst',
                            radius: [0, '40%'],
                            center: ['50%', '28%'],
                            label: {
                                formatter: '{b}: {@' + lastDate + '} ({d}%)'
                            },
                            data: sunburstData,
                            emphasis: {focus: 'data'},
                            encode: {
                                itemName: 'date',
                                value: lastDate,
                                tooltip: lastDate
                            }
                        }
                    ]
                };

                console.log(option);

                ipCharts.on('updateAxisPointer', function (event) {
                    var xAxisInfo = event.axesInfo[0];
                    if (xAxisInfo) {
                        var itemIndex = xAxisInfo.value;
                        var dimension = itemIndex + 1;

                        var itemTotal = 0;
                        for (const dataMapKey in dataMap) {
                            itemTotal += dataMap[dataMapKey][itemIndex];
                        }

                        ipCharts.setOption({
                            title: [
                                {},
                                {
                                    text: dateList[itemIndex] + '总 IP 数: '+itemTotal,
                                }
                            ],
                            series: {
                                id: 'sunburst',
                                label: {
                                    formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                                },
                                data: sunburst(dataMap, userList, itemIndex),
                                encode: {
                                    value: dimension,
                                    tooltip: dimension
                                }
                            }
                        });
                    }
                });

                ipCharts.setOption(option);
            }

            ipCharts.showLoading();
            $.ajax({
                url: "/seo/data/lastData",
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data.status === 200){
                        renderEcharts(data.data);
                    }
                },
                complete:function (){
                    ipCharts.hideLoading();
                }
            });
        });
    </script>
</@PageFrame>