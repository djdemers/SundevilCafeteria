package com.domain.model;

public class OrderFactory {
    public static Order createOrder(String orderId, Customer customer) {
        return new Order(orderId, customer);
    }
}