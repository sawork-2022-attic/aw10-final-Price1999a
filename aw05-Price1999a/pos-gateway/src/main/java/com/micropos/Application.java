package com.micropos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;

@SpringBootApplication
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.REACTIVE)
                .run();
    }

    @Bean
    public DirectChannel sampleChannel() {
        return new DirectChannel();
    }
}
