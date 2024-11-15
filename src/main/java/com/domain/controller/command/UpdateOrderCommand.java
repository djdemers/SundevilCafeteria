package com.domain.controller.command;

import com.domain.controller.Command;
import com.domain.model.Order;

public class UpdateOrderCommand implements Command {
    private Order order;
    private String previousStatus;
    private String newStatus;

    public UpdateOrderCommand(Order order, String newStatus) {
        this.order = order;
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        previousStatus = order.getStatus();
        order.updateStatus(newStatus);
        System.out.println("Order status updated to: " + newStatus);
    }

    @Override
    public void undo() {
        order.updateStatus(previousStatus);
        System.out.println("Order status reverted to: " + previousStatus);
    }
}