//封装axios
import axios from 'axios'
import { messageBox, Message } from 'element-ui'
import cookie from 'js-cookie'
// 创建axios实例
const service = axios.create({
    baseURL: 'http://localhost:8300/api', // api的base_url
    timeout: 20000 // 请求超时时间
})

//表示每次request请求前都拦截请求
/*service.interceptors.request.use(
    config => {
        //debugger
        //如果有token 每次向后台发送请求时在请求头中放置token信息
        if (cookie.get('Es_token')) {
            config.headers['token'] = cookie.get('Es_token');
        }
        return config
    },
    err => {
        return Promise.reject(err)
    }
)*/

export default service
