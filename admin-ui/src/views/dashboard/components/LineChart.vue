<template>
    <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import * as echarts from 'echarts'  // 根据 v5 修改引入方式
import { TEXT_STYLE_OPTIONS } from 'echarts/lib/util/model'
import resize from './mixins/resize'

const animationDuration = 6000
// 初始化数据（如没有会报错）
// 后期加上接口调用方法，更新 lineData 即可。
const lineData = {
    good: [120, 132, 101, 134, 190, 230, 210, 201, 234, 290, 230, 210],
    medium: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110],
    bad: [10, 22, 21, 14, 19, 13, 20, 11, 34, 29, 20, 10]
}

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
        },
        chartData: {
            type: Object,
            require: true
        }
    },
    data() {
        return {
            chart: null
        }
    },
    watch: {
        chartData: {
            deep: true,
            handler(val) {
                this.setOptions(val)
            }
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
            this.setOptions(lineData)
        },
        
        setOptions({good, medium, bad} = {}) {
            this.chart.setOption({
                title: {
                    text: '评价数据分析'
                },
                // 提示框
                tooltip: {
                    trigger: 'axis'
                },
                // 图例
                legend: {
                    icon: 'circle',
                    left: 'center',
                    top: 0,
                    data: ['好评', '一般', '差评']
                },
                grid: {
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true
                },
                // 工具栏
                toolbox: {
                    feature: {
                        magicType: {
                            type: ['line', 'bar', 'stack']
                        },
                        saveAsImage: {
                            type: 'png',
                        }
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '好评',
                        type: 'line',
                        // smooth: true, // 平滑曲线显示
                        data: good
                    }, {
                        name: '一般',
                        type: 'line',
                        // smooth: true,
                        data: medium
                    }, {
                        name: '差评',
                        type: 'line',
                        stack: '总量',
                        // smooth: true,
                        data: bad
                    }
                ]
            })
        }
    }
}
</script>
