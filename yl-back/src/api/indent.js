import request from '@/utils/httpRequest'

export default ({
  //根据参数选择订单信息
  selectByParams(page, limit, inUserName, beginTime, endTime, indentStatus) {
    return request({
      url: '/ylservice/indent/selectByParams',
      method: 'post',
      data: {
        page: page,
        limit: limit,
        inUserName: inUserName,
        beginTime: beginTime,
        endTime: endTime,
        indentStatus: indentStatus
      }
    })
  },
  //根据订单id查询订单详情信息 返回IndentDetailResult类型
  selectIndentDetailById(deIndentId){
    return request({
      url:`/ylservice/indentdetail/info/${deIndentId}`,
      method:'get'
    })
  },
  deleteIndentDetailDish(indentId,dishId){
    return request({
      url:`/ylservice/indentdetail/deleteIndentDetailDish/${indentId}/${dishId}`,
      method:'delete'
    })
  }

})

