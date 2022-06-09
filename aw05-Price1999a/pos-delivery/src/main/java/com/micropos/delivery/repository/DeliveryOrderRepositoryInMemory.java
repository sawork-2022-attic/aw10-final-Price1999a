package com.micropos.delivery.repository;

import com.micropos.delivery.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class DeliveryOrderRepositoryInMemory implements DeliveryOrderRepository {
    List<Order> allOrders = new ArrayList<>();

    @Override
    public List<Order> allOrders() {
        if (allOrders.size() == 0) allOrders.add(new Order());
        return allOrders;
    }

    @Override
    public List<Order> listOrdersbyUserID(String userID) {
        List<Order> toRet = new ArrayList<>();
        for (Order o : allOrders)
            if (o.getUserID().equals(userID))
                toRet.add(o);
        Collections.reverse(toRet);
        return toRet;
    }

    @Override
    public boolean addOrder(Order order) {
        return allOrders.add(order);
    }
}
