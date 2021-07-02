<template>

  <div>
    <!-- 幻灯片 开始 -->
<div v-swiper:mySwiper="swiperOption">
    <div class="swiper-wrapper">
        <!-- <div v-for="banner in bannerList" :key="banner.id" class="swiper-slide" style="background: #040B1B;">
            <a target="_blank" :href="banner.linkUrl">
                <img :src="banner.imageUrl" :alt="banner.title">
            </a>
        </div> -->
        <div class="swiper-slide" style="background: #040B1B;">
            <a target="_blank" href="/">
                <img src="~/assets/photo/banner/1.jpg" alt="首页banner">
            </a>
        </div>

        <div class="swiper-slide" style="background: #040B1B;">
            <a target="_blank" href="/">
                <img src="~/assets/photo/banner/2.jpg" alt="首页banner">
            </a>
        </div>

        <div class="swiper-slide" style="background: #040B1B;">
            <a target="_blank" href="/">
                <img src="~/assets/photo/banner/3.jpg" alt="首页banner">
            </a>
        </div>
    </div>
    <div class="swiper-pagination swiper-pagination-white"></div>
    <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
    <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
</div>
<!-- 幻灯片 结束 -->

     <div id="aCoursesList">
      <!-- 热门商品 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <section class="tac pt20">
                <a href="#" title="热门商品" class="comm-btn c-btn-2">热门商品</a>
              </section>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="hotCommodity in hotCommodityList" :key="hotCommodity.commodityId">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="hotCommodity.commodityPicture"
                        class="img-responsive"
                        :alt="hotCommodity.commodityName"
                        :width="200"
                        :height="100"
                      >
                      <div class="cc-mask">
                        <a :href="'/commodity/'+hotCommodity.commodityId" title="查看宝贝详情" class="comm-btn c-btn-1">查看宝贝详情</a>
                      </div>
                    </section>
                    <!-- <h3 class="hLh30 txtOf mt10">
                      <a href="#" title="听力口语" class="course-title fsize18 c-333">{{hotCommodity.title}}</a>
                    </h3> -->
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-green" v-if="Number(hotCommodity.commodityPrice) < 50">
                        <i class="c-fff fsize12 f-fA">低价出售</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA" >
                        <i class="c-999 f-fA">{{hotCommodity.commodityPrice}}元</i>
                        |
                        <i class="c-999 f-fA">{{hotCommodity.commodityWearlevel}}成新</i>
                      </span>
                    </section>
                  </div>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
          </div>
        </section>
      </div>
      <!-- /热门商品 结束 -->
      <!-- 上线商品 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
              <section class="tac pt20">
              <a href="#" title="上线商品" class="comm-btn c-btn-2">上线商品</a>
            </section>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="commodity in commonCommodityList" :key="commodity.commodityId">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :href="'/commodity/'+commodity.commodityId" :title="commodity.commodityName">
                        <img :alt="commodity.commodityName" :src="commodity.commodityPicture">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href="'/commodity/'+commodity.commodityId" :title="commodity.commodityName" class="fsize18 c-666">{{commodity.commodityName}}</a>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{commodity.commodityDescript}}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p
                        class="c-999 f-fA"
                      >库存:{{commodity.commodityStock}} | 价格：{{commodity.commodityPrice}} 元 | {{commodity.commodityWearlevel}}成新</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import bannerApi from '@/api/banner'

export default {
  data () {
    return {
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      },
      hotCommodityList:[],
      commonCommodityList:[]
    }
  },
  created(){
    //this.getBannerList()
  },
  methods: {

    /**
    * remove the tag of html
    * @param str
    * @param len
    */
    removeTAG(str){
      return str.replace(/<[^>]+>/g, "");
    },
    getBannerList(){
      bannerApi.getBannerList()
        .then(response =>{
          this.hotCommodityList = response.data.data.hotCommodity
          for(var commodity of response.data.data.commonCommodity){
           commodity.commodityDescript =  this.removeTAG(commodity.commodityDescript)
          }
          this.commonCommodityList = response.data.data.commonCommodity
        })
    }
  },
}
</script>
