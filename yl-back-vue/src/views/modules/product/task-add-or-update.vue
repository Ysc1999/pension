<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="送餐编号，唯一标识" prop="nkTaskNo">
      <el-input v-model="dataForm.nkTaskNo" placeholder="送餐编号，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="骑手表主键，唯一标识" prop="fkRiderId">
      <el-input v-model="dataForm.fkRiderId" placeholder="骑手表主键，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="骑手名称，可以重复" prop="fkRiderName">
      <el-input v-model="dataForm.fkRiderName" placeholder="骑手名称，可以重复"></el-input>
    </el-form-item>
    <el-form-item label="骑手编号，唯一标识" prop="fkRiderNo">
      <el-input v-model="dataForm.fkRiderNo" placeholder="骑手编号，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="骑手电话，唯一标识" prop="fkRiderPhone">
      <el-input v-model="dataForm.fkRiderPhone" placeholder="骑手电话，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="订单表主键，唯一标识" prop="fkOrderId">
      <el-input v-model="dataForm.fkOrderId" placeholder="订单表主键，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="订单编号，唯一标识" prop="fkOrderNo">
      <el-input v-model="dataForm.fkOrderNo" placeholder="订单编号，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="菜品表主键，唯一标识" prop="fkDishId">
      <el-input v-model="dataForm.fkDishId" placeholder="菜品表主键，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="菜品编号，唯一标识" prop="fkDishNo">
      <el-input v-model="dataForm.fkDishNo" placeholder="菜品编号，唯一标识"></el-input>
    </el-form-item>
    <el-form-item label="菜品名称" prop="fkDishName">
      <el-input v-model="dataForm.fkDishName" placeholder="菜品名称"></el-input>
    </el-form-item>
    <el-form-item label="送餐状态：0未配送，1配送中，2已送达" prop="taskStatus">
      <el-input v-model="dataForm.taskStatus" placeholder="送餐状态：0未配送，1配送中，2已送达"></el-input>
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
          pkTaskId: 0,
          nkTaskNo: '',
          fkRiderId: '',
          fkRiderName: '',
          fkRiderNo: '',
          fkRiderPhone: '',
          fkOrderId: '',
          fkOrderNo: '',
          fkDishId: '',
          fkDishNo: '',
          fkDishName: '',
          taskStatus: '',
          deleteFlag: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          nkTaskNo: [
            { required: true, message: '送餐编号，唯一标识不能为空', trigger: 'blur' }
          ],
          fkRiderId: [
            { required: true, message: '骑手表主键，唯一标识不能为空', trigger: 'blur' }
          ],
          fkRiderName: [
            { required: true, message: '骑手名称，可以重复不能为空', trigger: 'blur' }
          ],
          fkRiderNo: [
            { required: true, message: '骑手编号，唯一标识不能为空', trigger: 'blur' }
          ],
          fkRiderPhone: [
            { required: true, message: '骑手电话，唯一标识不能为空', trigger: 'blur' }
          ],
          fkOrderId: [
            { required: true, message: '订单表主键，唯一标识不能为空', trigger: 'blur' }
          ],
          fkOrderNo: [
            { required: true, message: '订单编号，唯一标识不能为空', trigger: 'blur' }
          ],
          fkDishId: [
            { required: true, message: '菜品表主键，唯一标识不能为空', trigger: 'blur' }
          ],
          fkDishNo: [
            { required: true, message: '菜品编号，唯一标识不能为空', trigger: 'blur' }
          ],
          fkDishName: [
            { required: true, message: '菜品名称不能为空', trigger: 'blur' }
          ],
          taskStatus: [
            { required: true, message: '送餐状态：0未配送，1配送中，2已送达不能为空', trigger: 'blur' }
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
        this.dataForm.pkTaskId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.pkTaskId) {
            this.$http({
              url: this.$http.adornUrl(`/ylservice/task/info/${this.dataForm.pkTaskId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.nkTaskNo = data.task.nkTaskNo
                this.dataForm.fkRiderId = data.task.fkRiderId
                this.dataForm.fkRiderName = data.task.fkRiderName
                this.dataForm.fkRiderNo = data.task.fkRiderNo
                this.dataForm.fkRiderPhone = data.task.fkRiderPhone
                this.dataForm.fkOrderId = data.task.fkOrderId
                this.dataForm.fkOrderNo = data.task.fkOrderNo
                this.dataForm.fkDishId = data.task.fkDishId
                this.dataForm.fkDishNo = data.task.fkDishNo
                this.dataForm.fkDishName = data.task.fkDishName
                this.dataForm.taskStatus = data.task.taskStatus
                this.dataForm.deleteFlag = data.task.deleteFlag
                this.dataForm.gmtCreate = data.task.gmtCreate
                this.dataForm.gmtModified = data.task.gmtModified
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
              url: this.$http.adornUrl(`/ylservice/task/${!this.dataForm.pkTaskId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'pkTaskId': this.dataForm.pkTaskId || undefined,
                'nkTaskNo': this.dataForm.nkTaskNo,
                'fkRiderId': this.dataForm.fkRiderId,
                'fkRiderName': this.dataForm.fkRiderName,
                'fkRiderNo': this.dataForm.fkRiderNo,
                'fkRiderPhone': this.dataForm.fkRiderPhone,
                'fkOrderId': this.dataForm.fkOrderId,
                'fkOrderNo': this.dataForm.fkOrderNo,
                'fkDishId': this.dataForm.fkDishId,
                'fkDishNo': this.dataForm.fkDishNo,
                'fkDishName': this.dataForm.fkDishName,
                'taskStatus': this.dataForm.taskStatus,
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
