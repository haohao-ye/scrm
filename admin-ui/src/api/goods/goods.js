import request from '@/utils/request'


// 查询商品列表

export function listGoods(query) {
  return request({
    url: '/api/goods/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品管理详细

export function getGoods(id) {
  return request({
    url: '/api/goods/goods/' + id,
    method: 'get'
  })
}


// 新增商品
export function addGoods(data) {
  return request({
    url: '/api/goods/goods',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGoods(data) {
  return request({
    url: '/api/goods/goods',
    method: 'put',
    data: data
  })
}


// 删除商品
export function delGoods(id) {
  return request({
    url: '/api/goods/goods/' + id,
    method: 'delete'
  })
}

// 导出商品
export function exportGoods(query) {
  return request({
    url: '/api/goods/goods/export',
    method: 'get',
    params: query
  })
}


// 订单入库
export function updateByOrder(data) {
  return request({
    url: '/api/goods/goods/order',
    method: 'put',
    data: data
  })
}

export function getGoodSum(query) {
  return request({
    url: '/api/goods/goods/count',
    method: 'get',
    params: query

  })
}
