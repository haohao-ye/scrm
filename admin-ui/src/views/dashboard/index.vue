<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
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
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>

<style lang="scss" scoped>
  @font-face {
    font-family:AliRegular;
    src:url('../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
  }
  @font-face {
    font-family:AliBold;
    src:url('../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
  }
  
  .app-container {
    font-family: "AliRegular","Source Han Sans CN","Microsoft YaHei";
  }
  
  </style>