<template>
    <div>
        <div ref="myChart"
            style="width: 400px; height: 400px; background-color: #ffffff; padding: 20px; border-radius: 20px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
    mounted() {
        this.myChart = echarts.init(this.$refs.myChart)
        this.myChart.setOption({
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
                left: 'right'
            },
            series: [
                {
                    name: '商品评价',
                    type: 'pie',
                    radius: ['40%', '55%'],
                    label: {
                        show: true,
                        padding: [0, -60],
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
                    data: [
                        { name: '好评', value: 1048 },
                        { name: '一般', value: 735 },
                        { name: '差评', value: 180 }
                    ]
                }
            ]
        })
    }
}
</script>