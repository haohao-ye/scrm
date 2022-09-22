<template>
  <div class="login-container">
    <el-form ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left">

      <div class="title-container">
        <h3 class="title">MyScrm</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input ref="username" v-model="loginForm.username" placeholder="请输入用户名" name="username" type="text"
          tabindex="1" auto-complete="on" />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType"
          placeholder="请输入密码" name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        v-loading.fullscreen.lock="loading"
        element-loading-spinner="el-icon-magic-stick"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin">登录</el-button>
      <div class="tips">
        <span>如忘记账户信息，请联系公司管理员。</span>
      </div>

    </el-form>
  </div>
</template>

<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      // if (!validUsername(value)) {
      //   callback(new Error('请输入正确的用户名'))
      // } else {
      //   callback()
      // }
      callback()
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不得少于 6 位数字'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('admin/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.$message.success('登录成功')
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;
$placeholder: #eae8e8;

@font-face {
  font-family: AliLight;
  src: url('../../assets/font/Alibaba-PuHuiTi-Light.ttf')
}

@font-face {
  font-family: AliRegular;
  src: url('../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
}

@font-face {
  font-family: AliBold;
  src: url('../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
}

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  text-shadow: 4px 2px 5px rgba(103, 103, 103);

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      border-radius: 0px;

      font-size: 16px;
      font-family: "AliRegular";

      color: $light_gray;
      caret-color: $cursor;
      -webkit-appearance: none;

      padding: 12px 5px 12px 15px;
      height: 47px;

      &::placeholder {
        color: $placeholder;
        font-size: 14px;
        letter-spacing: 1.5px;
        font-family: "AliLight";
      }

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    // border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(103, 103, 103, 0.4);
    border-radius: 30px;
    color: #454545;
    margin-bottom: 30px;
  }

  // 重写 elementUI 的错误提示样式
  .el-form-item__content > .el-form-item__error {
      color: #ff5c61;
      text-shadow: 5px 5px 10px rgba(255, 255, 255, 0.6);
      // background: rgba(255, 255, 255, 0.7);
  }

  .el-button {
    font-size: 14px;
    border-radius: 20px;
    letter-spacing: 20px;
    font-family: "AliRegular";
  }
}
</style>

<style lang="scss" scoped>
$svg_color: #fff;
$text_color: #fff;

@font-face {
  font-family: AliRegular;
  src: url('../../assets/font/Alibaba-PuHuiTi-Regular.ttf')
}

@font-face {
  font-family: AliBold;
  src: url('../../assets/font/Alibaba-PuHuiTi-Bold.ttf')
}

.login-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;
  font-family: "AliRegular";
  background-image: url("../../assets/login1.png");
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  text-shadow: 4px 2px 5px rgba(103, 103, 103, 0.2);

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 13px;
    letter-spacing: 1px;
    color: $text_color;
    text-align: center;
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $svg_color;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 50px;
      color: $text_color;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $svg_color;
    cursor: pointer;
    user-select: none;
  }
}
</style>
