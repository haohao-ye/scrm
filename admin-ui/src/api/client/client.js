import request from '@/utils/request'

// 查询客户表列表
export function listClient(query) {
  return request({
    url: '/api/client/list',
    method: 'get',
    params: query
  })
}

// 查询客户表详细
export function getClient(id) {
  return request({
    url: '/api/client/' + id,
    method: 'get'
  })
}

// 新增客户表
export function addClient(data) {
  return request({
    url: '/api/client',
    method: 'post',
    data: data
  })
}

// 修改客户表
export function updateClient(data) {
  return request({
    url: '/api/client',
    method: 'put',
    data: data
  })
}

// 删除客户表
export function delClient(id) {
  return request({
    url: '/api/client/' + id,
    method: 'delete'
  })
}

// 导出客户表
export function exportClient(query) {
  return request({
    url: '/api/client/export',
    method: 'get',
    params: query
  })
}