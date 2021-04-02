<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户ID" prop="inUserId">
      <el-input v-model="dataForm.inUserId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="支付方式(0 账户付款 1 支付宝)" prop="payStatus">
      <el-input v-model="dataForm.payStatus" placeholder="支付方式(0 账户付款 1 支付宝)"></el-input>
    </el-form-item>
    <el-form-item label="用户姓名" prop="inUserName">
      <el-input v-model="dataForm.inUserName" placeholder="用户姓名"></el-input>
    </el-form-item>
    <el-form-item label="用户住址" prop="inUserAddress">
      <el-input v-model="dataForm.inUserAddress" placeholder="用户住址"></el-input>
    </el-form-item>
    <el-form-item label="用户手机号" prop="inUserPhone">
      <el-input v-model="dataForm.inUserPhone" placeholder="用户手机号"></el-input>
    </el-form-item>
    <el-form-item label="总金额" prop="total">
      <el-input v-model="dataForm.total" placeholder="总金额"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除，1删除，0未删除" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="逻辑删除，1删除，0未删除"></el-input>
    </el-form-item>
    <el-form-item label="付款时间" prop="payTime">
      <el-input v-model="dataForm.payTime" placeholder="付款时间"></el-input>
    </el-form-item>
    <el-form-item label="到货时间" prop="arrayTime">
      <el-input v-model="dataForm.arrayTime" placeholder="到货时间"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="变更时间" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder="变更时间"></el-input>
    </el-form-item>
    <el-form-item label="是否支付" prop="isPayed">
      <el-input v-model="dataForm.isPayed" placeholder="是否支付"></el-input>
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
          indentId: 0,
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
        },
        dataRule: {
          inUserId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          payStatus: [
            { required: true, message: '支付方式(0 账户付款 1 支付宝)不能为空', trigger: 'blur' }
          ],
          inUserName: [
            { required: true, message: '用户姓名不能为空', trigger: 'blur' }
          ],
          inUserAddress: [
            { required: true, message: '用户住址不能为空', trigger: 'blur' }
          ],
          inUserPhone: [
            { required: true, message: '用户手机号不能为空', trigger: 'blur' }
          ],
          total: [
            { required: true, message: '总金额不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '逻辑删除，1删除，0未删除不能为空', trigger: 'blur' }
          ],
          payTime: [
            { required: true, message: '付款时间不能为空', trigger: 'blur' }
          ],
          arrayTime: [
            { required: true, message: '到货时间不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '变更时间不能为空', trigger: 'blur' }
          ],
          isPayed: [
            { required: true, message: '是否支付不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.indentId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.indentId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/indent/info/${this.dataForm.indentId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
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
