package com.micropos.delivery.repository;

import com.micropos.delivery.model.Order;

import java.util.List;

public interface DeliveryOrderRepository {
    List<Order> allOrders();

    List<Order> listOrdersbyUserID(String userID);

    boolean addOrder(Order order);
}
