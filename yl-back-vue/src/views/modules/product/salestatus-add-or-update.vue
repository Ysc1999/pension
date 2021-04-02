<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="逻辑删除，1删除，0未删除" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="逻辑删除，1删除，0未删除"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="变更时间" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder="变更时间"></el-input>
    </el-form-item>
    <el-form-item label="菜品售出状态（0售完，1未售完）" prop="saleStatus">
      <el-input v-model="dataForm.saleStatus" placeholder="菜品售出状态（0售完，1未售完）"></el-input>
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
          saleStatusId: 0,
          isDelete: '',
          gmtCreate: '',
          gmtModified: '',
          saleStatus: ''
        },
        dataRule: {
          isDelete: [
            { required: true, message: '逻辑删除，1删除，0未删除不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '变更时间不能为空', trigger: 'blur' }
          ],
          saleStatus: [
            { required: true, message: '菜品售出状态（0售完，1未售完）不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.saleStatusId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.saleStatusId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/salestatus/info/${this.dataForm.saleStatusId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.isDelete = data.saleStatus.isDelete
                this.dataForm.gmtCreate = data.saleStatus.gmtCreate
                this.dataForm.gmtModified = data.saleStatus.gmtModified
                this.dataForm.saleStatus = data.saleStatus.saleStatus
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
              url: this.$http.adornUrl(`/ylservice/salestatus/${!this.dataForm.saleStatusId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'saleStatusId': this.dataForm.saleStatusId || undefined,
                'isDelete': this.dataForm.isDelete,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified,
                'saleStatus': this.dataForm.saleStatus
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
