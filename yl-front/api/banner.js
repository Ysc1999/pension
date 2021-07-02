import request from '@/utils/request'
export default {
    getBannerList() {
        return request({
            url: `/edu/front/banner/show`,
            method: 'get'
        })
    }
}
