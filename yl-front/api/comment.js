import request from '@/utils/request'
export default {
    selectByCom(commodityId) {
        return request({
            url: `/edu/front/comment/selectByCom/${commodityId}`,
            method: 'get'
        })
    },
    addComment(commodityId,content){
        return request({
            url: `/edu/front/comment/addComment/${commodityId}`,
            method:'post',
            params:{
                "content":content
            }
        })
    }
}
