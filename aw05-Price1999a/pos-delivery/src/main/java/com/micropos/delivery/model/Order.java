package com.micropos.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private List<Item> cart;
    private double orderTotalPrice;
    private String userID;
    private String deliveryState;
    private String deliveryID;
}
