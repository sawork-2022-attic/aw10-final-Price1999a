package com.micropos.cart.model;

import java.io.Serializable;

public class Item implements Serializable {
    private Product product;
    private int quantity;

    public Item(Product p, int q) {
        product = p;
        quantity = q;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
