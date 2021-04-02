<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户姓名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="用户姓名"></el-input>
    </el-form-item>
    <el-form-item label="用户年龄" prop="userAge">
      <el-input v-model="dataForm.userAge" placeholder="用户年龄"></el-input>
    </el-form-item>
    <el-form-item label="用户性别(0 女 1 男)" prop="userSex">
      <el-input v-model="dataForm.userSex" placeholder="用户性别(0 女 1 男)"></el-input>
    </el-form-item>
    <el-form-item label="用户住址" prop="userAddress">
      <el-input v-model="dataForm.userAddress" placeholder="用户住址"></el-input>
    </el-form-item>
    <el-form-item label="身份证号" prop="userIdentify">
      <el-input v-model="dataForm.userIdentify" placeholder="身份证号"></el-input>
    </el-form-item>
    <el-form-item label="用户手机号" prop="userPhone">
      <el-input v-model="dataForm.userPhone" placeholder="用户手机号"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除，1删除，0未删除" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="逻辑删除，1删除，0未删除"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder="更新时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          userId: 0,
          userName: '',
          userAge: '',
          userSex: '',
          userAddress: '',
          userIdentify: '',
          userPhone: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          userName: [
            { required: true, message: '用户姓名不能为空', trigger: 'blur' }
          ],
          userAge: [
            { required: true, message: '用户年龄不能为空', trigger: 'blur' }
          ],
          userSex: [
            { required: true, message: '用户性别(0 女 1 男)不能为空', trigger: 'blur' }
          ],
          userAddress: [
            { required: true, message: '用户住址不能为空', trigger: 'blur' }
          ],
          userIdentify: [
            { required: true, message: '身份证号不能为空', trigger: 'blur' }
          ],
          userPhone: [
            { required: true, message: '用户手机号不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '逻辑删除，1删除，0未删除不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.userId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.userId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/user/info/${this.dataForm.userId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.user.userName
                this.dataForm.userAge = data.user.userAge
                this.dataForm.userSex = data.user.userSex
                this.dataForm.userAddress = data.user.userAddress
                this.dataForm.userIdentify = data.user.userIdentify
                this.dataForm.userPhone = data.user.userPhone
                this.dataForm.isDelete = data.user.isDelete
                this.dataForm.gmtCreate = data.user.gmtCreate
                this.dataForm.gmtModified = data.user.gmtModified
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/user/${!this.dataForm.userId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.userId || undefined,
                'userName': this.dataForm.userName,
                'userAge': this.dataForm.userAge,
                'userSex': this.dataForm.userSex,
                'userAddress': this.dataForm.userAddress,
                'userIdentify': this.dataForm.userIdentify,
                'userPhone': this.dataForm.userPhone,
                'isDelete': this.dataForm.isDelete,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
