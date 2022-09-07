import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/admin/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/admin/logout',
    method: 'post'
  })
}

export function listAdmin(params){
  return request({
    url: '/api/admin',
    method:'get',
    params
  })
}
export function getAdmin(id){
  return request({
    url: '/api/admin/'+id,
    method:'get'
  })
}

export function addAdmin(data) {
  return request({
    url: '/api/admin',
    method: 'post',
    data
  })
}

export function editAdmin(data) {
  return request({
    url: '/api/admin/update',
    method: 'put',
    data
  })
}

export function delAdmin(ids) {
  return request({
    url: '/api/admin/'+ids,
    method: 'delete'
  })
}
