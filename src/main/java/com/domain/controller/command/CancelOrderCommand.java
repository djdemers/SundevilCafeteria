package com.domain.controller.command;

import com.domain.controller.Command;
import com.domain.model.Order;
import java.util.List;

public class CancelOrderCommand implements Command {
    private List<Order> orderList;
    private Order order;
    private String orderId;

    public CancelOrderCommand(List<Order> orderList, String orderId) {
        this.orderList = orderList;
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        order = findOrderById(orderId);
        if (order != null) {
            orderList.remove(order);
            System.out.println("Order cancelled: " + orderId);
        }
    }

    @Override
    public void undo() {
        if (order != null) {
            orderList.add(order);
            System.out.println("Order cancellation undone: " + orderId);
        }
    }

    private Order findOrderById(String orderId) {
        for (Order o : orderList) {
            if (o.getOrderId().equals(orderId)) {
                return o;
            }
        }
        return null;
    }
}