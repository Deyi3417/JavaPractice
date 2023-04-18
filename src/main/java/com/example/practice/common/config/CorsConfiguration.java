package com.example.practice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域处理
 *
 * @author HP
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 来源
                .allowedOrigins("http://localhost:5173")
                // HTTP 方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 请求头
                .allowedHeaders("*")
                // 启用 cookie
                .allowCredentials(true)
                // 缓存时间
                .maxAge(3600);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        // 允许所有来源
//                        .allowedOrigins("http://localhost:5173")
//                        // 所有 HTTP 方法
//                        .allowedMethods("*")
//                        // 所有请求头
//                        .allowedHeaders("*")
//                        // 启用 cookie
//                        .allowCredentials(true)
//                        // 缓存时间
//                        .maxAge(3600);
//            }
//        };
//    }
}
