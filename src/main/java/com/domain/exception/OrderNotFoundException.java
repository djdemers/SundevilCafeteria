package com.domain.exception;

/**
 * Custom exception for cases where an order is not found in the system.
 *
 * This exception is thrown when an order cannot be located based on the given criteria,
 * such as an order ID or customer details.
 */
public class OrderNotFoundException extends Exception {

    /**
     * Constructs a new `OrderNotFoundException` with no detail message.
     */
    public OrderNotFoundException() {
        super("Order not found.");
    }

    /**
     * Constructs a new `OrderNotFoundException` with the specified detail message.
     *
     * @param message The detail message, which provides more information about the exception.
     */
    public OrderNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new `OrderNotFoundException` with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception (e.g., another exception that caused this one).
     */
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
