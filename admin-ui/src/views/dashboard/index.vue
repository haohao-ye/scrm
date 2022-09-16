<template>
  <div class="dashboard-container">
    <el-row>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
        
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <line-chart />
        </div>
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
