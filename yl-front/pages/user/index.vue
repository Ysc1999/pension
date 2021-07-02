<template>
  <div class="main">
    <!-- 个人信息展示 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">个人信息</span>
        </h2>
      </header>
    <div class="t-infor-wrap">
        <!-- 个人基本信息 -->
    <el-form label-width="120px" :model="user" ref="userForm" :rules="baseFormRules">
      <el-form-item label="用户名称">
        <el-input v-model="user.userNickname"/>
      </el-form-item>

      <el-form-item label="用户性别">
        <el-select v-model="user.userSex" clearable placeholder="请选择" disabled="disabled">
          <el-option :value="1" label="男"/>
          <el-option :value="0" label="女"/>
        </el-select>
      </el-form-item>
      
      <el-form-item label="身份证号" prop="identityNumber">
        <el-input v-model="user.identityNumber"/>
      </el-form-item>

      <el-form-item label="学号" prop="studentId">
        <el-input v-model="user.studentId"/>
      </el-form-item>

      <el-form-item label="用户邮箱" prop="userEmail">
        <el-input v-model="user.userEmail"/>
      </el-form-item>

      <el-form-item label="手机号" prop="userPhone">
        <el-input v-model="user.userPhone"/>
      </el-form-item>

      <el-form-item label="密码"> 
        <el-input v-model="user.userPassword" :type="[flag?'text':'password']" style="border:1px solid #fff;" placeholder="请输入密码" @blur="openCheck()">
          <i slot="suffix" :class="[flag?'el-icon-minus':'el-icon-view']" style="margin-top:8px;font-size:18px;" autocomplete="auto" @click="flag=!flag" />
        </el-input>
      </el-form-item>
      
      <el-form-item label="确认密码"> 
        <el-input v-model="checkPassword" :type="[checkflag?'text':'password']" style="border:1px solid #fff;" placeholder="请确认密码" :disabled="disable" @blur="check()">
          <i slot="suffix" :class="[checkflag?'el-icon-minus':'el-icon-view']" style="margin-top:8px;font-size:18px;" autocomplete="auto" @click="checkflag=!checkflag" />
        </el-input>
      </el-form-item>

      <el-upload
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :action="'http://localhost:9001/edu/fileoss'"
            class="avatar-uploader  ">
            <img :src="user.userAvatar" width="150px" height="150px" style="border-radius: 50% ;margin-left:120px">
        </el-upload>

     <el-form-item>
        <el-button  type="primary" @click="updateUser">确认完善个人信息</el-button>
      </el-form-item>
    </el-form>

    
    </div>
    </section>
  </div>
</template>

<script>
import userApi from '@/api/user'

export default {
  data(){
    return {
      flag: false,
      checkflag:false,
      pwdType: 'password',
      baseFormRules:{
        identityNumber:[
          { required: true,  message: '身份证号不能为空',trigger: 'blur' },
          { validator: this.checkCard, trigger: 'blur' }
        ],
        studentId:{ required: true,trigger: 'blur',validator: this.checkStudentId },
        userEmail:{ required: true,trigger: 'blur',validator: this.checkUserEmail },
        userPhone:{ required: true,trigger: 'blur',validator: this.checkUserPhone },
      },
      user:{
        userNickname:'',
        identityNumber:'',
        studentId:'',
        userPassword:''
      },
      checkPassword:'',
      disable:true, //先禁止确认密码
    }
  },
  created(){
    this.getUser()
  },
  methods:{
    //开启确认密码
    openCheck(){
      if(this.user.userPassword != ""){
        this.disable = false
      }else{
        this.disable = true
      }
    },
    //确认密码
    check(){
      if(this.user.userPassword != this.checkPassword){
        this.$message.warning('前后密码不一致哦,请检验密码~')
        return false
      }
    },
    checkUserPhone(){
      this.user.userPhone = this.user.userPhone.trim()
      var phone = this.user.userPhone
      let reg = /(^\d{11}$)/
      if(!reg.test(phone)){
        this.$message.warning('请输入合法联系方式~')
        return 
      }
    },
    //检查身份证是否合法
    checkCard(){
      //去除身份证两端的空格
      this.user.identityNumber = this.user.identityNumber.trim()
      var identity = this.user.identityNumber
      //第一步先初步判断  
      if(identity == "" || identity == null){
        this.$message.warning('身份证号不许为空呦~')
        return
      }
      //第二步 判断长度
      if(identity.length != 15 && identity.length != 18){
        this.$message.warning('请输入正确长度的身份证号')
        return
      }
      //第三步 15位身份证号 15位时全为数字
      if(identity.length == 15){
        if(this.is15Card(identity)){
          //根据身份证号确定性别
          let sex = identity.substring(13,14) 
          //偶数 是女生
          if(sex % 2 === 0){
            this.user.userSex = 0
          }
          //奇数 是男生
          if(sex % 2 === 1){
            this.user.userSex = 1
          }
        }
        else {
          this.$message.warning('您的一代身份证号有误！请输入你真实的身份证号，确保你的利益得到保障！');
          return 
        }
      }
      //第四步 18位身份证号 18位前17位为数字，最后一位是校验位，可能为数字或字符X 
      if(identity.length == 18){
        if(this.is18Card(identity)){
          //根据身份证号确定性别
          let sex = identity.substring(16,17);
          //偶数 是女生
          if(sex % 2 === 0){
            this.user.userSex = 0
          }
          //奇数 是男生
          if(sex % 2 === 1){
            this.user.userSex = 1
          }
        }
        else {
          this.$message.warning('您的二代身份证号有误！请输入你真实的身份证号，确保你的利益得到保障！');
          return 
        }
      }
    },
    //判断15位身份证号的有效性
    is15Card(value){
      let reg = /(^\d{15}$)/
      if (!reg.test(value)) {
        this.$message.warning('您的一代身份证只可以输入数字！');
      }
    },
    is18Card(value){
      let reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (!reg.test(value)) {
        this.$message.warning('您的二代身份证只可以输入数字或最后一位为X！');
        return false
      }
      return true
    },
    //判断学号有效性
    checkStudentId(){
      let reg = /(^\d{8}$)/
      if(!reg.test(this.user.studentId)){
        this.$message.warning('学号只可以是8位数字')
      }
    },
    //判断邮箱有效性
    checkUserEmail(){
      let reg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
      if(!reg.test(this.user.userEmail)){
        this.$message.warning('请输入合法邮箱')
      }
    },
    //头像上传
    handleAvatarSuccess(res, file) {
      console.log(URL.createObjectURL(file.raw))// base64编码
      this.user.userAvatar = res.data.url
    },
    //提交前
    beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
    },
    getUser(){
      userApi.getUserInfo()
        .then(response =>{
          this.user = response.data.data.userInfo
          this.user.userPassword = ''
          this.checkPassword = ''
        })
    },
    updateUser() {
      var check = true
      //修改密码才检验
      if(this.user.userPassword != ''){
        check = this.check()
      }
      if(check){
        userApi.updateUserInfo(this.user)
          .then(response => {
            //提示信息
            this.$message({
                type: 'success',
                message: '修改成功!'
            });
            window.location.herf="/"
            })
      }
    }
  }
}
</script>