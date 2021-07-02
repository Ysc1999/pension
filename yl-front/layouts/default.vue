<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="智慧养老服务平台">
            <img src="~/assets/img/logo.png" width="100%" alt="智慧养老服务平台">
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>
            <router-link to="/commodity" tag="li" active-class="current">
              <a>商品</a>
            </router-link>
            <router-link to="/cart" tag="li" active-class="current">
              <a>购物车</a>
            </router-link>
            <router-link to="/indent" tag="li" active-class="current">
              <a>订单</a>
            </router-link>
            <!-- <router-link to="/user" tag="li" active-class="current">
              <a>个人信息</a>
            </router-link> -->
          </ul>
           <!-- / nav -->
          <ul class="h-r-login">
              <li v-if="!loginInfo.userId" id="no-login">
                  <a href="/login" title="登录">

                      <span class="vam ml5">登录</span>
                  </a>
                  |
                  <a href="/register" title="注册">
                      <span class="vam ml5">注册</span>
                  </a>
              </li>
              <li v-if="loginInfo.userId" id="is-login-one" class="mr10">
                  <a id="headerMsgCountId" href="#" title="消息">

                  </a>
                  <q class="red-point" style="display: none">&nbsp;</q>
              </li>
              <li v-if="loginInfo.userId" id="is-login-two" class="h-r-user">
                  <a href="/user" title=loginInfo.userNickname>
                      <img
                          :src="loginInfo.userAvatar"
                          width="30"
                          height="30"
                          class="vam picImg"
                          alt
                          >
                      <span id="userNickname" class="vam disIb">{{ loginInfo.userNickname }}</span>
                  </a>
                  <a href="javascript:void(0);" title="退出" @click="logout()" class="ml5">退出</a>
              </li>
              <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>

        </div>
        <!-- <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside> -->
        <div class="clear"></div>
      </section>
    </header>
    <!-- /公共头引入 -->

    <nuxt/>

    <!-- 公共底引入 -->
    <footer id="footer">
      <section class="container">
        <div class>
          <h4 class="hLh30">
            <span class="fsize18 f-fM c-999">友情链接</span>
          </h4>
          <ul class="of flink-list">
            <li>
              <a href="http://www.baidu.com/" title="智慧养老服务平台" target="_blank">智慧养老服务平台</a>
            </li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="b-foot">
          <section class="fl col-7">
            <section class="mr20">
              <section class="b-f-link">
                <a href="#" title="关于我们" target="_blank">关于我们</a>|
                <a href="#" title="联系我们" target="_blank">联系我们</a>|
                <a href="#" title="帮助中心" target="_blank">帮助中心</a>|
                <a href="#" title="资源下载" target="_blank">资源下载</a>|
                <span>服务热线：010-56253825(北京) 0755-85293825(深圳)</span>
                <span>Email：info@atguigu.com</span>
              </section>
              <section class="b-f-link mt10">
                <span>©2020所有版权均归YSC团队所有 京ICP备17055252号</span>
              </section>
            </section>
          </section>
          <aside class="fl col-3 tac mt15">
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wx-icon.png" alt>
              </span>
            </section>
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wb-icon.png" alt>
              </span>
            </section>
          </aside>
          <div class="clear"></div>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>
<script>
import '~/assets/css/reset.css'
import '~/assets/css/theme.css'
import '~/assets/css/global.css'
import '~/assets/css/web.css'
import '~/assets/css/base.css'
import '~/assets/css/activity_tab.css'
import '~/assets/css/bottom_rec.css'
import '~/assets/css/nice_select.css'
import '~/assets/css/order.css'
import '~/assets/css/swiper-3.3.1.min.css'
import "~/assets/css/pages-weixinpay.css"

import cookie from 'js-cookie'
import loginApi from '@/api/login'

export default {
  data(){
    return{
      token:'',
      loginInfo:{
        userId: '',
        userAvatar: '',
        userNickname:''
      }
    }
  },
  created(){
    //获取路径里面token值
    this.token = this.$route.query.token
    if(this.token){//判断路径是否有token值
      this.wxLogin()
    }
    this.showInfo()
  },
  methods:{
    //微信登录显示的方法
    wxLogin(){
      //把token值放到cookie中
      cookie.set('Es_token',this.token,{domain:'localhost'})
      cookie.set('Es_user','',{domain:'localhost'})
      //调用接口，根据token获取用户信息
      loginApi.getLoginUserInfo()
        .then(response =>{
          this.loginInfo = response.data.data.userInfo
          cookie.set('Es_user',this.loginInfo ,{domain:'localhost'})
        })
    },
    //创建方法 从cookie获得用户信息
    showInfo(){
      //从cookie中获取用户信息
      var userStr = cookie.get('Es_user')
      console.log(cookie.get('Es_user'))
      console.log(cookie.get('Es_token'))
      //把返回的字符串转换json对象(js对象)
      //返回字符串如下 "{'name' : 'lucy' , 'age' : 20}" 只有去掉双引号
      if(userStr){
        this.loginInfo = JSON.parse(userStr)
      }
    },
    //退出
    //将cookie数据清空
    logout(){
      cookie.set('Es_token','',{domain:'localhost'})
      cookie.set('Es_user','',{domain:'localhost'})
      //回到首页面
      window.location.href = '/'
    }
  }
};
</script>
