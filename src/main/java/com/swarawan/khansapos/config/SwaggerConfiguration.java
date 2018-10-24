package com.swarawan.khansapos.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.swarawan.khansapos.controller"))
                .paths(PathSelectors.ant("api/v1/**"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Khansa POS",
                "Java Spring Boot",
                "v1",
                "none",
                new Contact("Rio Swarawan",
                        "http://swarawan.com",
                        "swarawan.rio@gmail.com"),
                "none", "none",
                new ArrayList<>());
    }
}