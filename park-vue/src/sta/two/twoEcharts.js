import echarts from 'echarts'
/**  两个统计图1
 * 两种数据统计,柱状图+折线图    这是横坐标一样,纵坐标有两种
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {show: boolean, textStyle: {color: string}}, axisLine: {lineStyle: {color: string}, show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, nameTextStyle: {color: string}}, {axisLabel: {formatter: string, show: boolean, textStyle: {color: string}}, axisLine: {show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, position: string, type: string, nameTextStyle: {color: string}}, {axisLabel: {show: boolean}, min: number, gridIndex: number, max: number, axisLine: {show: boolean}, splitArea: {areaStyle: {color: [string, string]}, show: boolean}, splitLine: {show: boolean}, axisTick: {show: boolean}, splitNumber: number, type: string}], xAxis: {axisLabel: {show: boolean, textStyle: {color: string}}, data: string[], axisLine: {lineStyle: {color: string}, show: boolean}, axisTick: {show: boolean}}, grid: {top: string, bottom: string}, legend: {data: [string, string], top: string, textStyle: {color: string}}, series: [{symbol: string, areaStyle: {color: string}, lineStyle: {color: string}, data, symbolSize: number, name: string, itemStyle: {color: string}, type: string, showAllSymbol: boolean, yAxisIndex: number, smooth: boolean}, {barWidth: number, data, name: string, itemStyle: {normal: {color: *}}, type: string}], tooltip: {axisPointer: {label: {show: boolean}, type: string}, trigger: string}, title: {top: string, left: string, text: string, textStyle: {color: string, fontSize: number, align: string}}}}
 * @param data
 * @param name
 */
export const histogramLineOptionOne = (data,name) => {
    const legendData = ["销售数量", "销售额"];
    const xData = data.xData;
    const yLeftData = data.yOneData;
    const yRightData = data.yTwoData;
    return  {
        title: {
            text: '销售额-销售数量',
            textStyle: {
                align: 'center',
                color: '#fff',
                fontSize: 20,
            },
            top: '3%',
            left: '10%',
        },
        backgroundColor: '#0f375f',
        grid: {
            top: "25%",
            bottom: "10%"//也可设置left和right设置距离来控制图表的大小
        },
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "shadow",
                label: {
                    show: true
                }
            }
        },
        legend: {
            data: legendData,
            top: "15%",
            textStyle: {
                color: "#ffffff"
            }
        },
        xAxis: {
            data: xData,
            axisLine: {
                show: true, //隐藏X轴轴线
                lineStyle: {
                    color: '#01FCE3'
                }
            },
            axisTick: {
                show: true //隐藏X轴刻度
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ebf8ac" //X轴文字颜色
                }
            },

        },
        yAxis: [{
            type: "value",
            name: "个",
            nameTextStyle: {
                color: "#ebf8ac"
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: true
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFFFFF'
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ebf8ac"
                }
            },

        },
            {
                type: "value",
                name: "销售额",
                nameTextStyle: {
                    color: "#ebf8ac"
                },
                position: "right",
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisLabel: {
                    show: true,
                    formatter: "{value}", //右侧Y轴文字显示
                    textStyle: {
                        color: "#ebf8ac"
                    }
                }
            },
            {
                type: "value",
                gridIndex: 0,
                min: 50,
                max: 100,
                splitNumber: 8,
                splitLine: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                splitArea: {
                    show: true,
                    areaStyle: {
                        color: ["rgba(250,250,250,0.0)", "rgba(250,250,250,0.05)"]
                    }
                }
            }
        ],
        series: [{
            name: "销售数量",
            type: "line",
            yAxisIndex: 1, //使用的 y 轴的 index，在单个图表实例中存在多个 y轴的时候有用
            smooth: true, //平滑曲线显示
            showAllSymbol: true, //显示所有图形。
            symbol: "circle", //标记的图形为实心圆
            symbolSize: 10, //标记的大小
            itemStyle: {
                //折线拐点标志的样式
                color: "#058cff"
            },
            lineStyle: {
                color: "#058cff"
            },
            areaStyle:{
                color: "rgba(5,140,255, 0.2)"
            },
            data: yLeftData
        },
            {
                name: "销售额",
                type: "bar",
                barWidth: 15,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: "#00FFE3"
                        },
                            {
                                offset: 1,
                                color: "#4693EC"
                            }
                        ])
                    }
                },
                data: yRightData
            }
        ]
    };
};


/**
 * 两个统计图2
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {formatter: string, color: string, textStyle: {fontSize: number}}, axisLine: {lineStyle: {color: string}, show: boolean}, type: string}], xAxis: [{axisLabel: {show: boolean, textStyle: {color: string, fontSize: number}}, data, type: string, boundaryGap: boolean}], calculable: boolean, legend: {data: [string, string], textStyle: {color: string}}, series: [{data, lineStyle: {normal: {shadowOffsetY: number, color: {globalCoord: boolean, colorStops: [{offset: number, color: string}, {offset: number, color: string}], type: string}, shadowBlur: number, width: number, shadowColor: string}}, name: string, itemStyle: {normal: {borderColor: string, color: string, borderWidth: number}}, type: string, markPoint: {data: [{name: string, type: string}, {name: string, type: string}]}}, {data: number[], lineStyle: {normal: {shadowOffsetY: number, color: {globalCoord: boolean, colorStops: [{offset: number, color: string}, {offset: number, color: string}], type: string}, shadowBlur: number, width: number, shadowColor: string}}, name: string, itemStyle: {normal: {borderColor: string, color: string, borderWidth: number}}, type: string, markPoint: {data: [{name: string, type: string}, {name: string, type: string}]}}], tooltip: {trigger: string}, toolbox: {feature: {saveAsImage: {show: boolean}, restore: {show: boolean}, magicType: {show: boolean, type: [string, string]}, dataView: {show: boolean, readOnly: boolean}, mark: {show: boolean}}, show: boolean}, title: {}}}
 */
export const lineLineOptionOne = (data,name) => {
    const legendData = ['信息科技', '大消费'];
    const xData =  ['一月份', '二月份', '三月份', '四月份', '五月份', '六月份'];
    const yOneData = [1, 4, 2, 5, 1, 2, 1];
    const yTwoData = [0, 1, 1, 3, 2];
    return  {
        backgroundColor: '#01004C',
        title: {

        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: legendData,
            textStyle: {
                color: '#fff'
            }
        },
        toolbox: {
            show: true,
            feature: {
                mark: {
                    show: true
                },
                dataView: {
                    show: true,
                    readOnly: false
                },
                magicType: {
                    show: true,
                    type: ['line', 'bar']
                },
                restore: {
                    show: true
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        calculable: true,
        xAxis: [{
            type: 'category',
            boundaryGap: false,
            data:xData,
            axisLabel: {
                show: true,
                textStyle: {
                    color: "#ffffff", //X轴文字颜色
                    fontSize: 16
                }
            },

        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value} ',
                color: '#ffffff',
                textStyle: {
                    fontSize: 16
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "#ccc"
                },
            },


        }],
        series: [{
            name: '信息科技',
            type: 'line',
            data: yOneData,
            lineStyle: {
                normal: {
                    width: 8,
                    color: {
                        type: 'linear',

                        colorStops: [{
                            offset: 0,
                            color: '#57BD9F' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#19BC8C' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    shadowColor: 'rgba(72,216,191, 0.3)',
                    shadowBlur: 6,
                    shadowOffsetY: 10
                }
            },
            itemStyle: {
                normal: {
                    color: '#57BD9F',
                    borderWidth: 10,
                    borderColor: "#57BD9F"
                }
            },
            markPoint: {
                data: [{
                    type: 'max',
                    name: '最大值'
                },
                    {
                        type: 'min',
                        name: '最小值'
                    }
                ]
            },

        },
            {
                name: '大消费',
                type: 'line',
                data: yTwoData,
                lineStyle: {
                    normal: {
                        width: 8,
                        color: {
                            type: 'linear',

                            colorStops: [{
                                offset: 0,
                                color: '#7597EB' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#638BEE' // 100% 处的颜色
                            }],
                            globalCoord: false // 缺省为 false
                        },
                        shadowColor: 'rgba(72,216,191, 0.3)',
                        shadowBlur: 6,
                        shadowOffsetY: 10
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#7597EB',
                        borderWidth: 10,
                        borderColor: "#7597EB"
                    }
                },
                markPoint: {
                    data: [{
                        type: 'max',
                        name: '最大值'
                    },
                        {
                            type: 'min',
                            name: '最小值'
                        }
                    ]
                },

            }
        ]
    };
};


/**
 * 两个统计图3
 * 两种数据统计,柱状图+折线图    这是横坐标一样,纵坐标有两种
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {show: boolean, textStyle: {color: string}}, axisLine: {lineStyle: {color: string}, show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, nameTextStyle: {color: string}}, {axisLabel: {formatter: string, show: boolean, textStyle: {color: string}}, axisLine: {show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, position: string, type: string, nameTextStyle: {color: string}}, {axisLabel: {show: boolean}, min: number, gridIndex: number, max: number, axisLine: {show: boolean}, splitArea: {areaStyle: {color: [string, string]}, show: boolean}, splitLine: {show: boolean}, axisTick: {show: boolean}, splitNumber: number, type: string}], xAxis: {axisLabel: {show: boolean, textStyle: {color: string}}, data: string[], axisLine: {lineStyle: {color: string}, show: boolean}, axisTick: {show: boolean}}, grid: {top: string, bottom: string}, legend: {data: [string, string], top: string, textStyle: {color: string}}, series: [{symbol: string, areaStyle: {color: string}, lineStyle: {color: string}, data, symbolSize: number, name: string, itemStyle: {color: string}, type: string, showAllSymbol: boolean, yAxisIndex: number, smooth: boolean}, {barWidth: number, data, name: string, itemStyle: {normal: {color: *}}, type: string}], tooltip: {axisPointer: {label: {show: boolean}, type: string}, trigger: string}, title: {top: string, left: string, text: string, textStyle: {color: string, fontSize: number, align: string}}}}
 * @param data
 * @param name
 */
export const histogramLineOptionTwo = (data,name) => {
    const xData = ['2016','2017','2018','2019','2020'];
    const histogramData = ['70','55','50','53','72'];
    const lineData = ['40','75','60','68','80'];
    const dataName=['数量','金额'];
//计算最大值
    const maxNum = Math.max.apply(null, histogramData);
    const maxNum1 = Math.max.apply(null, lineData);
    const maxLen = Math.pow(10, String(Math.ceil(maxNum)).length - 2);
    const maxLen1 = Math.pow(10, String(Math.ceil(maxNum1)).length - 2);
    let max;
    let max1;
    if (maxNum >= 5) {
         max = Math.ceil(maxNum / (10 * maxLen)) * maxLen * 10;
    } else {
         max = 5;
    }
    if (maxNum1 >= 5) {
        max1 = Math.ceil(maxNum1 / (10 * maxLen1)) * maxLen1 * 10;
    } else {
        max1 = 5;
    }
    return {
        backgroundColor:'#072685',
        grid: {
            top: '21%',
            right: '8%',
            left: '8%',
            bottom: '16%'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'none'
            },
            formatter: '{b0}年<br/>{a0}: {c0}个<br/>{a1}: {c1}万元',
        },
        legend: {
            data: dataName,
            left: "2%",
            top:'2%',
            icon: "roundRect",
            itemWidth: 15, // 设置宽度
            itemHeight: 15, // 设置高度
            textStyle: {
                color: "#666666",
                fontSize: 13,
                padding: [2, 0, 0, 0]
            },
        },
        xAxis: [{
            data: xData,
            axisLabel: {
                margin: 10,
                color: '#82AFC6',
                textStyle: {
                    fontSize: 12
                },
            },
            axisLine: {
                lineStyle: {
                    color: '#102E74',
                }
            },
            axisTick: {
                show: false
            },
        }],
        yAxis: [{
            type: 'value',
            min: 0,
            max: max, // 计算最大值
            interval: max / 5, // 平均分为5份
            splitNumber: 5,
            name: '数量（个）',
            nameTextStyle: {
                color: '#82AFC6',
                fontSize: 12,
                padding: [0, 0, -8, 100]
            },
            axisLabel: {
                formatter: function(value) {
                    let num;
                    num = value;
                    if (num && num != 'undefined' && num != 'null') {
                        let numS = num;
                        numS = numS.toString();
                        numS = numS.replace(/,/gi, '');
                        return numS;
                    } else {
                        return num;
                    }
                },
                color: '#82AFC6',
                textStyle: {
                    fontSize: 12
                },
            },
            axisLine: {
                show:false,
                lineStyle: {
                    color: '#3E5B7D',
                }
            },
            axisTick: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    color: '#102E74',
                }
            }
        }, {
            type: 'value',
            min: 0,
            max: max1, // 计算最大值
            interval: max1 / 5, // 平均分为5份
            splitNumber: 5,
            name: '金额（万元）',
            nameTextStyle: {
                color: '#82AFC6',
                fontSize: 12,
                padding: [0, 0, -8, -100]
            },
            axisLabel: {
                formatter: function(value) {
                    const num = value
                    if (num && num != 'undefined' && num != 'null') {
                        let numS = num;
                        numS = numS.toString();
                        numS = numS.replace(/,/gi, '');
                        return numS;
                    } else {
                        return num;
                    }
                },
                color: '#82AFC6',
                textStyle: {
                    fontSize: 12
                },
            },
            axisLine: {
                show:false,
                lineStyle: {
                    color: '#3E5B7D',
                }
            },
            axisTick: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    color: '#102E74',
                }
            }
        }],
        series: [{
            name: "数量",
            type: 'bar',
            data: histogramData,
            barWidth: '15px',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                        offset: 0,
                        color: '#1D82FF'
                    }, {
                        offset: 1,
                        color: '#00FFF6'
                    }], false),
                    barBorderRadius: [4, 4, 0, 0],
                }
            },
        }, {
            name: '金额',
            type: 'line',
            data: lineData,
            smooth: true,
            symbol: 'none',
            itemStyle: {
                normal: {
                    lineStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(255, 227, 168, 0)'
                        }, {
                            offset: 0.5,
                            color: 'rgba(255, 227, 168, 1)'
                        }, {
                            offset: 1,
                            color: 'rgba(255, 227, 168, 0)'
                        }, ]),
                        shadowColor: 'rgba(255, 120, 0,1)',
                        shadowBlur: 8
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(197, 106, 255, 0.6)'
                        }, {
                            offset: 0.6,
                            color: 'rgba(255, 120, 0, 0)'
                        }]),
                    }
                }
            }
        }]

    };
};


/**
 * 两个统计图4
 * 两种数据统计,柱状图+折线图    这是横坐标一样,纵坐标有两种
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {show: boolean, textStyle: {color: string}}, axisLine: {lineStyle: {color: string}, show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, nameTextStyle: {color: string}}, {axisLabel: {formatter: string, show: boolean, textStyle: {color: string}}, axisLine: {show: boolean}, name: string, splitLine: {show: boolean}, axisTick: {show: boolean}, position: string, type: string, nameTextStyle: {color: string}}, {axisLabel: {show: boolean}, min: number, gridIndex: number, max: number, axisLine: {show: boolean}, splitArea: {areaStyle: {color: [string, string]}, show: boolean}, splitLine: {show: boolean}, axisTick: {show: boolean}, splitNumber: number, type: string}], xAxis: {axisLabel: {show: boolean, textStyle: {color: string}}, data: string[], axisLine: {lineStyle: {color: string}, show: boolean}, axisTick: {show: boolean}}, grid: {top: string, bottom: string}, legend: {data: [string, string], top: string, textStyle: {color: string}}, series: [{symbol: string, areaStyle: {color: string}, lineStyle: {color: string}, data, symbolSize: number, name: string, itemStyle: {color: string}, type: string, showAllSymbol: boolean, yAxisIndex: number, smooth: boolean}, {barWidth: number, data, name: string, itemStyle: {normal: {color: *}}, type: string}], tooltip: {axisPointer: {label: {show: boolean}, type: string}, trigger: string}, title: {top: string, left: string, text: string, textStyle: {color: string, fontSize: number, align: string}}}}
 * @param data
 * @param name
 */
export const histogramLineOptionThree = (data,name) => {
    const xData = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];
    const histogramData = [232, 454, 345, 671, 552, 664, 657, 414, 655, 572, 766, 538];
    const lineData = [5, 6, 8, 8, 7, 7, 8, 5, 7, 8, 9, 6];
    const legendData = ["入库数", "增长率（%）"];
    return {
        backgroundColor: '#0F4583',
        legend: {
            data: legendData,
            top: 0,
            x: 'right',
            textStyle: {
                color: "#fff"
            }

        },
        tooltip: {},
        grid: {
            left: 40,
            right: 40,
            bottom: 45,
            top: 25,
        },
        xAxis: {
            data: xData,
            silent: false,
            splitLine: {
                show: false
            },
            axisLabel: {
                color: '#fff',
                interval: 0
            },
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#506D91'
                }
            },
            splitArea: {
                show: false
            }
        },
        yAxis: [{
            type: 'value',
            min: 0,
            max: 10,
            position: 'right',
            splitNumber: 10,
            // silent: false,
            axisTick: {
                show: false
            },
            axisLine: {
                show: false,

            },
            axisLabel: {
                color: '#fff'
            },
            splitLine: {
                show: false
            }
        }, {
            type: 'value',
            min: 0,
            max: 1000,
            position: 'left',
            splitNumber: 10,
            axisLine: {
                lineStyle: {
                    color: '#506D91'
                }
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#fff'
            },
            splitArea: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    color: '#16508C'
                }
            },

        }],
        series: [{
            name: '增长率（%）',
            type: 'line',
            stack: '总量',
            label: {
                normal: {
                    color: "#FFCA28",
                    show: true,
                    position: 'top'
                },
                emphasis: {
                    show: true,
                    fontSize: 22
                }
            },
            color:'#FFCA28',
            smooth:false,
            symbol:'circle',
            symbolSize:8,
            // markPoint:{
            //     symbol:'diamond'
            // },
            lineStyle: {
                normal: {
                    type:'solid',
                    color: "#FFCA28",
                    width: 2,
                    shadowColor: 'rgba(255,255,255,0.4)',
                    shadowBlur: 1,
                    shadowOffsetY: 1
                }
            },
            data: lineData
        }, {
            name: '入库数',
            type: 'bar',
            yAxisIndex: 1,
            barWidth: 18,
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            itemStyle: {
                normal: {
                    color: "#29B6F6"
                }
            },
            data: histogramData
        }]
    };
};

/**
 * 两个统计图5
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLine: {lineStyle: {color: string}, show: boolean}, splitArea: {show: boolean}, splitLine: {lineStyle: {color: string, type: string}}, splitNumber: number, type: string, nameTextStyle: {color: string}}], xAxis: [{data, axisLine: {lineStyle: {color: string}}, type: string}], series: [{data, lineStyle: {normal: {shadowOffsetY: number, color: {globalCoord: boolean, colorStops: [{offset: number, color: string}, {offset: number, color: string}], type: string}, shadowBlur: number, width: number, shadowColor: string}}, name: string, itemStyle: {normal: {borderColor: string, color: string, borderWidth: number}}, type: string, smooth: boolean}, {data, lineStyle: {normal: {shadowOffsetY: number, color: {globalCoord: boolean, colorStops: [{offset: number, color: string}, {offset: number, color: string}], type: string}, shadowBlur: number, width: number, shadowColor: string}}, name: string, itemStyle: {normal: {borderColor: string, color: string, borderWidth: number}}, type: string, smooth: boolean}], tooltip: {trigger: string}}}
 */
export const lineLineOptionTwo = (data,name) =>{
    const xData = ['2019-06', '2019-05', '2019-04', '2019-03', '2019-02', '2019-01', '2018-12', '2018-11', '2018-10', '2018-09', '2018-08', '2018-07'];
    const lineOneData= [3, 0, 13, 6, 15, 25,5, 11,12, 15,6, 9];
    const lineTwoData = [15, 12, 13, 16, 15, 10,15, 11,12, 12,0, 17];
    return {
        backgroundColor:'#0c3da6',
        tooltip: {
            trigger: 'axis',

        },
        xAxis: [{
            type: 'category',
            data: xData,
            axisLine: {
                lineStyle: {
                    color: "#fff",
                }
            }
        }],
        yAxis: [{
            type: 'value',
            splitNumber: 4,
            splitLine: {
                lineStyle: {
                    type: 'dashed',
                    color: '#2459ca'
                }
            },
            axisLine: {
                show: false,
                lineStyle: {
                    color: "#fff",

                },
            },
            nameTextStyle: {
                color: "#999",

            },
            splitArea: {
                show: false
            }
        }],
        series: [{
            name: '课时',
            type: 'line',
            data: lineOneData,
            lineStyle: {
                normal: {
                    width: 4,
                    color: {
                        type: 'linear',

                        colorStops: [{
                            offset: 0,
                            color: '#A9F387' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#48D8BF' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    shadowColor: 'rgba(72,216,191, 0.3)',
                    shadowBlur: 5,
                    shadowOffsetY: 10
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff',
                    borderWidth: 5,
                    /*shadowColor: 'rgba(72,216,191, 0.3)',
                    shadowBlur: 100,*/
                    borderColor: "#A9F387"
                }
            },
            smooth: true
        },{
            name: '课时2',
            type: 'line',
            data: lineTwoData,
            lineStyle: {
                normal: {
                    width: 4,
                    color: {
                        type: 'linear',

                        colorStops: [{
                            offset: 0,
                            color: '#507afe' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: '#7e3aff' // 100% 处的颜色
                        }],
                        globalCoord: false // 缺省为 false
                    },
                    shadowColor: 'rgba(78,124,254, 0.3)',
                    shadowBlur: 5,
                    shadowOffsetY: 10
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff',
                    borderWidth: 5,
                    /*shadowColor: 'rgba(95,100,255, 0.3)',
                    shadowBlur: 100,*/
                    borderColor: "#7840ff"
                }
            },
            smooth: true
        }
        ]
    };
};
