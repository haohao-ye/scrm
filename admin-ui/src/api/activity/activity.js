import request from '@/utils/request'

// 查询营销活动列表
export function listActivity(query) {
  return request({
    url: '/api/activity/activity/list',
    method: 'get',
    params: query
  })
}

// 查询营销活动详细
export function getActivity(id) {
  return request({
    url: '/api/activity/activity/' + id,
    method: 'get'
  })
}

// 新增营销活动
export function addActivity(data) {
  return request({
    url: '/api/activity/activity',
    method: 'post',
    data: data
  })
}

// 修改营销活动
export function updateActivity(data) {
  return request({
    url: '/api/activity/activity',
    method: 'put',
    data: data
  })
}

// 删除营销活动
export function delActivity(id) {
  return request({
    url: '/api/activity/activity/' + id,
    method: 'delete'
  })
}

// 导出营销活动
export function exportActivity(query) {
  return request({
    url: '/api/activity/activity/export',
    method: 'get',
    params: query
  })
}