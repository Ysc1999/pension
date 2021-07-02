import request from '@/utils/httpRequest'


export default {

  /****************************************** category begin*********************************************/
  //获取三级菜单目录
  selectMenuTreeList() {
    return request({
      url: "/product/category/tree/list",
      method: 'get'
    })
  },

  //删除菜单目录
  removeMenuByIds(catIds) {
    return request({
      url: '/product/category/delete',
      method: 'post',
      data: catIds
    })
  },

  //新增菜单目录
  saveMenu(category) {
    return request({
      url: '/product/category/save',
      method: 'post',
      data: category
    })
  },

  //更新菜单目录
  editMenu(category){w
    return request({
      url: '/product/category/update',
      method: 'post',
      data: category
    })
  },

  //查询单个信息
  searchInfo(catId){
    return request({
      url: `/product/category/info/${catId}`,
      method:'get'
    })
  },

  /****************************************** category over*********************************************/

  /****************************************** brand begin *********************************************/
  updateBrand(brand){
    return request({
      url:'/product/brand/update',
      method:'post',
      data:brand
    })
  },
  ossGetPolicy(){
    return request({
      url:'thirdparty/oss/policy',
      method:'get'
    })
  }
}
