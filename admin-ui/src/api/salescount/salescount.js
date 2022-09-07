import request from '@/utils/request'

// 查询销售统计列表
export function listSalescount(query) {
  return request({
    url: '/api/salescount/salescount/list',
    method: 'get',
    params: query
  })
}

// 查询销售统计详细
export function getSalescount(countId) {
  return request({
    url: '/api/salescount/salescount/' + countId,
    method: 'get'
  })
}

// 新增销售统计
export function addSalescount(data) {
  return request({
    url: '/api/salescount/salescount',
    method: 'post',
    data: data
  })
}

// 修改销售统计
export function updateSalescount(data) {
  return request({
    url: '/api/salescount/salescount',
    method: 'put',
    data: data
  })
}

// 删除销售统计
export function delSalescount(countId) {
  return request({
    url: '/api/salescount/salescount/' + countId,
    method: 'delete'
  })
}

// 导出销售统计
export function exportSalescount(query) {
  return request({
    url: '/api/salescount/salescount/export',
    method: 'get',
    params: query
  })
}