package com.graduation.ylservice;

import com.alibaba.nacos.client.config.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.ylservice.dao.*;
import com.graduation.ylservice.entity.*;
import com.graduation.ylservice.entity.query.IndentQuery;
import com.graduation.ylservice.service.AccountService;
import com.graduation.ylservice.service.IndentService;
import com.graduation.ylservice.service.SaleStatusService;
import com.graduation.ylservice.service.UserService;
import com.graduation.ylservice.utils.PageUtils;
import com.graduation.ylservice.utils.Query;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class YlServiceApplicationTests {

    @Autowired
    AccountService accountService;

    @Autowired
    SaleStatusService saleStatusService;

    @Autowired
    SaleStatusDao saleStatusDao;

    @Autowired
    DishDao dishDao;

    @Autowired
    IndentDetailDao indentDetailDao;

    @Autowired
    IndentDao indentDao;

    @Autowired
    IndentService indentService;

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Test
    void testSaleStatus() {
        Map<String, Object> params = new HashMap<>();
        params.put("params", null);
        PageUtils page = saleStatusService.queryPage(params);
        System.out.println(page.getTotalCount());
        List<SaleStatusEntity> list = (List<SaleStatusEntity>) page.getList();
        /*for(SaleStatusEntity d :list){
            System.out.println(d);
        }*/
    }

    @Test
    void testSaleStatusDao() {
        Map<String, Object> params = new HashMap<>();
        params.put("PAGE", 1);
        params.put("LIMIT", 5);
        IPage<SaleStatusEntity> saleStatusEntityIPage = saleStatusDao.selectPage(new Query<SaleStatusEntity>().getPage(params), null);
        System.out.println(saleStatusEntityIPage.getTotal());
    }

    @Test
    void contextLoads() {

    }

    /**
     * 爬取指定网站的数据 用于生成菜单数据
     *
     * @throws Exception
     */
    @Test
    public void dishInsert() throws Exception {
        //获取请求
        String path = "http://www.dianping.com/shop/l3LzuTarwwN7PXid/photos/tag-%E8%8F%9C?pg=6";
        URL url = new URL(path);
        URLConnection connection = url.openConnection();
        //设置登录时的cookie
        String cookie = "_lxsdk_cuid=177fdb1eff2c8-0e94db75df4998-53e3566-144000-177fdb1eff2c8; _lxsdk=177fdb1eff2c8-0e94db75df4998-53e3566-144000-177fdb1eff2c8; _hc.v=c968c8b5-9181-5174-0f8f-c53a0782c3ee.1614869034; s_ViewType=10; aburl=1; cy=10; cye=tianjin; Hm_lvt_602b80cf8079ae6591966cc70a3940e7=1617614557; Hm_lpvt_602b80cf8079ae6591966cc70a3940e7=1617614557; _lxsdk_s=178a157492f-64c-ae4-128||4";
        connection.setRequestProperty("Cookie", cookie);
        //解析网页
        Document document = Jsoup.parse(url, 30000);
        //返回的是Elements(ArrayList)，选择list中的第一个返回父类ELement
        //直接返回li标签的
        Elements entry = document.getElementsByTag("li");
        //System.out.println(document);
        //遍历返回的元素
        for (Element e : entry) {
            //System.out.println(e.html());
            String name = e.getElementsByTag("a").eq(1).attr("title");
            //System.out.println(name);
            //查看数据库是否存在该菜品
            DishEntity dishEntity = dishDao.selectOne(new QueryWrapper<DishEntity>().eq("dish_name", name));
            //System.out.println(title);
            if (dishEntity == null && name != null && name != "") {
                String picturePath = e.getElementsByTag("img").eq(0).attr("src");
                //根据菜品名称 + 菜品照片确定新增的数据
                if (picturePath != null && picturePath != "") {
                    String descript = e.getElementsByTag("img").eq(0).attr("alt").replaceAll("\\(.*?\\)", "").trim();
                    //会丢失精读
                    Float price = Float.parseFloat(new Double(Math.random() * 100).toString());
                    DishEntity dish = new DishEntity();
                    dish.setDishKindName("1378987462465998850");
                    dish.setDishName(name);
                    dish.setDishPicture(picturePath);
                    dish.setDishPrice(price);
                    dish.setDescription(descript);
                    //System.out.println(dish);
                    dishDao.insert(dish);
                }
            }
        }
    }

    /**
     * 以订单内数据为基准 向订单子表填充数据
     */
    @Test
    public void indentDetailBatchInsert() {
        //1.抽取所有菜品id 便于以后随机选取
        List<DishEntity> dishEntities = dishDao.selectList(null);
        //创建数组 存放菜品id 用于后面随机选择
        String[] dishIds = new String[dishEntities.size()];
        Map<String, Float> dishMap = new HashMap<>();
        int num = 0;
        for (DishEntity dishEntity : dishEntities) {
            dishMap.put(dishEntity.getDishId(), dishEntity.getDishPrice());
            dishIds[num] = dishEntity.getDishId();
            num++;
        }
        //2.从订单抽取订单号和每条订单所包含的菜品数目
        List<IndentEntity> list = indentDao.selectList(null);
        /**
         *  3.每一次for循环 就处理一个订单对象
         *    for循环里面嵌套的while循环 每一次生成一个订单子表对象存入indent_detail表
         */
        for (IndentEntity indentEntity : list) {
            //拿取每个订单的总菜品数
            int size = indentEntity.getDishTotal();
            //计算每条子表金额 生成最后存放到订单的总金额
            float indenTotal = 0f;
            //开始遍历生成子表
            while (size > 0) {
                //创建的订单子表 每一个循环就创建一个新的对象存储到数据表中
                IndentDetailEntity detailEntity = new IndentDetailEntity();
                detailEntity.setDeIndentId(indentEntity.getIndentId());
                //随机数*订单的菜品数 这样生成的随机数一定大于0且小于菜品数
                int getNum = (int) Math.round(Math.random() * size);
                getNum = getNum == 0 ? 1 : getNum;
                //随机选择菜品
                String dishId = dishIds[(int) Math.round(Math.random() * (dishIds.length - 1))];
                detailEntity.setDeDishId(dishId);
                detailEntity.setNum(getNum);
                /*
                 * 4.将最后的子表对象存入indent_detail表中
                 */
                indentDetailDao.insert(detailEntity);
                indenTotal += getNum * dishMap.get(dishId);
                size -= getNum;
            }
            /*
             * 5.更新订单表的总金额
             */
            indentEntity.setTotal(indenTotal);
            System.out.println(indentEntity);
            indentDao.updateById(indentEntity);
        }

    }

    /**
     * 以订单内数据为基准 向订单子表填充数据
     * 优化：去订单子表重复添加
     */
    @Test
    public void indentDetailBatchInsertOptimization() {
        //1.抽取所有菜品id 便于以后随机选取
        List<DishEntity> dishEntities = dishDao.selectList(null);
        //创建数组 存放菜品id 用于后面随机选择
        List<String> dishIds = new ArrayList<>();
        Map<String, Float> dishMap = new HashMap<>();
        int num = 0;

        for (DishEntity dishEntity : dishEntities) {
            dishMap.put(dishEntity.getDishId(), dishEntity.getDishPrice());
            dishIds.add(dishEntity.getDishId());
        }
        //2.从订单抽取订单号和每条订单所包含的菜品数目
        List<IndentEntity> list = indentDao.selectList(null);
        /**
         *  3.每一次for循环 就处理一个订单对象
         *    for循环里面嵌套的while循环 每一次生成一个订单子表对象存入indent_detail表
         */
        for (IndentEntity indentEntity : list) {
            //拿取每个订单的总菜品数
            int size = indentEntity.getDishTotal();
            //计算每条子表金额 生成最后存放到订单的总金额
            float indenTotal = 0f;
            List<String> temp = new ArrayList<>();
            for (String tmp : dishIds) {
                temp.add(tmp);
            }
            //开始遍历生成子表
            while (size > 0) {
                //创建的订单子表 每一个循环就创建一个新的对象存储到数据表中
                IndentDetailEntity detailEntity = new IndentDetailEntity();
                detailEntity.setDeIndentId(indentEntity.getIndentId());
                //随机数*订单的菜品数 这样生成的随机数一定大于0且小于菜品数
                int getNum = (int) Math.round(Math.random() * size);
                getNum = getNum == 0 ? 1 : getNum;
                //随机选择菜品
                int randomMath = (int) Math.round(Math.random() * (temp.size() - 1));
                String dishId = temp.get(randomMath);
                temp.remove(randomMath);

                detailEntity.setDeDishId(dishId);
                detailEntity.setNum(getNum);
                /*
                 * 4.将最后的子表对象存入indent_detail表中
                 */
                indentDetailDao.insert(detailEntity);
                indenTotal += getNum * dishMap.get(dishId);
                size -= getNum;
            }
            /*
             * 5.更新订单表的总金额
             */
            indentEntity.setTotal(indenTotal);
            System.out.println(indentEntity);
            indentDao.updateById(indentEntity);
        }

    }

    @Test
    void testIndent() {
        IndentQuery indentQuery = new IndentQuery();
        indentQuery.setPage("2");
        indentQuery.setLimit("2");
        PageUtils page = indentService.selectByParams(indentQuery);
    }

    /**
     * 根据用户id 在account中添加对应账户余额信息
     */
    @Test
    void userInsertTest() {
        List<UserEntity> userEntities = userDao.selectList(null);
        for (UserEntity user : userEntities) {
            AccountEntity account = new AccountEntity();
            account.setAcUserId(user.getUserId());
            float amout = Float.parseFloat(new Double(Math.random() * 10000).toString());
            account.setAmount(amout);
            accountDao.insert(account);
        }
    }

    /**
     * MD5加密
     */
    @Test
    void MD5() {
        String testPass = "123456";
        MD5 md5 = MD5.getInstance();
        System.out.println(md5.getMD5String(testPass));
    }

    /**
     * 测试用户登录
     */
    @Test
    void checkLogin() {
        UserEntity entity = new UserEntity();
        HttpServletRequest httpServletRequest = null;
        entity.setUserName("杨司成");
        entity.setUserPassword("123456");
        System.out.println(userService.checkLogin(entity, httpServletRequest));
    }

    @Test
    void testTime() {
        IndentEntity indent = new IndentEntity();
        indent.setIndentId("1379251082366095367");
        indent.setPayTime(new Date());
        indentService.updateById(indent);
    }

    @Test
    void testStr() {
        String str = "111-3,222-4,555-2";
        String[] couple = str.split(",");
        Map<String, Object> map = new HashMap<>();
        for (String tmp : couple) {
            String[] s = tmp.split("-");
            map.put(s[0], s[1]);
        }
        for (String s : map.keySet()) {
            System.out.println("key--> " + s + "value--> " + Integer.getInteger((String) map.get(s)));
        }
    }

    @Test
    void testIndentList(){
        IndentEntity indent = indentService.getById("1388127984531120130");
        System.out.println(indent);
        UserEntity user = userService.getById(indent.getInUserId());
        System.out.println(user);
        AccountEntity account = accountService.getOne(new QueryWrapper<AccountEntity>().eq("ac_user_id", user.getUserId()));
    }

    @Test
    void testThread() {
        Object o = new Object();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    for (int i = 0; i < 10; i += 2) {
                        System.out.println("A");
                        o.notify();
                        try{
                            o.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    for (int i = 1; i < 10; i += 2) {
                        System.out.println("B");
                        o.notify();
                        try {
                            o.wait();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        threadA.start();
        threadB.start();
        /*Object object = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 1; i < 10; i += 2) {
                        System.out.println(Thread.currentThread().getName() + "    " + i);
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    for (int i = 2; i < 10; i += 2) {
                        System.out.println(Thread.currentThread().getName() + "     " + i);
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();*/
    }
}
