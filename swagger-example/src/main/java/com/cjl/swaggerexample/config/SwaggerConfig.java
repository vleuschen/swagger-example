package com.cjl.swaggerexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
//必须存在，扫描的API Controller包
@ComponentScan(basePackages = {"com.cjl.swaggerexample.controller"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){

        Contact contact=new Contact("陈锦磊","http://www.baidu.com","1071309217@qq.com");

        return new ApiInfoBuilder()
                .title("项目API接口")
                .description("API接口测试")
                .contact(contact)
                .version("1.0.1")
                .build();


    }

}
