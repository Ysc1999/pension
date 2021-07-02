<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.inUserName" placeholder="下单人" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <template>
          <div class="block">
            <el-date-picker
              v-model="dataForm.gmtCreate"
              type="datetimerange"
              @change="showTime"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="截止时间"
              align="right">
            </el-date-picker>
          </div>
        </template>
      </el-form-item>
      <el-form-item>
        <el-button @click="selectIndentByParams()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        header-align="center"
        align="center"
        width="100px"
        type="index"
        label="序号">
      </el-table-column>
      <el-table-column
        prop="inUserName"
        header-align="center"
        align="center"
        label="收货人名称">
      </el-table-column>
      <el-table-column
        prop="dishTotal"
        header-align="center"
        align="center"
        label="菜品总数(件)">
      </el-table-column>
      <el-table-column
        prop="total"
        header-align="center"
        align="center"
        label="总金额(元)">
      </el-table-column>
      <el-table-column
        prop="payTime"
        header-align="center"
        align="center"
        label="下单时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="160"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="selectIndentDetail(scope.row.indentId)">订单菜品</el-button>
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.indentId)">订单详情</el-button>
          <el-button type="text" size="small" v-if="isPayed != 2" @click="deleteHandle(scope.row.indentId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <!-- 订单详情 -->
    <select-detail v-if="detailVisible" ref="selectDetail" @refreshDataList="getDataList"></select-detail>
  </div>
</template>

<script>
  import AddOrUpdate from './indent-add-or-update'
  import SelectDetail from './indentdetail-add-or-update'
  import indentApi from "@/api/indent"
  import moment from 'moment'

  export default {
    data() {
      return {
        dataForm: {
          inUserName: '',
          gmtCreate: '',
          indentStatus: 2
        },
        isPayed:2,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        detailVisible: false
      }
    },
    components: {
      AddOrUpdate,
      SelectDetail
    },
    activated() {
      this.getDataList()
    },
    /*watch: {
      detailVisible: {
        handler: function (oldVal, newVal) {
          if (newVal) {
            console.log("status-->", oldVal, newVal)
            this.getDataList();
          }
        }
      }
    },*/
    methods: {
      //根据下单人和下单时间确定订单信息
      selectIndentByParams() {
        indentApi.selectByParams(
          this.pageIndex,
          this.pageSize,
          this.dataForm.inUserName,
          //填充数据之后 再删除 gmtGrant的值会变成null 而不是""
          //moment将时间选择器的值格式化 否则会传递2020-04-12T16:00:00.000Z这样格式的数据
          this.dataForm.gmtCreate == "" || this.dataForm.gmtCreate == null ? null : moment(this.dataForm.gmtCreate[0]).format("YYYY-MM-DD HH:mm:ss"),
          this.dataForm.gmtCreate == "" || this.dataForm.gmtCreate == null ? null : moment(this.dataForm.gmtCreate[1]).format("YYYY-MM-DD HH:mm:ss"),
          this.dataForm.indentStatus)
          .then(response => {
            this.dataList = response.data.page.list;
            this.totalPage = response.data.page.totalCount
          })
      },
      //开启订单详情模态框
      selectIndentDetail(id) {
        this.detailVisible = true
        this.$nextTick(() => {
          this.$refs.selectDetail.init(id,this.isPayed)
        })
      },
      showTime() {
        console.log(this.dataForm.time)
      },
      // 获取数据列表
      getDataList() {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/ylservice/indent/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key,
            'isPayed': this.isPayed
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle(val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle(val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle(id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle(id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.indentId
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/ylservice/indent/deleteBackAccount'),
            method: 'delete',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
