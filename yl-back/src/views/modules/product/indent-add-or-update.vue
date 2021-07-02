<template>
  <el-dialog
    title="订单详情"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="120px">
      <el-form-item label="订单ID" prop="indentId">
        <span>{{dataForm.indentId}}</span>
      </el-form-item>
      <el-form-item label="用户ID" prop="inUserId">
        <span>{{dataForm.inUserId}}</span>
      </el-form-item>
      <el-form-item v-if="dataForm.payStatus != 2" label="支付方式" prop="payStatus">
        <span v-if="dataForm.payStatus === 0">充值表付款</span>
        <span v-if="dataForm.payStatus === 1">支付宝付款</span>
      </el-form-item>
      <el-form-item label="用户姓名" prop="inUserName">
        <span>{{dataForm.inUserName}}</span>
      </el-form-item>
      <el-form-item label="用户住址" prop="inUserAddress">
        <span>{{dataForm.inUserAddress}}</span>
      </el-form-item>
      <el-form-item label="用户手机号" prop="inUserPhone">
        <span>{{dataForm.inUserPhone}}</span>
      </el-form-item>
      <el-form-item label="用户身份证号" prop="userIdentify">
        <span>{{dataForm.userIdentify}}</span>
      </el-form-item>
      <el-form-item label="账户余额" prop="amount">
        <span>{{dataForm.amount}}元</span>
      </el-form-item>
      <el-form-item v-if="dataForm.isPayed != 1" label="下单时间" prop="payTime">
        <span>{{dataForm.payTime}}</span>
      </el-form-item>
      <!--<el-form-item v-if="dataForm.isPayed == 2 || dataForm.isPayed == 3" label="下单时间" prop="gmtCreate">
        <span>{{dataForm.gmtCreate}}</span>
      </el-form-item>-->
      <el-form-item v-if="dataForm.isPayed == 3" label="到货时间" prop="arrayTime">
        <span>{{dataForm.arrayTime}}</span>
      </el-form-item>
      <el-form-item v-if="dataForm.isPayed == 3" label="完成时间" prop="gmtModified">
        <span>{{dataForm.gmtModified}}</span>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="visible = false">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data() {
      return {
        visible: false,
        dataForm: {
          indentId: '',
          inUserId: '',
          payStatus: '',
          inUserName: '',
          inUserAddress: '',
          inUserPhone: '',
          total: '',
          isDelete: '',
          payTime: '',
          arrayTime: '',
          gmtCreate: '',
          gmtModified: '',
          isPayed: ''
        }
      }
    },
    methods: {
      init(id) {
        console.log("aa" + id)
        this.dataForm.indentId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.indentId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/indent/info/${id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.indentId = data.indent.indentId
                this.dataForm.inUserId = data.indent.inUserId
                this.dataForm.payStatus = data.indent.payStatus
                this.dataForm.inUserName = data.indent.inUserName
                this.dataForm.inUserAddress = data.indent.inUserAddress
                this.dataForm.inUserPhone = data.indent.inUserPhone
                this.dataForm.total = data.indent.total
                this.dataForm.isDelete = data.indent.isDelete
                this.dataForm.payTime = data.indent.payTime
                this.dataForm.arrayTime = data.indent.arrayTime
                this.dataForm.gmtCreate = data.indent.gmtCreate
                this.dataForm.gmtModified = data.indent.gmtModified
                this.dataForm.isPayed = data.indent.isPayed

                this.dataForm.userIdentify = data.user.userIdentify
                this.dataForm.amount = data.account.amount
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/indent/${!this.dataForm.indentId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'indentId': this.dataForm.indentId || undefined,
                'inUserId': this.dataForm.inUserId,
                'payStatus': this.dataForm.payStatus,
                'inUserName': this.dataForm.inUserName,
                'inUserAddress': this.dataForm.inUserAddress,
                'inUserPhone': this.dataForm.inUserPhone,
                'total': this.dataForm.total,
                'isDelete': this.dataForm.isDelete,
                'payTime': this.dataForm.payTime,
                'arrayTime': this.dataForm.arrayTime,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified,
                'isPayed': this.dataForm.isPayed
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
