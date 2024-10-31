package com.domain.model;

public class OrderStatusObserver implements Observer {
    private String observerName;

    public OrderStatusObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(String status) {
        System.out.println(observerName + " has been notified. Order status updated to: " + status);
    }
}
