package com.micropos.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
//@EnableRedisHttpSession
public class CartApplication {
    public static void main(String[] args) {
        //SpringApplication.run(CartApplication.class, args);
        new SpringApplicationBuilder(CartApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run();

    }
}
