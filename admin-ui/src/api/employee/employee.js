import request from '@/utils/request'

// 查询员工管理列表
export function listEmployee(query) {
  return request({
    url: '/api/employee/employee/list',
    method: 'get',
    params: query
  })
}

// 查询员工管理列表2
export function listEmp(query) {
  return request({
    url: '/api/employee/employee/lists',
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

// 导入员工管理
export function importEmployee(data) {
  return request({
    url: '/api/employee/employee/getImport',
    method: 'put',
    params: data
  })
}

// 重置员工密码
export function resetPassword(data) {
  return request({
    url: '/api/employee/employee/resetPw',
    method: 'Put',
    data:data
  })
}

export function getEmployeeSum(query) {
  return request({
    url: '/api/employee/employee/count',
    method: 'get',
    params: query

  })
}