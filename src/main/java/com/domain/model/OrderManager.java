// OrderManager Class
package com.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to manage all orders within the system.
 * Provides functionality to add, update, and retrieve orders, ensuring centralized order management.
 */
public class OrderManager {
    private static OrderManager instance; // Singleton instance
    private List<Order> orders;          // List of all orders

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes an empty list of orders.
     */
    private OrderManager() {
        orders = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of OrderManager.
     *
     * @return The single instance of OrderManager.
     */
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Adds an order to the order list.
     *
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Updates the status of an order by its name or details.
     * If the order is found, the status is updated.
     *
     * @param orderName The name or details of the order to update.
     */
    public void updateOrderStatus(String orderName) {
        for (Order order : orders) {
            if (order.getOrderDetails().equals(orderName)) {
                order.updateStatus("Updated Status"); // Example status
                return;
            }
        }
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A copy of the list of all orders.
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders); // Defensive copying
    }

    /**
     * Retrieves the status of an order by the customer's name.
     *
     * @param customerName The name of the customer.
     * @return The status of the customer's order, or null if no order is found.
     */
    public String getOrderStatusByCustomer(String customerName) {
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)) {
                return order.getStatus();
            }
        }
        return null; // No matching order found
    }

    /**
     * Retrieves an order by its unique ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The matching order, or null if no order is found.
     */
    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // No matching order found
    }
}

