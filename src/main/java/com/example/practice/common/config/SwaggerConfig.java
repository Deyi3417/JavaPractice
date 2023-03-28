package com.example.practice.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 自定义接口文档swagger配置
 *
 * @author 刘德意
 * @Profile("prod") 注解 限制注解在什么环境中使用，开发环境dev 生产环境prod
 * @Profile 主要作用在于指定环境加载指定的对象到容器中
 * 线上环境不要把接口暴露出去，可以通过在SwaggerConfig配置文件开头加上@Profile({"dev","test"})限定配置文件仅在部分环境开启
 *
 * @date 2022/8/28
 */
@Configuration
@EnableSwagger2
//@Profile({"dev","test"}) // 使用注解(开发环境中此对象才会进行注入到容器中)
// @ConditionalOnProperty // 目的是针对不同的配置，注册不同的配置bean。
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-center")
                .enable(enable)
                .apiInfo(adminApiInfo())
                .select()
                .build();

    }

    /**
     * api信息
     *
     * @return
     */
    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("user-center API文档")
                .description("用户中心接口文档")
                .version("v1.0")
                .contact(new Contact("刘德意", "https://blog.csdn.net/weixin_43982687", "18811553417@163.com"))
                .build();
    }

}
