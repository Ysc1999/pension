import request from '@/utils/request'
export default {
    getCart() {
        return request({
            url: `/edu/front/cart/getCart`,
            method: 'get'
        })
    },
    pageCartCondition(current, limit, cartId) {
        return request({
            url: `/edu/front/cartDetail/pageCartCondition/${current}/${limit}/${cartId}`,
            method: 'get'
        })
    },

    removeGoods(goodsId, cartId) {
        return request({
            url: `/edu/front/cartDetail/removeGoods/${goodsId}/${cartId}`,
            method: 'get'
        })
    },

    updateCartDetail(cartDetail, indentId) {
        return request({
            url: `/edu/front/cartDetail/updateCartDetail/${indentId}`,
            method: 'post',
            data: cartDetail
        })
    },

    addCart(goodsId, goodsPrice) {
        return request({
            url: `/edu/front/cartDetail/addCart/${goodsId}/${goodsPrice}`,
            method: 'get'
        })
    },

    updateCart(cartTotal) {
        return request({
            url: `/edu/front/cart/updateCart/${cartTotal}`,
            method: 'get'
        })
    }
}
