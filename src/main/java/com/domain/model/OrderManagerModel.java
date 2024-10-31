package com.domain.model;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerModel {
    private static OrderManagerModel instance;
    private List<Order> orders;

    // Private constructor to enforce singleton pattern
    private OrderManagerModel() {
        orders = new ArrayList<>();
    }

    // Get the singleton instance
    public static OrderManagerModel getInstance() {
        if (instance == null) {
            instance = new OrderManagerModel();
        }
        return instance;
    }

    // Add an order
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Update order status by order name or ID
    public void updateOrderStatus(String orderName) {
        for (Order order : orders) {
            if (order.getOrderDetails().equals(orderName)) {
                order.updateStatus("Updated Status");
                return;
            }
        }
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    // Get order status by customer name
    public String getOrderStatusByCustomer(String customerName) {
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)) {
                return order.getStatus();
            }
        }
        return null; // No order found
    }
    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // Order not found
    }

}
