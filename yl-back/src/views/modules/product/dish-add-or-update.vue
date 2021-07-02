<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="菜品种类名称" prop="dishKindName">
      <el-input v-model="dataForm.dishKindName" placeholder="菜品种类名称"></el-input>
    </el-form-item>
    <el-form-item label="菜品名称" prop="dishName">
      <el-input v-model="dataForm.dishName" placeholder="菜品名称"></el-input>
    </el-form-item>
    <el-form-item label="菜品单价" prop="dishPrice">
      <el-input v-model="dataForm.dishPrice" placeholder="菜品单价"></el-input>
    </el-form-item>
    <el-form-item label="菜品图片" prop="dishPicture">
      <el-input v-model="dataForm.dishPicture" placeholder="菜品图片"></el-input>
    </el-form-item>
    <el-form-item label="详情描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="详情描述"></el-input>
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
    <el-form-item label="菜品售出状态" prop="dishSaleStatus">
      <el-input v-model="dataForm.dishSaleStatus" placeholder="菜品售出状态"></el-input>
    </el-form-item>
    <el-form-item label="菜品上架状态" prop="dishStatus">
      <el-input v-model="dataForm.dishStatus" placeholder="菜品上架状态"></el-input>
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
          dishId: 0,
          dishKindName: '',
          dishName: '',
          dishPrice: '',
          dishPicture: '',
          description: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: '',
          dishSaleStatus: '',
          dishStatus: ''
        },
        dataRule: {
          dishKindName: [
            { required: true, message: '菜品种类名称不能为空', trigger: 'blur' }
          ],
          dishName: [
            { required: true, message: '菜品名称不能为空', trigger: 'blur' }
          ],
          dishPrice: [
            { required: true, message: '菜品单价不能为空', trigger: 'blur' }
          ],
          dishPicture: [
            { required: true, message: '菜品图片不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '详情描述不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '逻辑删除，1删除，0未删除不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '变更时间不能为空', trigger: 'blur' }
          ],
          dishSaleStatus: [
            { required: true, message: '菜品售出状态不能为空', trigger: 'blur' }
          ],
          dishStatus: [
            { required: true, message: '菜品上架状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.dishId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.dishId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/dish/info/${this.dataForm.dishId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.dishKindName = data.dish.dishKindName
                this.dataForm.dishName = data.dish.dishName
                this.dataForm.dishPrice = data.dish.dishPrice
                this.dataForm.dishPicture = data.dish.dishPicture
                this.dataForm.description = data.dish.description
                this.dataForm.isDelete = data.dish.isDelete
                this.dataForm.gmtCreate = data.dish.gmtCreate
                this.dataForm.gmtModified = data.dish.gmtModified
                this.dataForm.dishSaleStatus = data.dish.dishSaleStatus
                this.dataForm.dishStatus = data.dish.dishStatus
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
              url: this.$http.adornUrl(`/ylservice/dish/${!this.dataForm.dishId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'dishId': this.dataForm.dishId || undefined,
                'dishKindName': this.dataForm.dishKindName,
                'dishName': this.dataForm.dishName,
                'dishPrice': this.dataForm.dishPrice,
                'dishPicture': this.dataForm.dishPicture,
                'description': this.dataForm.description,
                'isDelete': this.dataForm.isDelete,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified,
                'dishSaleStatus': this.dataForm.dishSaleStatus,
                'dishStatus': this.dataForm.dishStatus
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
