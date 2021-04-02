<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="菜品ID" prop="deDishId">
      <el-input v-model="dataForm.deDishId" placeholder="菜品ID"></el-input>
    </el-form-item>
    <el-form-item label="单品数目" prop="num">
      <el-input v-model="dataForm.num" placeholder="单品数目"></el-input>
    </el-form-item>
    <el-form-item label="逻辑删除，1删除，0未删除" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="逻辑删除，1删除，0未删除"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="变更时间" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder="变更时间"></el-input>
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
          deIndentId: 0,
          deDishId: '',
          num: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          deDishId: [
            { required: true, message: '菜品ID不能为空', trigger: 'blur' }
          ],
          num: [
            { required: true, message: '单品数目不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '逻辑删除，1删除，0未删除不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '变更时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.deIndentId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.deIndentId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/indentdetail/info/${this.dataForm.deIndentId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.deDishId = data.indentDetail.deDishId
                this.dataForm.num = data.indentDetail.num
                this.dataForm.isDelete = data.indentDetail.isDelete
                this.dataForm.gmtCreate = data.indentDetail.gmtCreate
                this.dataForm.gmtModified = data.indentDetail.gmtModified
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
              url: this.$http.adornUrl(`/ylservice/indentdetail/${!this.dataForm.deIndentId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'deIndentId': this.dataForm.deIndentId || undefined,
                'deDishId': this.dataForm.deDishId,
                'num': this.dataForm.num,
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
