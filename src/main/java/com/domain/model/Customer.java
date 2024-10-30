package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private List<Order> orderHistory;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.orderHistory = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    // Getters and setters for customerId and name
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}