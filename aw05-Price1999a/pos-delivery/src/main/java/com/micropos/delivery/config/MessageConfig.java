package com.micropos.delivery.config;

import com.micropos.delivery.model.Order;
import com.micropos.delivery.service.DeliveryService;
import com.micropos.delivery.utils.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessageConfig {
    @Bean
    public Consumer<Order> consumerOrder() {
        return order -> SpringContextUtil.getBean(DeliveryService.class).addOrder(order);
    }
}
