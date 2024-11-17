package com.domain.controller.command;

import com.domain.model.Order;

/**
 * Concrete command class implementing the Command interface.
 *
 * The UpdateOrderCommand class encapsulates the logic to update the status
 * of an order and provides an undo mechanism to revert the status change.
 */
public class UpdateOrderCommand implements Command {
    private Order order;          // The order whose status is being updated
    private String previousStatus; // The order's status before the update
    private String newStatus;      // The new status to apply to the order

    /**
     * Constructs an UpdateOrderCommand with the specified order and new status.
     *
     * @param order     The order to update.
     * @param newStatus The new status to set for the order.
     */
    public UpdateOrderCommand(Order order, String newStatus) {
        this.order = order;
        this.newStatus = newStatus;
    }

    /**
     * Executes the status update on the specified order.
     * The previous status is saved for potential undo.
     */
    @Override
    public void execute() {
        previousStatus = order.getStatus();
        order.updateStatus(newStatus);
        System.out.println("Order status updated to: " + newStatus);
    }

    /**
     * Undoes the status update by restoring the previous status.
     */
    @Override
    public void undo() {
        order.updateStatus(previousStatus);
        System.out.println("Order status reverted to: " + previousStatus);
    }
}
