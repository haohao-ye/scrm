<template>
    <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'  // 根据 v5 修改引入方式
import resize from './mixins/resize'

const animationDuration = 6000
const pieData = [
                    { name: '好评', value: 1048 },
                    { name: '一般', value: 735 },
                    { name: '差评', value: 180 }
                ]

export default {
    mixins: [resize],
    props: {
        className: {
            type: String,
            default: 'chart'
        },
        width: {
            type: String,
            default: '100%'
        },
        height: {
            type: String,
            default: '400px'
        }
    },
    data() {
        return {
            chart: null
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.initChart()
        })
    },
    beforeDestroy() {
        if (!this.chart) {
            return
        }
        this.chart.dispose()
        this.chart = null
    },
    methods: {
        initChart() {
            this.chart = echarts.init(this.$el)
            this.setOptions(pieData)
        },

        setOptions(pieData) {
            this.chart.setOption({
                title: {
                    text: '32469',
                    subtext: '评价数',
                    left: 'center',
                    top: '43%',
                    subtextStyle: {
                        fontSize: 18
                    }
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    icon: 'circle',
                    top: '0',
                    left: 'left'
                },
                // 工具栏
                toolbox: {
                    feature: {
                        saveAsImage: {
                            type: 'png'
                        }
                    }
                },
                series: [
                    {
                        name: '商品评价',
                        type: 'pie',
                        radius: ['40%', '55%'],
                        label: {
                            show: true,
                            padding: [0, -50],
                            overflow: 'none',
                            fontSize: '15',
                            fontWeight: 'bold',
                            formatter: '{d}%\n\n{c}'
                        },
                        labelLine: {
                            show: true,
                            length: 15,
                            length2: 60
                        },
                        itemStyle: {
                            normal: {
                                color: function (params) {
                                    var colorList = ['#4FC3F7', '#00C853', '#F57F17']
                                    return colorList[params.dataIndex]
                                }
                            }
                        },
                        data: pieData
                    }
                ]
            })
        }
    }
}
</script>