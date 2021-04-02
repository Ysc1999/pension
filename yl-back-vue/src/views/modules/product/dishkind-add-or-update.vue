<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="菜品种类名称" prop="kindName">
      <el-input v-model="dataForm.kindName" placeholder="菜品种类名称"></el-input>
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
          kindId: 0,
          kindName: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          kindName: [
            { required: true, message: '菜品种类名称不能为空', trigger: 'blur' }
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
        this.dataForm.kindId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.kindId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/dishkind/info/${this.dataForm.kindId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.kindName = data.dishKind.kindName
                this.dataForm.isDelete = data.dishKind.isDelete
                this.dataForm.gmtCreate = data.dishKind.gmtCreate
                this.dataForm.gmtModified = data.dishKind.gmtModified
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
              url: this.$http.adornUrl(`/ylservice/dishkind/${!this.dataForm.kindId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'kindId': this.dataForm.kindId || undefined,
                'kindName': this.dataForm.kindName,
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
