
<!--
<template>
    <div>角色管理</div>
</template>


<script>
   export default{
       name:"Role"
   }
</script>

<style scoped>

</style>-->
<template>
<div class="calender2">
  <div class="date-headers">
  <div class="date-header">
    <div><el-button type="primary" @click="handlePrev"><i class="el-icon-arrow-left"></i>上一月</el-button></div>
    <div>{{ year }}年{{ month }}月{{ day }}日</div>
    <div><el-button type="primary" @click="handleNext">下一月<i class="el-icon-arrow-right"></i></el-button></div>
    <div><el-button type="primary" icon="el-icon-refresh-left" @click="refresh"></el-button></div>
  </div>
  </div>
  <div class="date-content">
    <div class="week-header">
      <div
       v-for="item in ['日','一','二','三','四','五','六']"
       :key=item
      >{{ item }}
      </div>
    </div>
    <div class="week-day">
      <div
       class="every-day"
       v-for="item in 42"
       :key="item"
       @click="handleClickDay(item - beginDay)"
      >
        <div v-if="item - beginDay > 0 && item - beginDay <= curDays"   :class="`${year}-${month}-${item - beginDay}` === `${year}-${month}-${day}` ? 'nowDay':''"
        >{{ item - beginDay }}</div>
        <div class="other-day" v-else-if="item - beginDay <= 0">{{ item - beginDay + prevDays }}</div>
        <div class="other-day" v-else>{{ item - beginDay -curDays }}</div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
  export default {
    name: "HelloWorld",
    data() {
      return {
        year: null,
        month: null,
        day: null,
        currentDay: null,
        currentYearMonthTimes: null,//当前年的月的天数
        monthOneDay: null,//一个月中的某一天
        curDate: null,
        prevDays: null,//上一月天数
      }
    },
    computed: {
      curDays() {
        return new Date(this.year, this.month, 0).getDate();
      },
      // 设置该月日期起始值（找到一号是在哪里）
      beginDay() {
        return new Date(this.year, this.month - 1, 1).getDay();
      }
    },
    created() {
      this.getInitDate();
      this.currentYearMonthTimes = this.mGetDate(this.year, this.month); //本月天数
      this.prevDays = this.mGetDate(this.year, this.month - 1);
      this.curDate = `${this.year}-${this.month}-${this.day}`
      console.log(this.curDate)
    },
    methods: {
      refresh(){ //强制刷新页面
        location. reload()
      },
      handleClickDay(day){ //点击这一天，绑定点击事件
        console.log( '形参传进来的',day)
        console.log( 'data里面的this.day',this.day)
        console.log( 'data里面的currentYearMonthTimes',this.currentYearMonthTimes)
        this.day = day
        if(this.day > this.currentYearMonthTimes){
          this.$message.warning('不能选择超出本月的日期');
        }
        console.log(day)
      },
      computedDay() {
        const allDay = new Date(this.year, this.month, 0).getDate();
        if (this.day > allDay) {
          this.day = allDay;
        }
      },
      //设置页头显示的年月日
      getInitDate() {
        const date = new Date();
        this.year = date.getFullYear();
        this.month = date.getUTCMonth() + 1;
        this.day = date.getDate();
      },
      // 如果要获取当前月份天数:
      mGetDate() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var d = new Date(year, month, 0);
        return d.getDate();
      },
      handlePrev() {
        if (this.month == 1) {
          this.month = 12
          this.year--
        } else {
          this.month--
        }
        this.computedDay()
      },
      handleNext() {
        if (this.month == 12) {
          this.month = 1
          this.year++
        } else {
          this.month++
        }
        this.computedDay()
      }
    }
  }
</script>

<style scoped>
  * {
    margin: 0px;
    border: 0px;
    list-style: none;
  }

  .calender2 {
    border-radius: 4px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    height: 396px;
    width: 420px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }

  .date-header {
    height: 60px;
    width: 422px;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }
  .date-headers {
    height: 60px;
    width: 422px;
    display: flex;
  }

  .pre-month {
    position: absolute;
    display: inline-block;
    height: 0px;
    width: 0px;
    border: 15px solid;
    border-color: transparent rgb(35, 137, 206) transparent transparent;
  }

  .next-month {
    position: absolute;
    display: inline-block;
    height: 0px;
    width: 0px;
    border: 15px solid;
    border-color: transparent transparent transparent rgb(35, 137, 206);
  }

  .show-date {
    margin-left: 40px;
    margin-top: 0px;
    display: inline-block;
    line-height: 30px;
    text-align: center;
    width: 310px;
    color: rgb(35, 137, 206);
  }

  .week-header {
    color: #000000;
    font-size: 14px;
    text-align: center;
    line-height: 30px;
  }

  .week-header div {
    margin: 0;
    padding: 0;
    display: inline-block;
    height: 20px;
    width: 60px;
  }

  .every-day {
    display: inline-block;
    height: 50px;
    width: 60px;
    text-align: center;
    line-height: 50px;
  }

  .other-day {
    color: #ccc;
  }

  .nowDay {
    background: rgb(121, 35, 206);
    border: 1px solid #87c66d;
  }

  .active-day {
    /* padding: 2px */
    /* border-sizing:content-box; */
    border: 2px solid rgb(35, 137, 206);
  }
</style>