package com.micropos.delivery.rest;

import com.micropos.delivery.api.DeliveryApi;
import com.micropos.delivery.dto.OrderDto;
import com.micropos.delivery.mapper.OrdersMapper;
import com.micropos.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("delivery-api")
@Log4j2
public class DeliveryController implements DeliveryApi {
    private final OrdersMapper ordersMapper;
    private final DeliveryService deliveryService;

    public DeliveryController(OrdersMapper o, DeliveryService d) {
        this.deliveryService = d;
        this.ordersMapper = o;
    }

//    @Override
//    public ResponseEntity<List<OrderDto>> listInfoByUserID(
//            @Parameter(name = "userId", description = "User id to view delivery information", required = true, schema = @Schema(description = "")) @PathVariable("userId") String userId
//    ) {
//        log.info("listInfoByUserID({})", userId);
//        List<OrderDto> orderDtoList;
//        if ("admin".equals(userId)) {
//            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.orders()));
//        } else {
//            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.ordersByUserID(userId)));
//        }
//        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
//    }

    private List<OrderDto> listFromSer(String userId) {
        log.info("listFromSer({})", userId);
        List<OrderDto> orderDtoList;
        if ("admin".equals(userId)) {
            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.orders()));
        } else {
            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.ordersByUserID(userId)));
        }
        return orderDtoList;
    }

    private Flux<OrderDto> dataFromSer(String userId) {
//        log.info("dataFromSer({})", userId);
//        List<OrderDto> orderDtoList;
//        if ("admin".equals(userId)) {
//            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.orders()));
//        } else {
//            orderDtoList = new ArrayList<>(ordersMapper.toOrdersDto(deliveryService.ordersByUserID(userId)));
//        }
        return Flux.fromArray(listFromSer(userId).toArray(new OrderDto[0]));
    }


    @Override
    public Mono<ResponseEntity<Flux<OrderDto>>> listInfoByUserID(String userId, ServerWebExchange exchange) {
        return Mono.just(
                        dataFromSer(userId)
                ).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/delivery-sse/{userId}",
            produces = {"text/event-stream;charset=UTF-8"}
    )
    public Flux<List<OrderDto>> listInfoByUserIDSSE(
            @Parameter(name = "userId", description = "User id to view delivery information", required = true, schema = @Schema(description = "")) @PathVariable("userId") String userId
    ) {
        return Flux.interval(Duration.ofSeconds(5))
                .map(sequence -> listFromSer(userId));
    }
}
