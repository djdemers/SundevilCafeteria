package com.domain.controller.command;

import com.domain.model.Order;
import com.domain.model.OrderFactory;
import java.util.List;

/**
 * Concrete command class implementing the Command interface.
 *
 * The CreateOrderCommand encapsulates the logic to create an order using the
 * provided details and adds it to a given order list. It also provides an undo mechanism
 * to remove the created order.
 */
public class CreateOrderCommand implements Command {
    private List<Order> orderList;        // The list of orders where the new order will be added
    private OrderFactory orderFactory;   // Factory for creating Order instances
    private Order order;                 // The newly created order
    private String orderId;              // ID of the new order
    private String customerName;         // Name of the customer placing the order
    private String orderDetails;         // Details of the order

    /**
     * Constructs a CreateOrderCommand with the necessary details for creating an order.
     *
     * @param orderList      The list of existing orders.
     * @param orderFactory   The factory for creating new orders.
     * @param orderId        The unique ID for the new order.
     * @param customerName   The name of the customer placing the order.
     * @param orderDetails   The details of the order.
     */
    public CreateOrderCommand(List<Order> orderList, OrderFactory orderFactory, String orderId, String customerName, String orderDetails) {
        this.orderList = orderList;
        this.orderFactory = orderFactory;
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }

    /**
     * Executes the command to create and add a new order to the order list.
     */
    @Override
    public void execute() {
        order = orderFactory.createOrder(orderId, customerName, orderDetails);
        orderList.add(order);
        System.out.println("Order created: " + orderId);
    }

    /**
     * Undoes the command by removing the created order from the order list.
     */
    @Override
    public void undo() {
        if (order != null) {
            orderList.remove(order);
            System.out.println("Order creation undone: " + orderId);
        }
    }
}

