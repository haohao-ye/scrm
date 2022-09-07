import request from '@/utils/request'

// 查询员工管理列表
export function listEmployee(query) {
  return request({
    url: '/api/employee/employee/list',
    method: 'get',
    params: query
  })
}

// 查询员工管理详细
export function getEmployee(id) {
  return request({
    url: '/api/employee/employee/' + id,
    method: 'get'
  })
}

// 新增员工管理
export function addEmployee(data) {
  return request({
    url: '/api/employee/employee',
    method: 'post',
    data: data
  })
}

// 修改员工管理
export function updateEmployee(data) {
  return request({
    url: '/api/employee/employee',
    method: 'put',
    data: data
  })
}

// 删除员工管理
export function delEmployee(id) {
  return request({
    url: '/api/employee/employee/' + id,
    method: 'delete'
  })
}

// 导出员工管理
export function exportEmployee(query) {
  return request({
    url: '/api/employee/employee/export',
    method: 'get',
    params: query
  })
}