<template>
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">购物车详情</span>
        </h2>
      </header>
       <section class="c-sort-box unBr">
        <div >
          <section class="no-data-wrap" v-if="data.total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">购物车里空空如也，快去添加些东西吧</span>
          </section>
          <!-- /无数据提示 结束-->
          <article v-if="data.total>0" class="i-teacher-list">
            <ul class="of" ref="table">
              <li v-for="items in data.items" :key="items.goodsId">          
                <section class="i-teach-wrap" >
                  <input  type="checkbox" class="isChecked" value=items.goodsId/>
                  <div class=" i-teach-pic">
                    <a :href="'/commodity/'+items.goodsId" :title="items.commodity.commodityName" target="_blank">
                      <img :src="items.commodity.commodityPicture" :alt="items.commodity.commodityName" >
                    </a>
                  </div>
                  <div class="mt10 hLh10 txtOf ">
                    <a :href="'/commodity/'+items.goodsId" :title="items.commodity.commodityName" target="_blank" class="fsize18 c-666">{{items.commodity.commodityName}}</a>
                  </div>
                  <div class="mt10 hLh10 txtOf ">
                    商品价格:
                    <span class="fsize14 c-999">{{items.goodsPrice}}</span>
                  </div>
                  <div class="mt10 i-q-txt">
                    数量:
                   <el-input-number :min="1" :max="items.commodity.commodityStock" v-model="items.cartGoodsNumber" controls-position="right"/> 件
                  </div>
                  <div class="mt10 ">
                  <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(items.goodsId,items.cartId)">移除该商品</el-button>
                  </div>
                </section>
              </li>
                 <div class="btn1"><el-button type="primary" size="mini" icon="el-icon-end" @click="settle">结算订单</el-button></div>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <!-- 公共分页 开始 -->
      <div>
        <div class="paging">
          <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
          <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="首页"
            @click.prevent="gotoPage(1)">首页</a>

          <a
            :class="{undisable: !data.hasPrevious}"
            href="#"
            title="前一页"
            @click.prevent="gotoPage(data.current-1)">&lt;</a>

          <a
            v-for="page in data.pages"
            :key="page"
            :class="{current: data.current == page, undisable: data.current == page}"
            :title="'第'+page+'页'"
            href="#"
            @click.prevent="gotoPage(page)">{{ page }}</a>

          <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="后一页"
            @click.prevent="gotoPage(data.current+1)">&gt;</a>

          <a
            :class="{undisable: !data.hasNext}"
            href="#"
            title="末页"
            @click.prevent="gotoPage(data.pages)">末页</a>

          <div class="clear"/>
        </div>
      </div>
      <!-- 公共分页 结束 -->
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /讲师列表 结束 -->
  </div>
</template>

<script>
import cartApi from '@/api/cart'
import indentApi from '@/api/indent'


export default {
  
  data(){
    return {
      data:{
        total:'',
        items:{
          cartGoodsNumber:''
        }
      }
    }
  },
  //异步调用，调用一次
  //params: 相当于之前 this.$route.params.id  等价  params.id
    

  created(){
    this.get()
    
  },
  methods:{
    
     async settle(){
       
      let checkDom = this.$refs.table.getElementsByClassName("isChecked")
      var cartTotal=0
      let flag = true
      var indentId = 0
      for(var i = 0; i < checkDom.length; i++){
        if(checkDom[i].checked){
          if(flag){ //第一次添加需要返回一个indentId
           await indentApi.insertIndent()
              .then(response =>{
                if(response.data.code == 20001){
                this.$message.error(response.data.message)
                return false
              }
              else{
                indentId = response.data.data.indentId
                flag = false
              }
              })
              
          }
          cartApi.updateCartDetail(this.data.items[i],indentId)
          cartTotal = cartTotal + this.data.items[i].goodsPrice * this.data.items[i].cartGoodsNumber
          //删除购物车详情表中信息
          cartApi.removeGoods(this.data.items[i].goodsId,this.data.items[i].cartId)
        }
      }
      if(!flag){ //修改了订单信息
        //更新订单大表的总金额
        indentApi.updateCart(indentId,cartTotal)
        this.$message.success('成功添加到订单')
        this.$router.push({path:'/indent/'+indentId})
      }else{
        return 
      }
      
    },
    get(){
      cartApi.getCart()
      .then(response => {
          cartApi.pageCartCondition(1,5,response.data.data.cartId)
           .then(response => {
             this.data = response.data.data
          })
       })
    },
    removeDataById(goodsId,cartId) {
            this.$confirm('是否将该商品移出购物车', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
            .then(() => {
            
                cartApi.removeGoods(goodsId,cartId)
                    .then(response =>{
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    this.get()
                })
            }) 
            
        },
    gotoPage(page) {
      cartApi.getCart()
        .then(response => {
          if(page > this.data.pages){
            page = this.data.pages
          }
          cartApi.pageCartCondition(page,5,response.data.data.cartId)
            .then(response => {
              this.data = response.data.data
          })
      })
    }
  }

};
</script>