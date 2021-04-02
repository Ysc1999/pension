<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="骑手名称，可以重复" prop="riderName">
      <el-input v-model="dataForm.riderName" placeholder="骑手名称，可以重复"></el-input>
    </el-form-item>
    <el-form-item label="骑手编号，唯一标识" prop="nkRiderNo">
      <el-input v-model="dataForm.nkRiderNo" placeholder="骑手编号，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="骑手密码" prop="riderPassword">
      <el-input v-model="dataForm.riderPassword" placeholder="骑手密码"></el-input>
    </el-form-item>
    <el-form-item label="骑手头像，可以为null" prop="riderAvater">
      <el-input v-model="dataForm.riderAvater" placeholder="骑手头像，可以为null"></el-input>
    </el-form-item>
    <el-form-item label="骑手电话，唯一标识" prop="nkRiderPhone">
      <el-input v-model="dataForm.nkRiderPhone" placeholder="骑手电话，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除，1删除，0未删除" prop="deleteFlag">
      <el-input v-model="dataForm.deleteFlag" placeholder="逻辑删除，1删除，0未删除"></el-input>
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
          pkRiderId: 0,
          riderName: '',
          nkRiderNo: '',
          riderPassword: '',
          riderAvater: '',
          nkRiderPhone: '',
          deleteFlag: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          riderName: [
            { required: true, message: '骑手名称，可以重复不能为空', trigger: 'blur' }
          ],
          nkRiderNo: [
            { required: true, message: '骑手编号，唯一标识不能为空', trigger: 'blur' }
          ],
          riderPassword: [
            { required: true, message: '骑手密码不能为空', trigger: 'blur' }
          ],
          riderAvater: [
            { required: true, message: '骑手头像，可以为null不能为空', trigger: 'blur' }
          ],
          nkRiderPhone: [
            { required: true, message: '骑手电话，唯一标识不能为空', trigger: 'blur' }
          ],
          deleteFlag: [
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
        this.dataForm.pkRiderId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.pkRiderId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/rider/info/${this.dataForm.pkRiderId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.riderName = data.rider.riderName
                this.dataForm.nkRiderNo = data.rider.nkRiderNo
                this.dataForm.riderPassword = data.rider.riderPassword
                this.dataForm.riderAvater = data.rider.riderAvater
                this.dataForm.nkRiderPhone = data.rider.nkRiderPhone
                this.dataForm.deleteFlag = data.rider.deleteFlag
                this.dataForm.gmtCreate = data.rider.gmtCreate
                this.dataForm.gmtModified = data.rider.gmtModified
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
              url: this.$http.adornUrl(`/ylservice/rider/${!this.dataForm.pkRiderId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'pkRiderId': this.dataForm.pkRiderId || undefined,
                'riderName': this.dataForm.riderName,
                'nkRiderNo': this.dataForm.nkRiderNo,
                'riderPassword': this.dataForm.riderPassword,
                'riderAvater': this.dataForm.riderAvater,
                'nkRiderPhone': this.dataForm.nkRiderPhone,
                'deleteFlag': this.dataForm.deleteFlag,
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
