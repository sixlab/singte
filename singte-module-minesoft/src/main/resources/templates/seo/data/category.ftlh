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
        $(function (){
            var ipChartsDom = document.getElementById('ip-charts');

            // 基于准备好的dom，初始化echarts实例
            var ipCharts = echarts.init(ipChartsDom);

            function renderEcharts(data) {
                var itemList = data.itemList;
                var dateList = data.dateList;
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
                for (const seoItem of itemList) {
                    let uid = seoItem.uid;
                    let userDataList = dataMap[uid];

                    lastTotal += userDataList[6].ip;

                    series.push({
                        type: 'line',
                        seriesLayoutBy: 'row',
                        emphasis: {focus: 'series'},
                        itemStyle: {normal: {label: {show: true}}}
                    });

                    let line = [seoItem.user];
                    for (const userData of userDataList) {
                        line.push(userData.ip);
                    }
                    source.push(line);
                }

                // 指定图表的配置项和数据
                var option = {
                    title: [
                        {
                            text: '${category?cap_first}导流 IP 统计',
                            left: 'center',
                            top: '2%'
                        },
                        {
                            text: lastTotal,
                            subtext: lastDate + '总 IP 数',
                            left: 'center',
                            top: '25%'
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
                            type: 'pie',
                            id: 'pie',
                            radius: ['30%', '20%'],
                            center: ['50%', '28%'],
                            emphasis: {focus: 'data'},
                            label: {
                                formatter: '{b}: {@' + lastDate + '} ({d}%)'
                            },
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
                            itemTotal += dataMap[dataMapKey][itemIndex].ip;
                        }

                        ipCharts.setOption({
                            title: [
                                {},
                                {
                                    text: itemTotal,
                                    subtext: dateList[itemIndex] + '总 IP 数',
                                }
                            ],
                            series: {
                                id: 'pie',
                                label: {
                                    formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                                },
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
                url: "/seo/data/category/${category}",
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