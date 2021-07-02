<template>
  <el-dialog
    title="订单菜品"
    :close-on-click-modal="false"
    width="1100px"
    :visible.sync="visible">
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">
      <el-table-column
        prop="dishId"
        header-align="center"
        align="center"
        label="菜品ID">
      </el-table-column>
      <el-table-column
        prop="dishName"
        header-align="center"
        align="center"
        label="菜品名称">
      </el-table-column>
      <el-table-column
        prop="num"
        header-align="center"
        align="center"
        label="单品数目">
        <!--<template v-if="numVisable" slot-scope="scope">
          <el-input-number v-model="scope.row.num" :min="1" :max="99" label="描述文字"></el-input-number>
        </template>-->
      </el-table-column>
      <el-table-column
        prop="dishPrice"
        header-align="center"
        align="center"
        label="菜品单价">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="菜品图片">
        <template slot-scope="scope">
          <img :src="scope.row.dishPicture" alt="" width="80px" height="80px">
        </template>
      </el-table-column>
      <el-table-column
        v-if="this.isPayed === 1"
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="changeNum(scope.row.indentId,scope.row.dishId)" hidden>修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.indentId,scope.row.dishId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="visible = false">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import indentApi from '@/api/indent'

  export default {
    data() {
      return {
        isPayed:'',  //如果是1 属于已下单未派送 可以修改菜品数量 若已派送或已完成 则不能修改
        numVisable:false,
        visible: false,
        dataList: [],
        dataListLoading: false,
      }
    },
    methods: {
      changeNum(){
        this.numVisable = !this.numVisable
      },
      init(id,isPayed) {
        this.isPayed = isPayed
        this.visible = true
        this.$nextTick(() => {
          /*this.$refs['dataForm'].resetFields()*/
          indentApi.selectIndentDetailById(id)
            .then(response => {
              this.dataList = response.data.list
            })
        })
      },
      deleteHandle(indentId, dishId) {
        this.$confirm('点击之后会删除该菜品，确定删除？', '提醒', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //1.删除订单该菜品
          indentApi.deleteIndentDetailDish(indentId, dishId)
            .then(response => {
              this.$message({
                type: 'success',
                message: '成功删除'
              })
              //2.刷新当前模态框
              this.init(indentId)
            })
        })
      },
      dataFormSubmit() {
        this.visible = false
        this.$emit('refreshDataList')  //调用父组件的自定义方法
      }
    }
  }
</script>
