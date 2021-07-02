<template>
  <div class="Page Confirm">
    <div class="Title">
      <h1 class="fl f18">订单确认</h1>
      <div class="clear"></div>
    </div>
    <form name="flowForm" id="flowForm" method="post" action="">
      <table class="GoodList">
        <tbody>
        <tr>
          <th class="priceNew">订单编号</th>
          <th class="priceNew">交易状态</th>
          <th class="priceNew">交易方式</th>
          <th class="priceNew">送货地址</th>
          <th class="priceNew">订单金额</th>
          <th class="priceNew">下单时间</th>
          <th class="priceNew">操作</th>
        </tr>
        </tbody>
        <tbody>
        <tr class="good" v-for="item in list" :key="item.indentId" ref="indent">
          <!-- <tr class="good"> -->
          <td class=" priceNew Last" text-algin="center">
            <!-- <a target="_blank" :href="'https://localhost:3000/course/'+order.courseId"> -->
            <a target="_blank" href="#">
              {{item.indentId}}</a>
          </td>
          <td class=" priceNew Last" v-if="item.isPayed == 0">
            待支付
          </td>
          <td class=" priceNew Last" v-if="item.isPayed == 1">
            已下单
          </td>
          <td class=" priceNew Last" v-if="item.isPayed == 2">
            配送中
          </td>
          <td class=" priceNew Last" v-if="item.isPayed == 3">
            已完成
          </td>
          <td class=" priceNew Last">
            <span v-if="item.payStatus == 2"><strong>无</strong></span>
            <span v-if="item.payStatus == 0">账户支付</span>
            <span v-if="item.payStatus == 1">支付宝</span>
          </td>

          <td class=" priceNew Last" v-if="item.isPayed == 0">
            <!-- 交易地址 -->
            <p><strong>无</strong></p>
          </td>
          <td class=" priceNew Last" v-if="item.isPayed != 0">
            <!-- 送货地址 -->
            <p><strong>{{item.inUserAddress}}</strong></p>
          </td>
          <!-- 订单金额 -->
          <td class=" priceNew Last">￥ {{item.total}}<strong></strong></td>
          <!-- 下单时间 -->
          <td class=" priceNew Last">{{item.gmtCreate}}<strong></strong></td>
          <td class=" priceNew Last" v-if="item.isPayed == 0">
            <strong>
              <el-button type="primary" size="mini" icon="el-icon-end" @click="jumpToDetail(item.indentId)">&nbsp;&nbsp;&nbsp;&nbsp;支付订单&nbsp;&nbsp;&nbsp;&nbsp;</el-button>
            </strong>
          </td>
          <td class=" priceNew Last" v-if="item.isPayed != 0">
            <strong>
              <el-button type="primary" size="mini" icon="el-icon-end" @click="jumpToDetail(item.indentId)">查看订单详情
              </el-button>
            </strong>
          </td>
        </tr>
        </tbody>
      </table>
    </form>
  </div>
</template>
<script>
  import indentApi from '@/api/indent'

  export default {
    data() {
      return {
        user_id: '1378914156526211074',
        size: '', //订单总商品数量
        list: {
          indentId: '',
          indentWay: '',
          inUserAddress: '',
          isPayed: '',
          total: '',
          gmtCreate: ''
        }
      }
    },
    created() {
      this.getAllIndentOfOne(this.user_id)
    },
    methods: {
      jumpToDetail(id) {
        this.$router.push({path: '/indent/' + id})
      },
      getAllIndentOfOne(user_Id) {
        console.log(user_Id)
        indentApi.selectIndentByUserId(user_Id)
          .then(response => {
            console.log(this.list)
            this.list = response.data.list
          })
      }
    }
  }
</script>
