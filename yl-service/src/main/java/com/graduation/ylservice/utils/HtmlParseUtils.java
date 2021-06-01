package com.graduation.ylservice.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.ylservice.dao.DishDao;
import com.graduation.ylservice.entity.DishEntity;
import com.graduation.ylservice.service.DishService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static javax.management.Query.attr;

/**
 * 爬取数据
 */
public class HtmlParseUtils {

    @Autowired
    private DishDao dishDao;

    public static void main(String[] args) throws Exception {
        HtmlParseUtils htmlParseUtils = new HtmlParseUtils();
        htmlParseUtils.dishInsert();
    }

    public void dishInsert () throws Exception{
        //获取请求
        String path = "http://www.dianping.com/shop/l3LzuTarwwN7PXid/photos/tag-%E8%8F%9C?pg=5";
        URL url = new URL(path);
        URLConnection connection = url.openConnection();
        //设置登录时的cookie
        String cookie = "_lxsdk_cuid=177fdb1eff2c8-0e94db75df4998-53e3566-144000-177fdb1eff2c8; _lxsdk=177fdb1eff2c8-0e94db75df4998-53e3566-144000-177fdb1eff2c8; _hc.v=c968c8b5-9181-5174-0f8f-c53a0782c3ee.1614869034; s_ViewType=10; aburl=1; cy=10; cye=tianjin; Hm_lvt_602b80cf8079ae6591966cc70a3940e7=1617614557; Hm_lpvt_602b80cf8079ae6591966cc70a3940e7=1617614557; _lxsdk_s=178a157492f-64c-ae4-128||4";
        connection.setRequestProperty("Cookie",cookie);
        //解析网页
        Document document = Jsoup.parse(url, 30000);
        //返回的是Elements(ArrayList)，选择list中的第一个返回父类ELement
        //直接返回li标签的
        Elements entry = document.getElementsByTag("li");
        //System.out.println(document);
        //遍历返回的元素
        for(Element e : entry){
            //System.out.println(e.html());
            String name = e.getElementsByTag("a").eq(1).attr("title");
            //System.out.println(name);
            //查看数据库是否存在该菜品
            //DishEntity dishEntity = dishService.getOne(new QueryWrapper<DishEntity>().eq("dish_name",name));
            //System.out.println(title);
            if(name != null && name != ""){
                String picturePath = e.getElementsByTag("img").eq(0).attr("src");
                //根据菜品名称 + 菜品照片确定新增的数据
                if(picturePath != null && picturePath != ""){
                    String descript = e.getElementsByTag("img").eq(0).attr("alt").replaceAll("\\(.*?\\)","").trim();
                    Float price = Float.parseFloat(new Double(Math.random() * 100).toString());
                    DishEntity dish = new DishEntity();
                    dish.setDishId("1378987462465998850");
                    dish.setDishKindName("1378987462465998850");
                    dish.setDishName(name);
                    dish.setDishPicture(picturePath);
                    dish.setDishPrice(price);
                    dish.setDescription(descript);
                    System.out.println(dish);
                    dishDao.insert(dish);
                }
            }
        }
    }

}
