// Order Class (Observer Pattern)
package com.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the system and serves as a subject in the Observer design pattern.
 * Observers can subscribe to changes in the order's status and will be notified when updates occur.
 */
public class Order {
    private String orderId; // Unique identifier for the order
    private String customerName; // Name of the customer who placed the order
    private String orderDetails; // Details of the order
    private String status; // Current status of the order (Pending, Preparing, Completed)
    private List<Observer> observers; // List of observers monitoring this order

    /**
     * Constructor to initialize an Order instance.
     *
     * @param orderId      Unique identifier for the order.
     * @param customerName Name of the customer placing the order.
     * @param orderDetails Description of the order details.
     */
    public Order(String orderId, String customerName, String orderDetails) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
        this.status = "Pending"; // Default status for a new order
        this.observers = new ArrayList<>(); // Initialize empty list of observers
    }

    // Observer Management

    /**
     * Adds an observer to the list of observers for this order.
     *
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers for this order.
     *
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of a status change in the order.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    // Update Order Status and notify observers

    /**
     * Updates the status of the order and notifies all observers.
     *
     * @param newStatus The new status for the order.
     */
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    // Getters and Setters

    public String getOrderId() {
        return orderId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public String getStatus() {
        return status;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }
}

