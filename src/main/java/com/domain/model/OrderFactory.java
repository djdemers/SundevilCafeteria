package com.domain.model;

public class OrderFactory {
    public Order createOrder(String orderId, String customerName, String orderDetails) {
        return new Order(orderId, customerName, orderDetails);
    }
}