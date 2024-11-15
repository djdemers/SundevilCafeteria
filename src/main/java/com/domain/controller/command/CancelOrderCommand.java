package com.domain.controller.command;

import com.domain.model.Order;
import java.util.List;

/**
 * Concrete command class implementing the Command interface.
 *
 * The CancelOrderCommand class is part of the Command design pattern and is responsible
 * for encapsulating the logic to cancel an order and undo the cancellation if necessary.
 */
public class CancelOrderCommand implements Command {
    private List<Order> orderList; // The list of all orders
    private Order order;          // The order being acted upon
    private String orderId;       // The ID of the order to cancel

    /**
     * Constructs a CancelOrderCommand with the provided order list and order ID.
     *
     * @param orderList The list of orders to modify.
     * @param orderId   The ID of the order to be cancelled.
     */
    public CancelOrderCommand(List<Order> orderList, String orderId) {
        this.orderList = orderList;
        this.orderId = orderId;
    }

    /**
     * Executes the cancellation of the specified order.
     * If the order exists, it is removed from the order list.
     */
    @Override
    public void execute() {
        order = findOrderById(orderId);
        if (order != null) {
            orderList.remove(order);
            System.out.println("Order cancelled: " + orderId);
        }
    }

    /**
     * Undoes the cancellation of the specified order.
     * If the order was previously cancelled, it is added back to the order list.
     */
    @Override
    public void undo() {
        if (order != null) {
            orderList.add(order);
            System.out.println("Order cancellation undone: " + orderId);
        }
    }

    /**
     * Finds an order by its ID in the provided order list.
     *
     * @param orderId The ID of the order to find.
     * @return The Order object if found; otherwise, null.
     */
    private Order findOrderById(String orderId) {
        for (Order o : orderList) {
            if (o.getOrderId().equals(orderId)) {
                return o;
            }
        }
        return null;
    }
}
