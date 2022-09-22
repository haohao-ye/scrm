import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'shouye' }
    }]
  },

  {
    path: '/getInform',
    component: Layout,
    name: 'GetInform',
    // meta: { title: '通知管理', icon: 'el-icon-message-solid' },
    children: [
      {
        path: 'getInform',
        name: 'GetInform',
        component: () => import('@/views/getInform/index'),
        meta: { title: '通知', icon: 'tongzhi', roles: ['admin'] }
      }
    ]
  },
  // {
  //   path: '/tool',
  //   component: Layout,
  //   name: 'Tool',
  //   // meta: { title: '系统工具', icon: 'el-icon-s-tools' },
  //   children: [
  //     {
  //       path: 'gen',
  //       name: 'Gen',
  //       component: () => import('@/views/tool/gen'),
  //       meta: { title: '代码生成', icon: 'gongju' }
  //     }
  //   ]
  // },

  /*
    {
      path: '/example',
      component: Layout,
      redirect: '/example/table',
      name: 'Example',
      meta: { title: 'Example', icon: 'el-icon-s-help' },
      children: [
        {
          path: 'table',
          name: 'Table',
          component: () => import('@/views/table/index'),
          meta: { title: 'Table', icon: 'table' }
        },
        {
          path: 'tree',
          name: 'Tree',
          component: () => import('@/views/tree/index'),
          meta: { title: 'Tree', icon: 'tree' }
        }
      ]
    },
  */

  // {
  //   path: '/salecount',
  //   component: Layout,
  //   name: 'Salescount',
  //   meta: { title: '销售统计', icon: 'el-icon-s-tools' },
  //   children: [
  //     {
  //       path: 'salescount',
  //       name: 'Salescount',
  //       component: () => import('@/views/salescount/salescount'),
  //       meta: { title: '销售统计', icon: 'el-icon-document-copy' }
  //     }
  //   ]
  // },

  {
    path: '/gen',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'edit/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: { title: '修改生成配置' }
      }
    ]
  }
]

// 把需要设置权限的理由放在动态路由里那就得写为 其中 meta中的 roles 就是对角色的管理
export const asyncRoutes = [
  {
    path: '/sysmgr',
    component: Layout,
    redirect: '/sysmgr/admin',
    name: 'Sysadmin',
    // meta: { title: '成员管理', icon: 'el-icon-user-solid' },
    children: [
      {
        path: 'admin',
        name: 'Admin',
        component: () => import('@/views/sysmgr/admin'),
        meta: { title: '系统管理员', icon: 'guanliyuan', roles: ['admin'] }
      },
      // {
      //   path: 'admin1',
      //   name: 'Admin1',
      //   component: () => import('@/views/sysmgr/admin1'),
      //   meta: { title: '成员管理', icon: 'el-icon-platform-eleme' }
      // }
    ]
  },

  {
    path: '/employee',
    component: Layout,
    name: 'employee',
    // meta: { title: '员工管理', icon: 'el-icon-user' },
    children: [
      {
        path: 'employee',
        name: '员工管理',
        component: () => import('@/views/employee/index'),
        meta: { title: '员工管理', icon: 'yuangong', roles: ['admin'] }
      }
    ]
  },

  {
    path: '/dept',
    component: Layout,
    name: 'Dept',
    // meta: { title: '部门列表', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'dept',
        name: 'Dept',
        component: () => import('@/views/dept/dept'),
        meta: { title: '部门管理', icon: 'bumen', roles: ['admin'] }
      }
    ]
  },

  {
    path: '/inform',
    component: Layout,
    name: 'Inform',
    // meta: { title: '通知管理', icon: 'el-icon-message-solid' },
    children: [
      {
        path: 'inform',
        name: 'Inform',
        component: () => import('@/views/inform/index'),
        meta: { title: '通知管理', icon: 'tongzhiguanli', roles: ['admin'] }
      }
    ]
  },


  {
    path: '/goods',
    component: Layout,
    name: 'goods',
    // meta: { title: '商品管理', icon: 'el-icon-s-goods' },
    children: [
      {
        path: 'goods',
        name: '商品列表',
        component: () => import('@/views/goods/goods'),
        meta: { title: '商品管理', icon: 'shangpin', roles: ['admin', 'sales', 'assets']  }
      }
    ]
  },

  // {
  //   path: '/activity',
  //   component: Layout,
  //   name: 'Activity',
  //   // meta: { title: '营销活动', icon: 'el-icon-s-goods' },
  //   children: [
  //     {
  //       path: 'activity',
  //       name: '营销',
  //       component: () => import('@/views/activity/activity'),
  //       meta: { title: '营销活动', icon: 'yingxiao', roles: ['admin', 'sales'] }
  //     }
  //   ]
  // },

  {
    path: '/orders',
    component: Layout,
    name: 'Orders',
    // meta: { title: '订单列表', icon: 'el-icon-s-tools' },
    children: [
      {
        path: 'orders',
        name: 'Orders',
        component: () => import('@/views/orders/orders'),
        meta: { title: '订单管理', icon: 'dingdan', roles: ['admin', 'sales'] }
      }
    ]
  },

  {
    path: '/client',
    component: Layout,
    name: 'Client',
    children: [
      {
        path: 'client',
        name: 'Client',
        component: () => import('@/views/client/client/index'),
        meta: { title: '客户管理', icon: 'kehu', roles: ['admin', 'sales'] }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
