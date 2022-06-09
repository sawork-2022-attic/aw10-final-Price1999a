package com.micropos.cart.service;

import com.micropos.cart.dto.ProductDto;
import com.micropos.cart.model.Item;
import com.micropos.cart.model.Product;

import java.util.List;

public interface CartService {
    public void checkout(List<Item> cart, String userID);

    public List<Item> add(List<Item> cart, Product productId, int amount);

    public ProductDto getProductFromId(String productId);

    public List<Item> delete(List<Item> cart, String productId);

    public List<Item> modify(List<Item> cart, String productId, int amount);
}
