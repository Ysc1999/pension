package com.graduation.ylgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class YlCorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter(){
        //UrlBasedCorsConfigurationSource类实现CorsConfigurationSource 解决跨域问题
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configuration = new CorsConfiguration();

        //配置跨域
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");
        //开启cookie
        configuration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**",configuration);

        return new CorsWebFilter(source);
    }
}
