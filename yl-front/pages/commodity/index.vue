<template>
  <!-- <div class="app-container"> -->
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部商品</span>
        </h2>
      </header>

      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <el-form :inline="true" class="demo-form-inline">
                    <el-form-item label="商品类别">
                      <el-select
                        v-model="kindBase.kindName"
                        placeholder="请选择"
                      >
                        <el-option
                          v-for="kindBase in kindBaseList"
                          :key="kindBase.kindName"
                          :label="kindBase.kindName"
                          :value="kindBase.kindName"
                        />
                      </el-select>
                      <el-button
                        type="primary"
                        icon="el-icon-search"
                        @click="getCommodityByKindFrontList()"
                        >查询</el-button
                      >
                      <el-button type="default" @click="resetData(1)"
                        >清空</el-button
                      >
                    </el-form-item>
                    <el-form-item label="查询商品">
                      <el-input
                        v-model="list.commodityName"
                        placeholder="商品名称"
                      />
                    </el-form-item>
                    <el-button
                      type="primary"
                      icon="el-icon-search"
                      @click="getCommodityByNameFrontList()"
                      >查询
                    </el-button>
                    <el-button type="default" @click="resetData(2)"
                      >清空</el-button
                    >
                  </el-form>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>

        <div class="mt40">
          <section class="no-data-wrap" v-if="total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
            >
          </section>
          <article class="comm-course-list">
            <ul>
              <li v-for="commodity in commodity" :key="commodity.commodityId">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img
                      :src="commodity.commodityPicture"
                      class="img-responsive"
                      :alt="commodity.commodityName"
                    />
                    <div class="cc-mask">
                      <a
                        :href="'/commodity/' + commodity.commodityId"
                        title="查看详情"
                        class="comm-btn c-btn-1"
                        >查看详情</a
                      >
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a
                      :href="'/commodity/' + commodity.commodityId"
                      :title="commodity.commodityName"
                      class="course-title fsize18 c-333"
                      >{{ commodity.commodityName }}</a
                    >
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA"
                        >{{ commodity.commodityPrice }}元</i
                      >
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA"
                        >{{ commodity.commodityWearlevel }}成新</i
                      >
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <div>
          <el-pagination
            :current-page="page"
            :page-size="limit"
            :total="total"
            style="padding: 30px 0; text-align: center"
            layout="total, prev, pager, next, jumper"
            @current-change="getList"
          />
        </div>
      </section>
    </section>
  </div>
  <!-- </div> -->
</template>
<script>
import commodity from "@/api/commodity";
export default {
  // layout: "sign",

  data() {
    return {
      // list : null,
      list: {
        commodityName: "",
      },
      commodity: {
        commodityName: "",
      },
      page: 1,
      limit: 4,
      total: "",

      kindBaseList: [],
      kindBase: {
        kindName: "",
      },
      loginInfo: {},
    };
  },
  created() {
    this.getAllKindBase();
    this.getList();
  },
  methods: {
    getAllKindBase() {
      commodity.getAllKindBase().then((response) => {
        this.kindBaseList = response.data.data.list;
        // console.log(this.kindBaseList);
      });
    },
    getList(page = 1) {
      this.page = page;
      commodity
        .getCommodityOnShelvesFrontList(this.page, this.limit)
        .then((response) => {
          this.commodity = response.data.data.items;
          this.total = response.data.data.total;
          // console.log(this.list);
          // console.log(this.total);
        });
    },
    resetData(flag) {
            if(flag == 2){
              this.list.commodityName = ""
              this.getList()
            }
            if(flag == 1){
              this.kindBase.kindName = ""
              this.getList()
            }
        },
    getCommodityByKindFrontList(page = 1) {
      console.log(this.kindBase.kindName);
      this.page = page;
      commodity
        .getCommodityByKindFrontList(this.page, this.limit, this.kindBase)
        .then((response) => {
          this.commodity = response.data.data.items;
          this.total = response.data.data.total;
          // console.log(this.commodity);
          // console.log(this.total);
        });
    },
    getCommodityByNameFrontList(page = 1) {
      console.log(this.list);
      this.page = page;
      commodity
        .getCommodityByNameFrontList(this.page, this.limit, this.list)
        .then((response) => {
          this.commodity = response.data.data.items;
          this.total = response.data.data.total;
          console.log(this.commodity);
          console.log(this.total);
        });
    },
  },
};
</script>