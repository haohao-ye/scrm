import request from '@/utils/request'

// 查询订单表列表
export function listOrders(query) {
  return request({
    url: '/api/orders/orders/list',
    method: 'get',
    params: query
  })
}

// 查询订单表详细
export function getOrders(consumptionListId) {
  return request({
    url: '/api/orders/orders/' + consumptionListId,
    method: 'get'
  })
}

// 新增订单表
export function addOrders(data) {
  return request({
    url: '/api/orders/orders',
    method: 'post',
    data: data
  })
}

// 修改订单表
export function updateOrders(data) {
  return request({
    url: '/api/orders/orders',
    method: 'put',
    data: data
  })
}

// 删除订单表
export function delOrders(consumptionListId) {
  return request({
    url: '/api/orders/orders/' + consumptionListId,
    method: 'delete'
  })
}

// 导出订单表
export function exportOrders(query) {
  return request({
    url: '/api/orders/orders/export',
    method: 'get',
    params: query
  })
}

export function getMoney(query) {
  return request({
    url: '/api/orders/orders/money',
    method: 'get',
    params: query

  })
}

export function getOrderSum(query) {
  return request({
    url: '/api/orders/orders/count',
    method: 'get',
    params: query

  })
}