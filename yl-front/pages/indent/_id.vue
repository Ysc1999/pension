<template>
  <div class="Page Confirm">
    <div class="Title">
      <h1 class="fl f18">订单确认</h1>
      <div class="clear"></div>
    </div>
    <form name="flowForm" id="flowForm" method="post" action="">
      <table class="GoodList" style="border:1px">
        <tbody>
        <tr>
          <th class="priceNew">菜品名</th>
          <th class="priceNew">菜品图像</th>
          <th class="priceNew">菜品数量</th>
          <th class="priceNew">单价</th>
          <th class="priceNew">商品总金额</th>
        </tr>
        </tbody>
        <tbody>
        <tr class="good" v-for="item in items" :key="item.indentDetailId">
          <!-- <tr class="good"> -->
          <td class="red priceNew Last" text-algin="center">
            <!-- <a target="_blank" :href="'https://localhost:3000/course/'+order.courseId"> -->
            <a target="_blank" href="#">
              {{item.dishName}}</a>
          </td>

          <!-- <input type="hidden" class="ids ids_14502" value="14502">
          <a target="_blank" :href="'https://localhost:3000/course/'+ order.courseId">{{order.courseTitle}}</a> -->
          <td class="red priceNew Last">
            <img :src="item.dishPicture" width="150px" height="75px"/>
          </td>

          <td class="red priceNew Last">
            <!-- 购买的商品数量 -->
            <p><strong>{{item.num}}</strong></p>
          </td>
          <!-- 商品单价 -->
          <td class="red priceNew Last">￥<strong>{{item.dishPrice}}</strong></td>
          <!-- 总金额 -->
          <td class="red priceNew Last">￥<strong>{{item.num * item.dishPrice}}</strong></td>
        </tr>
        </tbody>
      </table>
      <div class="Finish" v-if="indent.isPayed == 0">
        <div style="text-align:right">
          <el-radio v-model="indent.payStatus" :label="1">
            <img src="~/assets/photo/payWay/account.jpg" alt="账户支付">
          </el-radio>
          <el-radio v-model="indent.payStatus" :label="3">
            <img src="~/assets/photo/payWay/zhifubao.jpg" alt="支付宝支付">
          </el-radio>
        </div>
        <div style="float: right;font-size:16px;" id="AgreeDiv">
          <label for="Agree"><p class="on"><input type="checkbox" :checked="isChecked" @click="check">我已阅读并同意<a
            href="javascript:" target="_blank">《养老餐厅平台购买协议》</a></p></label>
        </div>
        <div class="clear"></div>
        <div class="Main fl">
          <div class="fl">
            <!-- <a :href="'/course/'+order.courseId">返回课程详情页</a> -->
            <el-button type="danger" icon="el-icon-delete" id="submitPay" @click="cancelIndent">取消订单</el-button>
          </div>
          <div class="fr">
            <p>共 <strong class="red">{{total}}</strong> 件商品，合计<span class="red f20">￥<strong
              id="AllPrice">{{indent.total}}</strong></span></p>
          </div>
        </div>
        <input name="score" value="0" type="hidden" id="usedScore">
        <button class="fr redb" type="button" id="submitPay" @click="paid">去支付</button>
        <div class="clear"></div>
      </div>
    </form>
  </div>
</template>
<script>
  import indentApi from '@/api/indent'

  export default {
    data() {
      return {
        isChecked: false,
        total: '', //订单详情总条数
        items: {
          dishName: '',
          dishName: '',
          num: '',
          dishPrice: ''
        },
        indent: {
          indentId: '', //订单id
          total: '', //订单总金额,
          payStatus: '', //支付方式
          isPayed: ''
        },
        indentId: ''
      }
    },
    created() {
      if (this.$route.params && this.$route.params.id) {
        this.indentId = this.$route.params.id
        this.asyncData(this.indentId)
      }
    },
    methods: {
      //修改复选框状态
      check() {
        this.isChecked = !this.isChecked
      },
      cancelIndent() { //取消订单 回退商品数量到商品表
        this.$confirm('将取消此订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() =>{
          indentApi.cancelIndent(this.indentId)
            .then(response => {
              this.$message.success('成功取消订单')
              this.$router.push({path: '/indent/'})
            })
        })
      },
      //支付方法
      paid() {
        //没有选择支付方式
        if (this.indent.payStatus == "") {
          this.$message.error('亲~ ,您还没有选择支付方式呢')
          return false
        }
        //同意购买协议
        if (!this.isChecked) {
          this.$message.error('亲~ ,请同意下购买协议')
          return false
        }
        if (this.indent.payStatus == 1) {
          this.$confirm('将使用账户余额购买, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            indentApi.payByAccount(this.indentId,this.indent.total).then(response =>{
              //500说明支付出问题
              if(response.data.code == 500){
                this.$message({
                  type:"error",
                  message:response.data.msg
                })
                return ;
              }else{
                this.$message({
                  type:"success",
                  message:"支付成功!"
                })
                this.$router.push({path: '/indent/'})
              }
            })
          })
        }
        //更改订单状态
        if (this.indent.payStatus == 3) {
          indentApi.pay(this.indent)
            .then(response => {
              document.write(response.data)
              //this.$message.success('支付成功')
            })
          //this.$router.push({path : '/indent/'})
        }
      },
      //获取id值
      asyncData(id) {
        //直接从地址栏中拿数据 本页面是oId.vue 所以params.oId
        return indentApi.getOneIndentForPaid(id)
          .then(response => {
              this.items = response.data.list, //初始化订单详情
              this.total = response.data.total,
              this.indent = response.data.indent
          })
      }
    }
  }
</script>
