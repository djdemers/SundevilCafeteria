package com.domain.model;

import com.domain.util.PasswordUtils;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String customerId; // Unique ID for the customer
    private String email;      // Customer's email address
    private String phoneNumber; // Customer's phone number
    private String address;    // Customer's physical address
    private List<Order> orderHistory; // List of the customer's past orders

    /**
     * Constructs a new Customer with a username, plain-text password, email, and contact info.
     *
     * @param username      The username of the customer.
     * @param plainPassword The plain-text password of the customer.
     * @param email         The customer's email address.
     * @param phoneNumber   The customer's phone number.
     * @param address       The customer's physical address.
     */
    public Customer(String username, String plainPassword, String email, String phoneNumber, String address) {
        // Generate a unique salt and hash the password before calling super
        this(username, PasswordUtils.hashPassword(plainPassword, PasswordUtils.generateSalt()),
                PasswordUtils.generateSalt(), email, phoneNumber, address);
    }

    /**
     * Overloaded constructor to create a Customer with pre-hashed password, salt, and contact info.
     * Useful when loading users from persistent storage.
     *
     * @param username       The username of the customer.
     * @param hashedPassword The already hashed password.
     * @param salt           The salt used for hashing.
     * @param email          The customer's email address.
     * @param phoneNumber    The customer's phone number.
     * @param address        The customer's physical address.
     */
    public Customer(String username, String hashedPassword, String salt, String email, String phoneNumber, String address) {
        super(username, hashedPassword, salt, "CUSTOMER");
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderHistory = new ArrayList<>();
    }

    // Getters and setters for contact information
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    // Methods to manage order history
    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
