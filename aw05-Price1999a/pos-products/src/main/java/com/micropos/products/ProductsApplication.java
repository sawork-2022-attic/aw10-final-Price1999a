package com.micropos.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class ProductsApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ProductsApplication.class, args);
        new SpringApplicationBuilder(ProductsApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run();

    }
}