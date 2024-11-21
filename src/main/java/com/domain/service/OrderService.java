package com.domain.service;

import com.domain.exception.GlobalExceptionHandler;
import com.domain.exception.OrderCreationException;
import com.domain.model.*;

import java.util.Arrays;
import java.util.List;

// SCATTERED LOGIC FOR ADDING, UPDATING, OR RETRIEVING ORDERS IN CONTROLLERS SHOULD BE CONSOLIDATED IN TO THIS SERVICE.


/**
 * Service class for managing order-related business logic.
 *
 * The OrderService class acts as an intermediary between the controllers
 * and the OrderManager model. It provides methods to handle orders, such as
 * creating, updating, retrieving, and managing their lifecycle.
 *
 * Purpose:
 * - To encapsulate order-related operations and logic.
 * - To ensure the separation of concerns by keeping order processing logic
 *   out of the controller and model layers.
 * - To make order operations reusable and easier to test.
 *
 * TODO:
 * - Implement methods for order creation, retrieval, status updates, and cancellation.
 * - Add input validation for order details and customer information.
 * - Handle exceptions and provide meaningful feedback for invalid operations.
 */
public class OrderService {

    private OrderManager orderManager;
    private OrderFactory orderFactory;
    private Menu menu;


    /**
     * Constructor to initialize the service.
     * Uses the singleton instance of OrderManager.
     */
    public OrderService() {
        this.orderManager = OrderManager.getInstance();
        this.orderFactory = new OrderFactory();
        this.menu = Menu.getInstance();
    }

    /**
     * Creates a new order and adds it to the order manager.
     *
     * @param orderID The unique identifier for the order.
     * @param customerName The name of the customer placing the order.
     * @param orderDetails The details of the order, including the items.
     */
    public boolean createOrder(String orderID, String customerName, String orderDetails){
        if (orderID == null || orderID.isEmpty()) {
            GlobalExceptionHandler.handleException(new OrderCreationException("Order ID cannot be empty"));
        } else if (customerName == null || customerName.isEmpty()) {
            GlobalExceptionHandler.handleException(new OrderCreationException("Customer name cannot be empty"));
        }

        List<String> items = Arrays.asList(orderDetails.split(","));
        if (items.isEmpty()) {
            GlobalExceptionHandler.handleException(new OrderCreationException("Order details cannot be empty"));
        }
        for (String itemName : items) {
            MenuItem item = menu.getMenuItemByName(itemName);
            if (item == null) {
                GlobalExceptionHandler.handleException(new OrderCreationException("Invalid item in order: " + itemName));
            }
        }

        Order newOrder = orderFactory.createOrder(orderID, customerName, orderDetails);
        orderManager.addOrder(newOrder);
        System.out.println("Order created in Order Manager: " + orderID);
        return true;
    }

    /**
     * Returns a list of all orders from the order manager.
     *
     * @return A copy of the list of all orders.
     */
    public List<Order> getAllOrders() {
        return orderManager.getAllOrders();
    }

    /**
     * Updates the status of the order to the given status and notifies all listeners.
     *
     * @param orderID The unique identifier of the order.
     * @param status The new status of the order.
     */
    public void updateOrderStatus(String orderID, String status) {
        orderManager.updateOrderStatus(orderID, status);
    }

    /**
     * Cancels an order by removing it from the order manager.
     * If the order is found, it is removed and observers are notified.
     *
     * @param orderID The unique identifier of the order to cancel.
     */
    public void cancelOrder(String orderID) {
        if (orderID == null || orderID.isEmpty()) {
            GlobalExceptionHandler.handleException(new OrderCreationException("Order ID cannot be empty, please try again."));
        }
        orderManager.removeOrder(orderID);
    }

    /**
     * TODO: Add logging and error handling.
     * Example:
     * - Log successful or failed operations.
     * - Handle cases where an order is not found or invalid data is provided.
     */
}
