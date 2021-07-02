import request from '@/utils/request'

export default {

    //获取全部商品类别
    getAllKindBase() {
        return request({
            url: `/edu/front/kindBase/selectAll`,
            method: 'get'
        })
    },

    getCommodityFrontList(page, limit) {
        return request({
            url: `/edu/front/commodityFront/getCommodityFrontList/${page}/${limit}`,
            method: 'get'
        })
    },
    getCommodityOnShelvesFrontList(page, limit) {
        return request({
            url: `/edu/front/commodityFront/getCommodityOnShelvesFrontList/${page}/${limit}`,
            method: 'get'
        })
    },
    getCommodityByNameFrontList(page, limit, commodity) {
        return request({
            url: `/edu/front/commodityFront/getCommodityByNameFrontList/${page}/${limit}`,
            method: 'post',
            data: commodity
        })
    },
    getCommodityByKindFrontList(page, limit, kindBase) {
        return request({
            url: `/edu/front/commodityFront/getCommodityByKindFrontList/${page}/${limit}`,
            method: 'post',
            data: kindBase
        })
    },

    deleteCommodity(id) {
        return request({
            url: `/edu/front/commodityFront/deleteCommodity/${id}`,
            method: 'delete'
        })
    },
    setCommodityOnShelves(id) {
        return request({
            url: `/edu/front/commodityFront/setCommodityOnShelves/${id}`,
            method: 'post'
        })
    },
    setCommodityOffShelves(id) {
        return request({
            url: `/edu/front/commodityFront/setCommodityOffShelves/${id}`,
            method: 'post'
        })
    },
    selectCommodityById(id) {
        return request({
            url: `/edu/front/commodityFront/selectCommodityById/${id}`,
            method: 'get',
        })
    },
    updateCommodity(commodity) {
        return request({
            url: `/edu/front/commodityFront/updateCommodity`,
            method: 'post',
            data: commodity
        })
    },
    getOneCommodity(id) {
        return request({
            url: `/edu/front/commodityFront/getOneCommodity/${id}`,
            method: 'get'
        })
    }


}
