package com.micropos;

import com.micropos.gateway.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.webflux.dsl.WebFlux;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class HttpInBoundGateway {
    @Bean
    public IntegrationFlow inGate(){
        ///delivery/{userId}
        return IntegrationFlows
                .from(WebFlux.inboundGateway("/si-api/delivery/{userId}")
//                        .replyChannel("sampleChannel")
                        .headerExpression("my_var", "#pathVariables.userId")
                )
                .headerFilter("accept-encoding", false)
                .log(LoggingHandler.Level.INFO, "Http.inboundGateway pathVariables-parsing", "headers.my_var")
                .handle(Http.outboundGateway("http://localhost:8080/delivery/{userId}")
                        .uriVariable("userId", "headers.my_var")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(Order[].class)
                )
                .get();
    }
}
