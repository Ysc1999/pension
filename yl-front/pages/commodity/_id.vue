<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 商品介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">商品介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- 商品基本信息 -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="commodity.commodityPicture" width="640px" height="357px">
            </section>

            <section class="mt20 t-infor-txt">
            <h3 class="hLh30">
              <span class="fsize24 c-333">商品名称:{{commodity.commodityName}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </h3>
              <h3 class="mt30 hLh30">
                <span class="fsize24 c-333" v-if="commodity.commodityPopuler=== 1" style="color:red">热销商品</span>
                <span class="fsize24 c-333" v-if="commodity.commodityPopuler=== 0" style="color:green">普通商品</span>
              </h3>
                <!-- {{commodity.commodityPopuler==1?'热销':'常规'}} -->
              <h3 class="mt20 hLh30">
                <span class="fsize24 c-333"> {{ commodity.commoditySex===1?'男用':'女用' }}&nbsp;&nbsp;&nbsp;&nbsp;</span>
              </h3>
              <h3 class="mt20 hLh30">
                <span class="mt20" v-if="commodity.commodityStock<10" style="color:red">剩余库存：{{commodity.commodityStock}}件</span>
                <span class="mt20" v-if="commodity.commodityStock>=10" style="color:blue">剩余库存：{{commodity.commodityStock}}件</span>
              </h3>
              <h3 class="mt20 hLh30">
                <span class="mt20">商品颜色： </span>
                <!-- 根据后台数据改变商品颜色 -->
                <div :style="{'background':commodity.commodityColor,'width':'20px', 'height':'20px','margin-left':'90px','margin-top':'-25px'}"></div>
              </h3>
              <h3 class="mt20 hLh30">
                <span class="mt20">商品价格：{{commodity.commodityPrice}}元</span>
              </h3>
              <h3 class="mt20 hLh30">
                <span class="mt20">商品程度：{{commodity.commodityWearlevel}}成新</span>
              </h3>
              <h3 class="mt20 hLh30">
                <span class="mt20" >商品描述：{{commodity.commodityDescript}}</span>
              </h3>
              <h3 class="mt30 hLh30">
               <el-button type="primary" round @click="addCart(commodity.commodityId,commodity.commodityPrice)" :disabled="disClick">添加到购物车</el-button>
              </h3>

            </section>

            <div class="clear"></div>
          </div>
        </section>

        <section class="mt50 fl t-infor-box c-desc-content">
         <div class="mt20 ">&nbsp;&nbsp;&nbsp;&nbsp;</div>

           <div class=" mt20 ds-post-main" v-for="commen in list" :key="commen.commentId">
	                <div class="ds-avatar">
		                  <img :src="(commen.user || {}).userAvatar" style="width:100%;height:100%; border-radius: 50%;">
                      </div>
                  <div class="ds-comment-body">{{(commen.user||{}).userNickname}}：{{commen.content}} <br> {{commen.gmtCreate}}</div>
            </div>
            <!-- <el-form-item label="发表评论">
                 <tinymce :height="300" v-model="list.content"/>
            </el-form-item> -->


         <section class="container ">

                 <el-input v-model="content" class="quill-editor" v-quill:myQuillEditor="editorOption"></el-input>

                <div >&nbsp;&nbsp;&nbsp;&nbsp;</div>
                <el-button  type="primary" @click="addCom(commodity.commodityId,content)">提交评论</el-button>
          </section>


          <div class="clear"></div>
        </section>



      </div>
    </section>
  </div>
</template>
<script>

import commodity from '@/api/commodity'
import cartApi from '@/api/cart'
import commentApi from '@/api/comment'

export default {
  // components: { Tinymce },
  data(){
    return{
      disClick:false,
      content:'',
      list:{
        commentId:'',
        editorOption: {}
      },
      commodity:{
      }
      }
    },
   created(){
      /*if(this.$route.params && this.$route.params.id){
        const id = this.$route.params.id
        this.getGoods(id)
        this.getCom(id)
      }*/
   },
   methods:{
      removeTAG(str){
        return str.replace(/<[^>]+>/g, "");
      },
      getGoods(id){
        commodity.getOneCommodity(id)
        .then(response => {
          this.commodity= response.data.data.commodity
          this.commodity.commodityDescript =  this.removeTAG(this.commodity.commodityDescript)
          if(this.commodity.commodityStock <= 0){
            this.disClick = true //禁用按钮
          }
        })
      },

      addCart(goodsId,goodsPrice){
          cartApi.addCart(goodsId,goodsPrice)
            .then(response =>{
              if(response.data.code == 20001){
                this.$message.error(response.data.message)
                return false
              }
              this.$message.success("成功添加到购物车")
            })
      },

      getCom(id){
      commentApi.selectByCom(id)
      .then(response => {

          for(var comment of response.data.data.comment){
           comment.content =  this.removeTAG(comment.content)
          }
          this.list = response.data.data.comment
      })
      },

      addCom(commodityId,content){

      commentApi.addComment(commodityId,content)
      .then(response =>{
        this.$message.success('成功添加评论')
            const id = this.$route.params.id
            this.getCom(id)
            this.content = ''
          })
      }
   }
};

</script>


<style lang="scss" scoped>
  .container {
    width: 1000px;
    margin: 0 auto;
    padding: 20px 0;
    .quill-editor {
      min-height: 200px;
      max-height: 400px;
      // overflow-y: auto;
    }
  }
</style>
