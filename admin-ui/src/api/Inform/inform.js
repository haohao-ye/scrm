import request from '@/utils/request'

// 查询inform列表
export function listInform(query) {
  return request({
    url: '/api/Inform/inform/list',
    method: 'get',
    params: query
  })
}

// 查询inform详细
export function getInform(id) {
  return request({
    url: '/api/Inform/inform/' + id,
    method: 'get'
  })
}

// 新增inform
export function addInform(data) {
  return request({
    url: '/api/Inform/inform',
    method: 'post',
    data: data
  })
}

// 修改inform
export function updateInform(data) {
  return request({
    url: '/api/Inform/inform',
    method: 'put',
    data: data
  })
}

// 删除inform
export function delInform(id) {
  return request({
    url: '/api/Inform/inform/' + id,
    method: 'delete'
  })
}

// 导出inform
export function exportInform(query) {
  return request({
    url: '/api/Inform/inform/export',
    method: 'get',
    params: query
  })
}