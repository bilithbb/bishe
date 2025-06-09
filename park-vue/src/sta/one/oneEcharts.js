/**
 * 可视化网址： https://www.makeapie.cn/
 */


import echarts from 'echarts'
/**
 * 单个弯曲折线图(带平均值,但是只显示最大最小值的)
 * @returns {{yAxis: {axisLabel: {textStyle: {color: string, fontSize: number}}, axisLine: {lineStyle: {color: string, width: number}}, name: string}, xAxis: {axisLabel: {textStyle: {color: string, fontSize: number}}, data: *, axisLine: {lineStyle: {color: string, width: number}}, name: string, splitLine: {lineStyle: {color: string}}}, series: [{data: *, lineStyle: {normal: {shadowOffsetY: number, shadowBlur: number, width: number, shadowColor: string}, width: number}, itemStyle: {normal: {color: string}}, type: string, markLine: {data: [{name: string, type: string}]}, markPoint: {data: [{name: string, type: string}, {name: string, type: string}], itemStyle: {normal: {color: string}}}, smooth: boolean}], title: {top: string, left: string, text: *, textStyle: {color: string, fontSize: number, align: string}}}}
 * @param data
 * @param name
 */
export const curveLineOptionOne = (data,name) => {
    // name.title ='1';
    // name.xTitle = '2';
    // name.yTitle = '3'
    const title = 1;
    const xTitle = 2;
    const yTitle = 3;
    const xData = data.xData;
    const yData = data.yData;
    return {
        // backgroundColor: '#00265f',
        title: {
            text: '审理程序分布',
            textStyle: {
                fontSize: 12,
                fontWeight: 400,
                color:'white'
            },
        },
        xAxis: {
            name: xTitle,
            data: xData,
            splitLine: {
                lineStyle: {
                    color: '#D4DFF5'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#289bd3',
                    width: 2
                }
            },
            axisLabel: {
                textStyle: {
                    fontSize: 14,
                    color: '#333'
                }
            }
        },
        yAxis: {
            name: yTitle,
            axisLine: {
                lineStyle: {
                    color: '#289bd3',
                    width: 2
                }
            },
            axisLabel: {
                textStyle: {
                    fontSize: 14,
                    color: '#333'
                }
            },
            // max: 800
        },
        series: [{
            type: 'line',
            smooth: true,
            data: yData,
            itemStyle: {
                normal: {
                    color: '#2ec7c9'
                }

            },
            lineStyle: {
                width: 5,
                normal: {
                    width: 2,
                    shadowOffsetY: 4,
                    shadowBlur: 4,
                    shadowColor: 'rgba(0,0,0,.08)'

                },
            },
            markPoint: {
                itemStyle: {
                    normal: {
                        color: '#2ec7c9'
                    }

                },
                data: [{
                    type: 'max',
                    name: '最大值'
                }, {
                    type: 'min',
                    name: '最小值'
                }]
            },
            markLine: {
                data: [
                    { type: 'average', name: '平均值' }
                ]
            }
        }]
    };
};

/**
 * 单个弯曲折线图(鼠标放上去每个都可以显示的那种)
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {color: string}, min: number, max: number, axisLine: {lineStyle: {color: string}}, name: *, axisTick: {show: boolean}, splitLine: {lineStyle: {color: string}, show: boolean}, type: string}], xAxis: [{axisLabel: {textStyle: {color: string, width: number, fontSize: number}}, data: *, axisLine: {lineStyle: {color: string}}, name: null, show: boolean, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, boundaryGap: boolean}], color: [string], grid: {top: string, left: string, bottom: string, right: string, containLabel: boolean}, series: [{lineStyle: {normal: {shadowOffsetY: number, shadowBlur: number, width: number, shadowColor: string}}, data: *, symbolSize: number, name: string, type: string, smooth: boolean}], tooltip: {trigger: string}, title: {top: string, left: string, text: *, textStyle: {color: string, fontSize: number, align: string}}}}
 */
export const curveLineOptionTwo = (data,name) =>{
    const title = '1'
    const xTitle = 2;
    const yTitle = 3;
    const xData = data.xData;
    const yData = data.yData;
    return {
        title: {
            text: title,
            textStyle: {
                align: 'center',
                color: 'black',
                fontSize: 20,
            },
            top: '0%',
            left: '4%',
        },
        backgroundColor:'#fff',
        tooltip: {
            trigger: 'axis',
            //				        trigger: 'yAxis',
        },
        color: ['#138af5'],
        grid: {
            left: '2%',
            right: '2%',
            top: '10%',
            bottom: '13%',
            containLabel: true
        },
        xAxis: [{
            name: null,
            show: true,
            type: 'category',
            splitLine: {
                show: false
            },
            data: xData,
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#999',
                }
            },

            axisLabel: {  //横坐标相关样式
                textStyle: {
                    fontSize: 14,
                    color: '#333',
                    width:2
                }
            },
            boundaryGap: true,
        }],
        yAxis: [{
            name: yTitle,
            type: 'value',
            min: 0,
            max: 250,
            axisLabel: {
                color: '#999',
            },
            axisLine: {
                lineStyle: {
                    color: '#999',
                }
            },
            axisTick: {
                show: false
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#999',
                }
            }
        }
        ],
        series: [
            {
                name: '回路',
                type: 'line',
                symbolSize: 8,
                lineStyle: {
                    normal: {
                        width: 2,
                        shadowOffsetY: 4,
                        shadowBlur: 4,
                        shadowColor: 'rgba(0,0,0,.08)'

                    },
                },
                smooth: true,
                data: yData,
            }
        ]
    };
}

/**
 * 单个弯曲折线图
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {color: string}, min: number, max: number, axisLine: {lineStyle: {color: string}}, name: *, axisTick: {show: boolean}, splitLine: {lineStyle: {color: string}, show: boolean}, type: string}], xAxis: [{axisLabel: {textStyle: {color: string, width: number, fontSize: number}}, data: *, axisLine: {lineStyle: {color: string}}, name: null, show: boolean, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, boundaryGap: boolean}], color: [string], grid: {top: string, left: string, bottom: string, right: string, containLabel: boolean}, series: [{lineStyle: {normal: {shadowOffsetY: number, shadowBlur: number, width: number, shadowColor: string}}, data: *, symbolSize: number, name: string, type: string, smooth: boolean}], tooltip: {trigger: string}, title: {top: string, left: string, text: *, textStyle: {color: string, fontSize: number, align: string}}}}
 */

export const curveLineOptionThree = (data,name) =>{
    const xData = data.xData;
    const yData = data.yData;
    return {
        backgroundColor: '#00265f',
        title: {
            text: '用户性别分布',
            textStyle: {
                fontSize: 12,
                fontWeight: 400,
                color:'white'
            },
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: [{
            type: 'category',
            data: xData,
            axisLine: {
                lineStyle: {
                    color: "#999"
                }
            }
        }],
        yAxis: [{
            type: 'value',
            splitNumber: 4,
            splitLine: {
                lineStyle: {
                    type: 'dashed',
                    color: '#DDD'
                }
            },
            axisLine: {
                show: false,
                lineStyle: {
                    color: "#333"
                },
            },
            nameTextStyle: {
                color: "#999"
            },
            splitArea: {
                show: false
            }
        }],
        series: [{
            name: '课时',
            type: 'line',
            data: yData,
            lineStyle: {
                normal: {
                    width: 8,
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
                    shadowBlur: 10,
                    shadowOffsetY: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff',
                    borderWidth: 10,
                    /*shadowColor: 'rgba(72,216,191, 0.3)',
                    shadowBlur: 100,*/
                    borderColor: "#A9F387"
                }
            },
            smooth: true
        }]
    };
};

/**
 * 单个弯曲动态流光折线图
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {color: string}, min: number, max: number, axisLine: {lineStyle: {color: string}}, name: *, axisTick: {show: boolean}, splitLine: {lineStyle: {color: string}, show: boolean}, type: string}], xAxis: [{axisLabel: {textStyle: {color: string, width: number, fontSize: number}}, data: *, axisLine: {lineStyle: {color: string}}, name: null, show: boolean, splitLine: {show: boolean}, axisTick: {show: boolean}, type: string, boundaryGap: boolean}], color: [string], grid: {top: string, left: string, bottom: string, right: string, containLabel: boolean}, series: [{lineStyle: {normal: {shadowOffsetY: number, shadowBlur: number, width: number, shadowColor: string}}, data: *, symbolSize: number, name: string, type: string, smooth: boolean}], tooltip: {trigger: string}, title: {top: string, left: string, text: *, textStyle: {color: string, fontSize: number, align: string}}}}
 */

export const curveLineOptionFour = (data2,name) =>{
    //数据
    const XName = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
    const data1 = [67, 97, 51, 338, 32, 11, 1];
    const img = [
        'image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABRCAYAAABFTSEIAAAACXBIWXMAAAsSAAALEgHS3X78AAAEp0lEQVR42u3cz4sjRRTA8W9Vd3Vn8mMmjj9WQWSRZQ+CsH+B7MnDIgiCd0E8CYJ/gOAIelo8ehUP/gF6WLw5/gMueFP2sIcF0dHd2Z1kknR11fOQZJJJMtlZd03H7HtQpNOTnpn+8Lrm1etmjIig8e/DKoECKqACKqCGAiqgAiqghgIqoAIqoIYCKqACKqCGAiqgAiqghgIqoAJudKTr+osZMNPvBUQBHwHsPF9fB9R0DeHMOQ6T6WOrhEzXBM4swDOL0M6CrArRVoq3t2dGUIb9fTvatg8ZZup1PDBgzPmy98mey6qfzjLz2WaWjEUZKEvGyi9nWyneMOvGIyFQo2Sbg4MUSChpU9IeTTUpJdsEajPZOJeJG5uBZj7rLLduWS5dGm6XNLEELOFUFj54ACJCaychkpDSASK3bwsXL0YgVpWJKwM0iy9Zy8HdGru7jvt3Pbu7w0wES7drTwAbjTHMGCsQcIAnYTC1/wRx0wEnl27JNgZI8HQ6Kc1mQq83RNzaMjPzXqDbjTQaJRFLxIyyMSxAXEkWrhrQzAAmo5HOjCQf7jflILxOkohL+aUPgV4vEGNJo+E5PAy02+UIMEwBxo0CPDP7Dg5SnEtpt1PA0e87XO25FOoh8IYIH2Y5b45RzGAQBiIltZoHxqMcjbksXAVgdc2EQMYzzzdotyeZWKuleULXJtwT4SODfC2QCWR+IF9KnjuX1Xbo99Op7LVE8iXlz0YBTk5SyLEEjo5OLuccEoFUvHfO+reuUPx4zftXAIcx1hdcF+/TvFab4A0Bs0VwqyhpVnkJT89/Q4DDQ0e77YCMwIUsFMeFZD856699URRvX4nxE4A/jbnxXp7v4Zw3ReGNSDHI8wFQjIafuoyn58L/fB6sth/Ybg9fez2TRC6QZcZYvgHsazF+MP7YCyLXcM7gvSXLDGBqYDg+NhwdmSpPoTrAkub0W+f4FSB1fDucIunMHSLpO8WAH0rSy8u+19MBCHB4OHzd2pI+CEUhpigEiN+l6WcdY252jLn5s7Wf472ImPcN8pUl/tEHoV4XWq1Ke4KrLmPsTA3oODpytFoOyJKSyzHyMSIxteWngMW5cSEdDJQUhTdZVgxOz3/+jFJm4+bA2e5JpNU6WZ4Fw99JwnWMKccwpeddP+B7GZTNUPKqybJy0O+Hs1YfMz9swwvpB8fbGDG0GuGkkK7V0hxSmZQpABI8l2z0v3sJf50qpAMJCd2qCulql3LD1lRGQjm7lEsDz0rkxTQOfiPPxUBcuJTbbhss/Y1eyi3NwsmKInmkZsKk5gtPUzNhvp11507CSy/X6XYStpvFudpZw1ZWIOF4Cq6SdtbKbioJyAhRTu3u9yMJXerN+ugvaQQsjcZ8Q3VnZwxlSDhe1lB9GjrSw5b+1avT8+Jw+979nNaOI6U3KpTrWAosxVQmygK4ld8X0ZtK/7eViExD7O1NQPb3T7fsl4/4sBpwYzPwjFbTo95Yl9l9Vd1YN1X/147HebSjary1AHyc5qc+XLQEQx9ve8Kg6xr6hKoCKqACKqCGAiqgAiqghgIqoAIqoIYCKqACKqCGAiqgAiqghgIq4JrHP8fEWV8FMTmOAAAAAElFTkSuQmCC',
        'image://data:image/png;base64,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',
        'image://data:image/png;base64,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',
        'image://data:image/png;base64,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'
    ];

    const data = [{
        coords: [
            ['周一', 67],
            ['周二', 97],
            ['周三', 51],
            ['周四', 338],
            ['周五', 32],
            ['周六', 11],
            ['周日', 1],
        ]
    }];
    return {
        backgroundColor: '#0e2147',
        grid: {
            left: '5%',
            top: '5%',
            bottom: '5%',
            right: '5%',
        },
        legend: {
            type: "scroll",
            data: "来电量",
            itemWidth: 18,
            itemHeight: 12,
            textStyle: {
                color: "#00ffff",
                fontSize: 14
            },
        },
        yAxis: [{
            type: 'value',
            position: 'left',
            nameTextStyle: {
                color: '#00FFFF'
            },
            splitLine: {
                lineStyle: {
                    type: 'dashed',
                    color: 'rgba(135,140,147,0.8)'
                }
            },
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                formatter: '{value}',
                color: '#fff',
                fontSize: 14
            }
        }, ],
        xAxis: [{
            type: 'category',
            axisTick: {
                show: false
            },
            axisLine: {
                show: false,
                lineStyle: {
                    color: '#0696f9',
                }
            },
            axisLabel: {
                inside: false,
                textStyle: {
                    color: '#fff', // x轴颜色
                    fontWeight: 'normal',
                    fontSize: '14',
                    lineHeight: 22
                }

            },
            data: XName,
        }, ],
        series: [{
            symbolSize: 150,
            symbol: img[2],
            name: '小灯光',
            type: "line",
            data: data1,
            itemStyle: {
                normal: {
                    borderWidth: 5,
                    color: '#0696f9',
                }
            }
        },
            {
                name: '滑行的光点',
                type: 'lines',
                coordinateSystem: 'cartesian2d',
                symbolSize: 30,
                polyline: true,
                effect: {
                    show: true,
                    trailLength: 0,
                    // symbol: "arrow",
                    period: 10, //光点滑动速度
                    symbolSize: 150,
                    symbol: img[0]
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        opacity: 0.6,
                        curveness: 0.2
                    }
                },
                data: data
            }
        ],
    };
}


/**
 * 柱状图1
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {formatter: string, color: string}, axisLine: {show: boolean}, splitLine: {lineStyle: {color: string}}}], xAxis: [{axisLabel: {margin: number, color: string, textStyle: {fontSize: number}}, data, axisLine: {lineStyle: {color: string}}, type: string}], grid: {top: string, left: string, bottom: string, right: string}, series: [{barWidth: string, data, itemStyle: {normal: {color: *, shadowBlur: number, barBorderRadius: number[], shadowColor: string}}, label: {normal: {formatter: string, backgroundColor: string, borderRadius: number, distance: number, show: boolean, width: number, rich: {a: {color: string, align: string}, b: {borderColor: string, borderWidth: number, width: number, align: string, height: number}, d: {color: string}}, lineHeight: number, position: [string, string], height: number}}, type: string}], tooltip: {axisPointer: {type: string}, trigger: string}}}
 */
export const histogramOptionOne = (data,name) =>{
    const xData = data.xData;
    const yData = data.yData;
    return {
        backgroundColor: '#00265f',
        title: {
            text: '近七条销售额',
            textStyle: {
                fontSize: 12,
                fontWeight: 400,
                color:'white'
            },
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            top: '15%',
            right: '3%',
            left: '5%',
            bottom: '12%'
        },
        xAxis: [{
            type: 'category',
            data: xData,
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,0.12)'
                }
            },
            axisLabel: {
                margin: 10,
                color: '#e2e9ff',
                textStyle: {
                    fontSize: 14
                },
            },
        }],
        yAxis: [{
            axisLabel: {
                formatter: '{value}',
                color: '#e2e9ff',
            },
            axisLine: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,0.12)'
                }
            }
        }],
        series: [{
            type: 'bar',
            data: yData,
            barWidth: '20px',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgba(0,244,255,1)' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: 'rgba(0,77,167,1)' // 100% 处的颜色
                    }], false),
                    barBorderRadius: [30, 30, 30, 30],
                    shadowColor: 'rgba(0,160,221,1)',
                    shadowBlur: 4,
                }
            },
            label: {
                normal: {
                    show: true,
                    lineHeight: 21,
                    width: 100,
                    height: 25,
                    backgroundColor: 'rgba(0,160,221,0.1)',
                    borderRadius: 200,
                    position: ['-5', '-40'],
                    distance: 1,
                    formatter: [
                        '    {d|●}',
                        ' {a|{c}} \n',
                        '    {b|}'
                    ].join(''),
                    rich: {
                        d: {
                            color: '#3CDDCF',
                        },
                        a: {
                            color: '#fff',
                            align: 'center',
                        },
                        b: {
                            width: 1,
                            height: 15,
                            borderWidth: 1,
                            borderColor: '#234e6c',
                            align: 'left'
                        },
                    }
                }
            }
        }]
    };

};

/**
 * 柱状图2
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: [{axisLabel: {formatter: string, color: string}, axisLine: {show: boolean}, splitLine: {lineStyle: {color: string}}}], xAxis: [{axisLabel: {margin: number, color: string, textStyle: {fontSize: number}}, data, axisLine: {lineStyle: {color: string}}, type: string}], grid: {top: string, left: string, bottom: string, right: string}, series: [{barWidth: string, data, itemStyle: {normal: {color: *, shadowBlur: number, barBorderRadius: number[], shadowColor: string}}, label: {normal: {formatter: string, backgroundColor: string, borderRadius: number, distance: number, show: boolean, width: number, rich: {a: {color: string, align: string}, b: {borderColor: string, borderWidth: number, width: number, align: string, height: number}, d: {color: string}}, lineHeight: number, position: [string, string], height: number}}, type: string}], tooltip: {axisPointer: {type: string}, trigger: string}}}
 */
export const histogramOptionTwo = (data,name) =>{
    const colorList = ['#f36c6c', '#e6cf4e', '#20d180', '#0093ff'];
    const datas = [{
        'value': 36,
        'name': '系列一'
        },
        {
            'value': 54,
            'name': '系列二'
        },
        {
            'value': 29,
            'name': '系列三'
        },
        {
            'value': 25,
            'name': '系列四'
        },
        {
            'value': 55,
            'name': '系列五'
        },
        {
            'value': 69,
            'name': '系列6'
        },
        {
            'value': 75,
            'name': '系列7'
        },
        {
            'value': 85,
            'name': '系列8'
        }
    ];
    let maxArr = (new Array(datas.length)).fill(100);
    return  {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            show: false
        },
        grid: {
            left: 0,
            right:0,
            containLabel:true
        },
        xAxis: {
            show: false,
            type: 'value'

        },
        yAxis: [{
            type: 'category',
            inverse: true,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisPointer: {
                label: {
                    show: true,
                    margin: 30
                }
            },
            data: datas.map(item => item.name),
            axisLabel: {
                margin: 100,
                fontSize: 14,
                align: 'left',
                color: '#333',
                rich: {
                    a1: {
                        color: '#fff',
                        backgroundColor: colorList[0],
                        width: 30,
                        height: 30,
                        align: 'center',
                        borderRadius: 2
                    },
                    a2: {
                        color: '#fff',
                        backgroundColor: colorList[1],
                        width: 30,
                        height: 30,
                        align: 'center',
                        borderRadius: 2
                    },
                    a3: {
                        color: '#fff',
                        backgroundColor: colorList[2],
                        width: 30,
                        height: 30,
                        align: 'center',
                        borderRadius: 2
                    },
                    b: {
                        color: '#fff',
                        backgroundColor: colorList[3],
                        width: 30,
                        height: 30,
                        align: 'center',
                        borderRadius: 2
                    }
                },
                formatter: function(params) {
                    var index = datas.map(item => item.name).indexOf(params);
                    index = index + 1;
                    if (index - 1 < 3) {
                        return [
                            '{a' + index + '|' + index + '}' + '  ' + params
                        ].join('\n')
                    } else {
                        return [
                            '{b|' + index + '}' + '  ' + params
                        ].join('\n')
                    }
                }
            }
        }, {
            type: 'category',
            inverse: true,
            axisTick: 'none',
            axisLine: 'none',
            show: true,
            data: datas.map(item => item.value),
            axisLabel: {
                show:true,
                fontSize: 14,
                color: '#333',
                formatter:'{value}%'
            }
        }],
        series: [{
            z: 2,
            name: 'value',
            type: 'bar',
            barWidth: 20,
            zlevel: 1,
            data: datas.map((item, i) => {
                let itemStyle;
                itemStyle = {
                    color: i > 3 ? colorList[3] : colorList[i]
                };
                return {
                    value: item.value,
                    itemStyle: itemStyle
                };
            }),
            label: {
                show: false,
                position: 'right',
                color: '#333333',
                fontSize: 14,
                offset: [10, 0]
            }
        },
            {
                name: '背景',
                type: 'bar',
                barWidth: 20,
                barGap: '-100%',
                itemStyle: {
                    normal: {
                        color: 'rgba(118, 111, 111, 0.55)'
                    }
                },
                data: maxArr,
            }

        ]
    };

};

/**
 * 柱状图3
 * @param data
 * @param name
 * @returns {{backgroundColor: string, yAxis: {axisLabel: {show: boolean, textStyle: {color: string}}, axisLine: {show: boolean}, splitLine: {lineStyle: {color: string}}}, xAxis: {axisLabel: {show: boolean, textStyle: {color: string}}, data: string[], axisLine: {lineStyle: {color: string}}}, series: [{barWidth: number, data: number[], barGap: number, name: string, tooltip: {show: boolean}, itemStyle: {normal: {color: *}}, type: string}, {barWidth: number, data: *[], barGap: number, itemStyle: {normal: {color: *}}, type: string}, {symbol: string, symbolPosition: string, data: number[], symbolSize: [string, string], symbolOffset: [string, string], name: string, tooltip: {show: boolean}, itemStyle: {borderColor: string, color: string, borderWidth: number}, z: number, type: string}], tooltip: {formatter: string, axisPointer: {type: string}, trigger: string}}}
 */
export const histogramOptionThree = (data2,name) =>{
    const xData = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    const yData = [220, 182, 191, 234, 290, 330, 310];
    return  {

        backgroundColor: "#041730",
        tooltip: {
            trigger: 'axis',
            formatter: "{b} : {c}",
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            data: xData,
            //坐标轴
            axisLine: {
                lineStyle: {
                    color: '#3eb2e8'
                }
            },
            //坐标值标注
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff',
                }
            }
        },
        yAxis: {
            //坐标轴
            axisLine: {
                show: false
            },
            //坐标值标注
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff',
                }
            },
            //分格线
            splitLine: {
                lineStyle: {
                    color: '#4784e8'
                }
            }
        },
        series: [{
            name: 'a',
            tooltip: {
                show: false
            },
            type: 'bar',
            barWidth: 24.5,
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                        offset: 0,
                        color: "#0B4EC3" // 0% 处的颜色
                    }, {
                        offset: 0.6,
                        color: "#138CEB" // 60% 处的颜色
                    }, {
                        offset: 1,
                        color: "#17AAFE" // 100% 处的颜色
                    }], false)
                }
            },
            data: yData,
            barGap: 0
        }, {
            type: 'bar',
            barWidth: 8,
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                        offset: 0,
                        color: "#09337C" // 0% 处的颜色
                    }, {
                        offset: 0.6,
                        color: "#0761C0" // 60% 处的颜色
                    }, {
                        offset: 1,
                        color: "#0575DE" // 100% 处的颜色
                    }], false)
                }
            },
            barGap: 0,
            data: yData
        }, {
            name: 'b',
            tooltip: {
                show: false
            },
            type: 'pictorialBar',
            itemStyle: {
                borderWidth: 1,
                borderColor: '#0571D5',
                color: '#1779E0'
            },
            symbol: 'path://M 0,0 l 120,0 l -30,60 l -120,0 z',
            symbolSize: ['30', '12'],
            symbolOffset: ['0', '-11'],
            //symbolRotate: -5,
            symbolPosition: 'end',
            data: yData,
            z: 3
        }]
    };

};


/**
 * 柱状图4
 * @param data
 * @param name
 * @returns {{yAxis: [{type: string}], xAxis: [{data: string[], type: string}], calculable: boolean, legend: {data: [string]}, series: [{data: number[], name: string, itemStyle: {normal: {color: function(*): string, label: {formatter: string, show: boolean, position: string}}}, type: string}], tooltip: {trigger: string}, toolbox: {feature: {saveAsImage: {show: boolean}, restore: {show: boolean}, magicType: {show: boolean, type: [string, string]}, dataView: {show: boolean, readOnly: boolean}, mark: {show: boolean}}, show: boolean}, title: {subtext: string, text: string}}}
 */
export const histogramOptionFour = (data,name) =>{
    const xData = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
    const yData = [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3];
    const colorList = [
        '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
    ];
    return {
        title : {
            text: '某地区蒸发量和降水量',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['蒸发量']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : xData
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'蒸发量',
                type:'bar',
                data:yData,
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.

                            return colorList[params.dataIndex]
                        },label: {
                            show: true,
                            position: 'top',
                            formatter: '{b}\n{c}'
                        }
                    }
                }
            }
        ]
    };
};



/**
 * 饼图1
 * @param data2
 * @param name
 * @returns {{backgroundColor: string, color: string[], legend: {itemGap: number, orient: string, data: [string, string, string, string], bottom: number, icon: string, right: number, textStyle: {color: string}, align: string}, series: [{hoverAnimation: boolean, clockWise: boolean, data: [], name: string, itemStyle: {normal: {label: {formatter: function(*): (string|string), color: string, show: boolean, position: string}, labelLine: {color: string, length2: number, length: number, show: boolean}}}, type: string, radius: number[]}], tooltip: {show: boolean}, toolbox: {show: boolean}, title: {top: string, textAlign: string, left: string, text: string, textStyle: {color: string, fontSize: number, fontWeight: string}}, graphic: {elements: [{top: string, left: string, z: number, style: {image: string, width: number, height: number}, position: number[], type: string}]}}}
 */
export const pieOptionOne = (data2,name) =>{
    const img = 'data:image/png;base64,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';

    const pieData = [{
        name: '火车',
        value: 20
    },{
        name: '飞机',
        value: 10
    },{
        name: '客车',
        value: 30
    },{
        name: '轮渡',
        value: 40
    }];

    const data = [];
    const color=['#00ffff','#00cfff','#006ced','#ffe000','#ffa800','#ff5b00','#ff3000'];
    const legendData = ['火车','飞机','客车','轮渡'];
    for (let i = 0; i < pieData.length; i++) {
        data.push({
            value: pieData[i].value,
            name: pieData[i].name,
            itemStyle: {
                normal: {
                    borderWidth: 5,
                    shadowBlur: 20,
                    borderColor:color[i],
                    shadowColor: color[i]
                }
            }
        }, {
            value: 2,
            name: '',
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    },
                    color: 'rgba(0, 0, 0, 0)',
                    borderColor: 'rgba(0, 0, 0, 0)',
                    borderWidth: 0
                }
            }
        });
    }
    const seriesOption = [{
        name: '',
        type: 'pie',
        clockWise: false,
        radius: [105, 109],
        hoverAnimation: false,
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    position: 'outside',
                    color: '#ddd',
                    formatter: function(params) {
                        var percent = 0;
                        var total = 0;
                        for (var i = 0; i < pieData.length; i++) {
                            total += pieData[i].value;
                        }
                        percent = ((params.value / total) * 100).toFixed(0);
                        if (params.name !== '') {
                            return '交通方式：' + params.name + '\n' + '\n' + '占百分比：' + percent + '%';
                        } else {
                            return '';
                        }
                    }
                },
                labelLine: {
                    length: 30,
                    length2: 100,
                    show: true,
                    color: '#00ffff'
                }
            }
        },
        data: data
    }];
    return  {
        backgroundColor: '#0A2E5D',
        color : color,
        title: {
            text: '交通方式',
            top: '48%',
            textAlign: "center",
            left: "49%",
            textStyle: {
                color: '#fff',
                fontSize: 22,
                fontWeight: '400'
            }
        },
        graphic: {
            elements: [{
                type: "image",
                z: 3,
                style: {
                    image: img,
                    width: 178,
                    height: 178
                },
                left: 'center',
                top:  'center',
                position: [100, 100]
            }]
        },
        tooltip: {
            show: false
        },
        legend: {
            icon: "circle",
            orient: 'horizontal',
            // x: 'left',
            data:legendData,
            right: 340,
            bottom: 150,
            align: 'right',
            textStyle: {
                color: "#fff"
            },
            itemGap: 20
        },
        toolbox: {
            show: false
        },
        series: seriesOption
    };
};


/**
 * 饼图2
 * @param data2
 * @param name
 * @returns {{color: string[], legend: {orient: string, top: string, left: string, textStyle: {color: string}, type: string, align: string, height: number}, series: [{data, name: string, type: string, radius: number[]}], tooltip: {formatter: string, trigger: string}, toolbox: {show: boolean}}}
 */
export const pieOptionTwo = (data2,name) =>{
    const pieData = [
        {value:20, name:'国宝'},
        {value:30, name:'治安'},
        {value:25, name:'rose3'},
        {value:25, name:'rose4'},
        {value:20, name:'rose5'},
        {value:35, name:'rose6'},
        {value:30, name:'rose7'},
        {value:40, name:'rose8'}
    ];
    const colorList = ['#37a2da','#32c5e9','#9fe6b8','#ffdb5c','#ff9f7f','#fb7293','#e7bcf3','#8378ea'];
    return {
        color: colorList,
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            show : true,

        },
        legend: {
            type:"scroll",
            orient: 'vertical',
            left:'10%',
            align:'left',
            top:'middle',
            textStyle: {
                color:'#8C8C8C'
            },
            height:150
        },
        series : [
            {
                name:'业务警种',
                type:'pie',
                radius : [0, 150],
                data:pieData
            }
        ]
    };

};


/**
 * 饼图3
 * @param data2
 * @param name
 * @returns {{color: string[], legend: {orient: string, top: string, left: string, textStyle: {color: string}, type: string, align: string, height: number}, series: [{data, name: string, type: string, radius: number[]}], tooltip: {formatter: string, trigger: string}, toolbox: {show: boolean}}}
 */
export const pieOptionThree = (data2,name) =>{
    const scaleData = [
        {'name': '工程建设', 'value': 10 },
        {'name': '产权交易', 'value': 10 },
        { 'name': '土地交易', 'value': 10 },
        { 'name': '其他交易', 'value': 10 },
        { 'name': '土地交易', 'value': 10 },
        { 'name': '其他交易', 'value': 10 },
    ];
    const color1 = ['rgb(255, 153, 153)', 'rgb(255, 176, 63)', 'rgb(61, 186, 45)', 'rgb(43, 166, 254)','rgb(255,222,0)','rgb(255,0,0)'];

    const rich = {
        white: {
            color: '#fff',
            align: 'center',
            fontWeight:'bold',
            padding: [3, 0]
        }
    };
    const placeHolderStyle = {
        normal: {
            label: {
                show: false
            },
            labelLine: {
                show: false
            },
            color: 'rgba(0, 0, 0, 0)',
            borderColor: 'rgba(0, 0, 0, 0)',
            borderWidth: 0
        }
    };
    const data = [];

    for (let i = 0; i < scaleData.length; i++) {
        data.push({
            value: scaleData[i].value,
            name: scaleData[i].name,
            itemStyle: {
                normal: {
                    borderWidth: 4,
                    shadowBlur: 30,
                    borderColor:color1[i],
                    shadowColor: color1[i],
                    color: color1[i],
                    opacity:0.75,
                }
            }
        }, {
            value: 2,
            name: '',
            itemStyle: placeHolderStyle
        });
    }
    const seriesObj = [{
        name: '',
        type: 'pie',
        clockWise: false,
        radius: [100, 200],
        hoverAnimation: false,
        itemStyle: {
            normal: {
                label: {
                    show: true,
                    position: 'inner',
                    fontWeight:'bold',
                    formatter: function(params) {
                        var percent = 0;
                        var total = 0;
                        for (var i = 0; i < scaleData.length; i++) {
                            total += scaleData[i].value;
                        }
                        percent = ((params.value / total) * 100).toFixed(0);
                        if(params.name !== '') {
                            return params.name + '\n{white|' + '占比' + percent + '%}';
                        }else {
                            return '';
                        }
                    },
                    rich: rich
                },
                labelLine: {
                    show: false,
                }
            }
        },
        data: data
    }];
    return  {
        backgroundColor: '#04243E',
        tooltip: {
            show: false
        },
        legend: {
            show: false
        },
        toolbox: {
            show: false
        },
        series: seriesObj
    };

};


/**
 * 饼图4
 * @param data2
 * @param name
 * @returns {{color: string[], legend: {orient: string, top: string, left: string, textStyle: {color: string}, type: string, align: string, height: number}, series: [{data, name: string, type: string, radius: number[]}], tooltip: {formatter: string, trigger: string}, toolbox: {show: boolean}}}
 */
export const pieOptionFour = (data2,name) =>{
    const legends = data2.legendData;
    const colors = ['#0278e6', '#34d160', '#fcdf39', '#f19611', '#00c6ff', '#f76363'].reverse();
    const data = data2.data;
    const total = data.reduce((prev, curr) => prev + curr.value, 0);
    return  {
        backgroundColor: "#0f375f",
        title: {
            text: '用户性别分布',
            textStyle: {
                fontSize: 12,
                fontWeight: 400,
                color:'white'
            },
        },
        color: colors,
        legend: {
            orient: 'vertical',
            top: "center",
            right: "5%",
            itemGap: 50,
            itemWidth: 16,
            itemHeight: 16,
            textStyle: {
                color: "#fff",
                fontSize: 16
            },
            data: legends,
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        series: [{
            name: '半径模式',
            type: 'pie',
            radius: ['30%', '80%'],
            center: ['40%', '50%'],
            roseType: 'radius',
            minShowLabelAngle: 60,
            label: {
                show: true,
                normal: {
                    position: 'outside',
                    fontSize: 16,
                    formatter: (params) => {
                        return params.name + '(' + (params.value / total * 100).toFixed(2) + '%)' + '\n' + params.value + '个';
                    }
                }
            },
            labelLine: {
                length: 1,
                length2: 20,
                smooth: true
            },
            data: data
        }]
    };

};
