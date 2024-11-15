package com.domain.controller.command;

import com.domain.controller.Command;
import com.domain.model.Order;
import com.domain.model.OrderFactory;
import java.util.List;

public class CreateOrderCommand implements Command {
    private List<Order> orderList;
    private OrderFactory orderFactory;
    private Order order;
    private String orderId;
    private String customerName;
    private String orderDetails;

    public CreateOrderCommand(List<Order> orderList, OrderFactory orderFactory, String orderId, String customerName, String orderDetails) {
        this.orderList = orderList;
        this.orderFactory = orderFactory;
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }

    @Override
    public void execute() {
        order = orderFactory.createOrder(orderId, customerName, orderDetails);
        orderList.add(order);
        System.out.println("Order created: " + orderId);
    }

    @Override
    public void undo() {
        if (order != null) {
            orderList.remove(order);
            System.out.println("Order creation undone: " + orderId);
        }
    }
}

