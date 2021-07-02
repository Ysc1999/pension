import request from '@/utils/request'


export default {
  selectIndentByUserId(user_id) {
    return request({
      url: `/ylservice/indentFront/getIndent/${user_id}`,
      method: 'get'
    })
  },


  //查询该用户这次要支付的订单详情
  getOneIndentForPaid(indent_id) {
    return request({
      url: `/ylservice/indentDetailFront/getIndentDetail/${indent_id}`,
      method: 'get'
    })
  },


  //修改订单状态 --> 支付订单
  pay(indent) {
    return request({
      url: '/ylservice/payConfig/pay',
      method: 'post',
      data: indent,
      dataType : "html"
    })
  },

  //通过账户支付
  payByAccount(indentId,total){
    return request({
      url:`/ylservice/payConfig/payByAccount/${indentId}/${total}`,
      method:'get'
    })
  },

  //取消订单
  cancelIndent(indentId) {
    return request({
      url: `/ylservice/indentFront/deleteIndent/${indentId}`,
      method: 'delete'
    })
  },
  /*******************************************Old Version***********************************************************/
  insertIndent() {
    return request({
      url: `/edu/front/indent/insertIndent`,
      method: 'get'
    })
  },
  updateCart(indentId, indentTotal) {
    return request({
      url: `edu/front/indent/updateCart`,
      method: 'post',
      params: {
        indentId: indentId,
        indentTotal: indentTotal
      }
    })
  },



  //查询登录用户的所有订单信息
  getAllIndentOfOne() {
    return request({
      url: `edu/front/indent/getAllIndentOfOne`,
      method: 'get'
    })
  }
}
