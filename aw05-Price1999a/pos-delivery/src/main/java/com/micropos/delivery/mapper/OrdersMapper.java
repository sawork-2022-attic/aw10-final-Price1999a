package com.micropos.delivery.mapper;

import com.micropos.delivery.dto.OrderDto;
import com.micropos.delivery.model.Order;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface OrdersMapper {
    Collection<OrderDto> toOrdersDto(Collection<Order> orders);
}
