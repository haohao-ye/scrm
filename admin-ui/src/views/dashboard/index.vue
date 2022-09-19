<template>
  <div class="dashboard-container">
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
            <line-chart />
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import BarChart from './components/BarChart'
import PieChart from './components/PieChart'
import LineChart from './components/LineChart'

export default {
  name: 'Dashboard',

  components: {
    BarChart,
    PieChart,
    LineChart
  },

  computed: {
    ...mapGetters([
      'name'
    ])
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
