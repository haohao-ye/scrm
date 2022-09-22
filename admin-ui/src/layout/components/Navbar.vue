<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <template v-if="device!=='mobile'">
        <screenfull id="screenfull" class="right-menu-item hover-effect" />
      </template>
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <!-- <i class="el-icon-arrow-down" /> -->
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <router-link to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>
          <a target="_blank" href="https://qfcode.coding.net/p/huanong0929/d/myscrm/git">
            <el-dropdown-item>Git一下</el-dropdown-item>
          </a>
          <a target="_blank" href="https://shimo.im/docs/2wAlXDoW1KfNj4AP/">
            <el-dropdown-item>文档</el-dropdown-item>
          </a>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">登出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Screenfull from '@/components/Screenfull'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('admin/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 60px;
  overflow: hidden;
  position: relative;
  background: #fff;
  border-bottom: 0.1px solid #f7f7f7;
  border-left: 1px solid #f7f7f7;
  // box-shadow: 0 1px 4px rgba(0,21,41,.05);

  .hamburger-container {
    line-height: 60px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: all .3s ease-in-out;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      // color: rgba(64, 158, 255, .8);
      background: rgba(0, 0, 0, .025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 20px;
    margin-top: 8px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 30px;
      height: 100%;
      font-size: 16px;
      color: #5a5e66;
      vertical-align: middle;  // 使块内元素居中排列
      transition: all .3s ease-in-out;

      &.hover-effect {
        cursor: pointer;

        &:hover {
          // background-color: rgba(0, 0, 0, .025);
          color: rgba(64, 158, 255, .8);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;
      
      .avatar-wrapper {
        margin-top: 10px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          position: relative;
          bottom: 8px;
          width: 35px;
          height: 35px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 10px;
          font-size: 1px;
        }
      }
    }
  }
}
</style>
