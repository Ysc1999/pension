package com.graduation.ylgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //开启nacos服务
@SpringBootApplication
public class YlGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlGatewayApplication.class, args);
    }

}
