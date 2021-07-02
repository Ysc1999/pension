import request from '@/utils/request'

export default {
    //根据手机号发送验证码
    setCode(phone) {
        return request({
            url: `edu/msm/send/${phone}`,
            method: 'get'
        })
    },
    //注册方法
    registerMember(registerVo) {
        return request({
            url: `edu/front/user/register`,
            method: 'post',
            data: registerVo
        })
    }
}
