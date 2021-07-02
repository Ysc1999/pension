import request from '@/utils/request'

export default {
  //查询当前用户信息用于登录
  checkLogin(user) {
    return request({
      url: '/ylservice/userFront/checkLogin',
      method: 'post',
      data: user
    })
  }
}
