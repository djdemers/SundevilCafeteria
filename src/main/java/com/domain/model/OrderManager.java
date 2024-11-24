// OrderManager Class
package com.domain.model;

import com.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to manage all orders within the system.
 * Provides functionality to add, update, and retrieve orders, ensuring centralized order management.
 */
public class OrderManager {
    private static OrderManager instance; // Singleton instance
    private OrderRepository orderRepository;

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes an empty list of orders.
     */
    private OrderManager() {
        orderRepository = new OrderRepository();
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
        orderRepository.addOrder(order);
    }

    /**
     * Updates the status of an order by its ID.
     * If the order is found, the status is updated.
     *
     * @param orderID The orderID of the order to update.
     */
    public void updateOrderStatus(String orderID, String status) {
        Order newOrder = orderRepository.getOrderById(orderID);
        if (newOrder != null) {
            newOrder.updateStatus(status);
            orderRepository.updateOrder(newOrder);
        }
    }

    /**
     * Removes an order from the order list by its ID.
     * If the order is found, it is removed and observers are notified.
     *
     * @param orderID The orderID of the order to remove.
     */
    public void removeOrder(String orderID) {
        orderRepository.removeOrder(orderID);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return A copy of the list of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    /**
     * Retrieves the status of an order by the customer's name.
     *
     * @param customerName The name of the customer.
     * @return The status of the customer's order, or null if no order is found.
     */
    public String getOrderStatusByCustomer(String customerName) {
        for (Order order : orderRepository.getAllOrders()) {
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
        return orderRepository.getOrderById(orderId);
    }
}

