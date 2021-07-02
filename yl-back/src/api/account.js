import request from '@/utils/httpRequest'

export default ({
  selectAllAccount() {
    return request({
      url: "/ylservice/account/list",
      method: 'get'
    })
  },
})
