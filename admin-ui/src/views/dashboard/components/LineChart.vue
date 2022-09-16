<template>
    <div>
        <div ref="lineChart" style="width: 800px; height: 400px; background-color: #ffffff; padding: 20px; border-radius: 20px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
    mounted() {
        this.lineChart = echarts.init(this.$refs.lineChart)
        this.lineChart.setOption({
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
                    saveAsImage: {
                        type: 'png'
                    },
                    magicType: {
                        type: ['line', 'bar', 'stack']
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
                    data: [120, 132, 101, 134, 190, 230, 210, 201, 234, 290, 230, 210]
                },
                {
                    name: '一般',
                    type: 'line',
                    // smooth: true,
                    data: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110]
                },
                {
                    name: '差评',
                    type: 'line',
                    stack: '总量',
                    // smooth: true,
                    data: [10, 22, 21, 14, 19, 13, 20, 11, 34, 29, 20, 10]
                }
            ]
        })
    }
}
</script>