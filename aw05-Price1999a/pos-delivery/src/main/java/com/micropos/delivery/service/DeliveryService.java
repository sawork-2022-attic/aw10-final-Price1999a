package com.micropos.delivery.service;

import com.micropos.delivery.model.Order;

import java.util.List;

public interface DeliveryService {
    List<Order> orders();

    List<Order> ordersByUserID(String userID);

    boolean addOrder(Order order);
}
