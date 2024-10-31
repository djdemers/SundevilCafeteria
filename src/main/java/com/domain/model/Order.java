// Order Class (Observer Pattern)
package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String status;
    private List<Observer> observers;

    public Order(String orderId) {
        this.orderId = orderId;
        this.status = "Pending";
        this.observers = new ArrayList<>();
    }

    // Observer Management
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    // Update Order Status and notify observers
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }
}