# pension
智慧养老服务

# 系统功能简述
系统主要实现订单模块的开发。
# 
前台系统：使用支付宝沙箱服务，模拟真实订单提交的交易过程
#
后台系统：可根据参数实现简单查询、删除订单操作
# 系统架构及相关技术简述
系统使用springboot+vue实现前后端分离。
#
前台系统前端页面使用nuxt框架作为基本框架，在与后端接口数据的交互过程中，使用axios代替ajax，完成基本业务要求的同时，也能实现客户端防止CSRF恶意攻击。
#
后台系统前端页面使用开源的人人项目，此系统可以通过点击页面的方式，添加新菜单，便于开发。
#
后端使用springcloud搭建项目，开发使用springboot，mysql部署在linux环境(dev配置文件,test配置文件是windows的配置环境)中，利用nacos配置服务相关信息并实现配置文件的实时修改。

