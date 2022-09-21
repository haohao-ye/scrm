<template>
  <div class="dashboard-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row :gutter="32">
      <el-col :xs="38" :sm="28" :lg="8">
        <el-card>
          <div class="chart-wrapper">
            <pie-chart />
          </div>
        </el-card>
      </el-col>
        
      <el-col :xs="38" :sm="28" :lg="16">
        <el-card>
          <div class="chart-wrapper">
            <line-chart :chart-data='lineChartData'/>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import PanelGroup from './components/PanelGroup'
import BarChart from './components/BarChart'
import PieChart from './components/PieChart'
import LineChart from './components/LineChart'


const lineChartData = {
  test: {
    good: [520, 532, 501, 534, 190, 230, 210, 201, 234, 290, 230, 210],
    medium: [100, 82, 91, 54, 90, 76, 110, 81, 104, 90, 130, 110],
    bad: [10, 22, 51, 14, 59, 13, 20, 11, 34, 29, 20, 10]
  }
}

export default {
  name: 'Dashboard',

  components: {
    PanelGroup,
    BarChart,
    PieChart,
    LineChart
  },

  computed: {
    ...mapGetters([
      'name'
    ])
  },

  data() {
    return {
      lineChartData: lineChartData
    }
  },

  methods: {
    handleSetLineChartData(type) {
      // 根据 type 调用对应方法，然后获取数据并更新 this.lineChartData
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
}
</style>
