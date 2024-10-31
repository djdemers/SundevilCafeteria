package com.domain.model;

public class OrderFactory {
    public Order createOrder(String orderId) {
        return new Order(orderId);
    }
}