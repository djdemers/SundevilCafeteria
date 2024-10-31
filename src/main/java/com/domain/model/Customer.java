package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String customerId;
    private List<Order> orderHistory;

    public Customer(String username, String password) {
        super(username, password, "customer");
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
}