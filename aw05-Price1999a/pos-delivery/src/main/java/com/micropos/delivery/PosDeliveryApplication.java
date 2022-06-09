package com.micropos.delivery;

import com.micropos.delivery.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PosDeliveryApplication {

    public static void main(String[] args) {
//        SpringApplication.run(PosDeliveryApplication.class, args);
        new SpringApplicationBuilder(PosDeliveryApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run();
    }

}
