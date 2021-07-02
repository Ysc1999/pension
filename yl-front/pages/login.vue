<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">

        <el-form-item class="input-prepend restyle" prop="userName"
                      :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
          <div>
            <el-input type="text" placeholder="用户名" v-model="user.userName"/>
            <i class="iconfont icon-phone"/>
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="userPassword"
                      :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="密码" v-model="user.userPassword"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogin()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://localhost:8150/api/ucenter/wx/login"><i
            class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
  import '~/assets/css/sign.css'
  import '~/assets/css/iconfont.css'

  import cookie from 'js-cookie'
  import loginApi from '@/api/login'

  export default {
    layout: 'sign',

    data() {
      return {
        //封装用户名和密码对象
        user: {
          userName: '',
          userPassword: ''
        },
        //用户信息
        loginInfo: {}
      }
    },

    methods: {
      submitLogin() {
        loginApi.checkLogin(this.user)
          .then(response => {
            console.log(response)
            //如果返回的是错误的 则返回错误信息
            if (response.data.code == 500) {
              this.$message({
                type: 'error',
                message: response.data.message
              })
              return callback()
            } else {
              this.$message({
                type: 'success',
                message: '登录成功'
              })
              //跳转页面
              //this.$router.push({path:'/'})
              window.location.href = "/"
            }
          })

      }
    }
  }
</script>
<style>
  .el-form-item__error {
    z-index: 9999999;
  }
</style>
