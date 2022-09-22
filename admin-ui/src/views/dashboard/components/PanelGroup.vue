<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('test')">
          <div class="card-panel-icon-wrapper icon-people">
            <svg-icon icon-class="dashboard_yingshou" class-name="card-panel-icon" />
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">
              总营收（W）
            </div>
            <count-to :end-val="turnover" :duration="2600" class="card-panel-num" />
          </div>
        </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="dashboard_dingdan" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            订单（个）
          </div>
          <count-to :start-val="0" :end-val="orderSum" :duration="3000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="dashboard_yuangong" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            员工（人）
          </div>
          <count-to :start-val="0" :end-val="peopleSum" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="dashboard_kucun" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            货物库存（件）
          </div>
          <count-to :start-val="0" :end-val="goodSum" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'
import { getMoney, getOrderSum } from '@/api/orders/orders'
import { getGoodSum } from '@/api/goods/goods'
import { getEmployeeSum } from '@/api/employee/employee'

export default {
  components: {
    CountTo
  },
  data() {
    return {
      turnover: null,
      orderSum: null,
      peopleSum: null,
      goodSum: null
    }
  },
  watch: {
    turnover: {
      handler(newVal, oldVal) {
        this.timer()
        this.destroyed()
      },
      deep: true   //对象内部属性的监听
    },
    orderSum: {
      handler(newVal, oldVal) {
        this.timer()
        this.destroyed()
      },
      deep: true   //对象内部属性的监听
    },
    peopleSum: {
      handler(newVal, oldVal) {
        this.timer()
        this.destroyed()
      },
      deep: true   //对象内部属性的监听
    },
    goodSum: {
      handler(newVal, oldVal) {
        this.timer()
        this.destroyed()
      },
      deep: true   //对象内部属性的监听
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.getData()
    })
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },

    getData() {
      getMoney().then(res => {
        let code = res["code"]
        if (code == 20000) {
          this.turnover = res["data"]
        } else {
          alert("营业额更新失败，请检查！")
        }
      }),

      getOrderSum().then(res => {
        let code = res["code"]
        if (code == 20000) {
          this.orderSum = res["data"]
        } else {
          alert("订单数更新失败，请检查！")
        }
      }),

      getEmployeeSum().then(res => {
        let code = res["code"]
        if (code == 20000) {
          this.peopleSum = res["data"]
        } else {
          alert("员工数更新失败，请检查！")
        }
      }),

      getGoodSum().then(res => {
        let code = res["code"]
        if (code == 20000) {
          this.goodSum = res["data"]
        } else {
          alert("库存数更新失败，请检查！")
        }
      })
    },

    timer () {
      return setTimeout(() => {
        this.getData()
      }, 5000)
    },

    // 销毁定时器
    destroyed () {
      clearTimeout(this.timer())
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #464646;
    background: #fff;
    // background-image: url('../../../assets/login.png');
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .04);
    border-radius: 10px;

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #FFD900;
      }

      .icon-message {
        background: #00CB53;
      }

      .icon-money {
        background: #36a3f7;
      }

      .icon-shopping {
        background: #956FE7;
      }
    }

    .icon-people {
      color: #E6B322;
    }

    .icon-message {
      color: #00CB53;
    }

    .icon-money {
      color: #36a3f7;
    }

    .icon-shopping {
      color: #956FE7;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        // color: #096DD9;
        color: #464646;
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
