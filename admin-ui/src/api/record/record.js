import request from '@/utils/request'

// 查询销售员沟通记录列表
export function listRecord(query) {
  return request({
    url: '/api/record/record/list',
    method: 'get',
    params: query
  })
}

// 查询销售员沟通记录详细
export function getRecord(id) {
  return request({
    url: '/api/record/record/' + id,
    method: 'get'
  })
}

// 新增销售员沟通记录
export function addRecord(data) {
  return request({
    url: '/api/record/record',
    method: 'post',
    data: data
  })
}

// 修改销售员沟通记录
export function updateRecord(data) {
  return request({
    url: '/api/record/record',
    method: 'put',
    data: data
  })
}

// 删除销售员沟通记录
export function delRecord(id) {
  return request({
    url: '/api/record/record/' + id,
    method: 'delete'
  })
}

// 导出销售员沟通记录
export function exportRecord(query) {
  return request({
    url: '/api/record/record/export',
    method: 'get',
    params: query
  })
}
