// OrderFactory Class
package com.domain.model;

/**
 * Factory class to create instances of the Order class.
 * Provides a consistent and centralized way to construct Order objects.
 *
 * This adheres to the Factory design pattern, which abstracts the object creation process.
 */
public class OrderFactory {

    /**
     * Creates a new Order instance with the specified details.
     *
     * @param customerName Name of the customer placing the order.
     * @param orderDetails Details of the order (items and quantities).
     *
     * @return A new Order object initialized with the provided details.
     */
    public Order createOrder(String orderId, String customerName, String orderDetails) {
        return new Order(orderId, customerName, orderDetails);
    }

}
