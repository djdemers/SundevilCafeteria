package com.domain.exception;

public class OrderCreationException extends Exception{
    /**
     * Constructs a new OrderCreationException with no message.
     */
    public OrderCreationException() {
        super("Order creation failed.");
    }

    /**
     * Constructs a new OrderCreationException with the specified message.
     * @param message the exception message.
     */
    public OrderCreationException(String message) {
        super(message);
    }

    /**
     * Constructs a new OrderCreationException with the specified message and cause.
     * @param message the exception message.
     * @param cause the cause of the exception.
     */
    public OrderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
