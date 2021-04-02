package com.graduation.ylservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient  //开启nacos服务
@SpringBootApplication
@MapperScan("com.graduation.ylservice.dao")
public class YlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlServiceApplication.class, args);
    }

}
