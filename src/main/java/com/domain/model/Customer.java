package com.domain.model;

import com.domain.util.PasswordUtils;

import java.util.List;

public class Customer extends User {
    private String customerId;
    private List<Order> orderHistory;

    /**
     * Constructs a new Customer with a username and plain-text password.
     *
     * @param username      The username of the customer.
     * @param plainPassword The plain-text password of the customer.
     */
    public Customer(String username, String plainPassword) {
        // Generate a unique salt and hash the password before calling super
        this(username, PasswordUtils.hashPassword(plainPassword, PasswordUtils.generateSalt()), PasswordUtils.generateSalt());
    }

    /**
     * Overloaded constructor to create a Customer with pre-hashed password and salt.
     * Useful when loading users from persistent storage.
     *
     * @param username       The username of the customer.
     * @param hashedPassword The already hashed password.
     * @param salt           The salt used for hashing.
     */
    public Customer(String username, String hashedPassword, String salt) {
        super(username, hashedPassword, salt, "CUSTOMER");
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