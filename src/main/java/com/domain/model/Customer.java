package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String customerId;
    private List<Order> orderHistory;

    /**
     * Constructs a Customer with the specified username and plain-text password.
     * The password is hashed before being passed to the User constructor.
     *
     * @param username The customer's username.
     * @param plainPassword The customer's plain-text password.
     */
    public Customer(String username, String plainPassword) {
        super(username, com.domain.util.PasswordUtils.hashPassword(plainPassword), "CUSTOMER");
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