package com.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.domain.model.Customer;
import com.domain.model.Order;
import com.domain.model.OrderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OrderManager {
    @FXML
    private TextField customerNameField;

    @FXML
    private TextField orderDetailsField;

    @FXML
    private Label orderStatusLabel;

    private List<Order> orderList;
    private OrderFactory orderFactory;
    private Stack<Command> commandHistory;

    // Constructor for non-JavaFX usage
    public OrderManager(OrderFactory orderFactory) {
        this.orderList = new ArrayList<>();
        this.orderFactory = orderFactory;
        this.commandHistory = new Stack<>();
    }

    // Constructor for JavaFX usage
    public OrderManager() {
        this.orderFactory = new OrderFactory();
        this.orderList = new ArrayList<>();
        this.commandHistory = new Stack<>();
    }

    @FXML
    private void handlePlaceOrder() {
        String customerName = customerNameField.getText();
        String orderDetails = orderDetailsField.getText();

        if (!customerName.isEmpty() && !orderDetails.isEmpty()) {
            // Test data for Customer object
            String customerId = "custId";
            String password = "defaultPassword"; // Placeholder password
            Customer customer = new Customer(customerName, password, customerId);

            // Generate an order ID
            String orderId = "order_" + System.currentTimeMillis(); // Simple ID generation for demonstration
            Order newOrder = new Order(orderId);
            Command command = new CreateOrderCommand(orderList, orderFactory, orderId);
            command.execute();
            commandHistory.push(command);
            orderStatusLabel.setText("Order placed for: " + customerName);
        } else {
            orderStatusLabel.setText("Please enter customer name and order details");
        }
    }

    @FXML
    private void handleCancelOrder() {
        String orderId = orderDetailsField.getText();

        if (!orderId.isEmpty()) {
            Command command = new CancelOrderCommand(orderList, orderId);
            command.execute();
            commandHistory.push(command);
            orderStatusLabel.setText("Order with ID " + orderId + " has been cancelled");
        } else {
            orderStatusLabel.setText("Please enter a valid order ID to cancel");
        }
    }

    @FXML
    private void handleUndoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
            orderStatusLabel.setText("Last command undone");
        } else {
            orderStatusLabel.setText("No commands to undo");
        }
    }

    public void createOrder(String orderId) {
        Command command = new CreateOrderCommand(orderList, orderFactory, orderId);
        command.execute();
        commandHistory.push(command);
    }

    public void cancelOrder(String orderId) {
        Command command = new CancelOrderCommand(orderList, orderId);
        command.execute();
        commandHistory.push(command);
    }

    public void updateOrderStatus(String orderId, String newStatus) {
        Order order = findOrderById(orderId);
        if (order != null) {
            Command command = new UpdateOrderCommand(order, newStatus);
            command.execute();
            commandHistory.push(command);
        }
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command command = commandHistory.pop();
            command.undo();
        }
    }

    // Finding an Order by ID to match the previous functionality
    public Order findOrderById(String orderId) {
        for (Order o : orderList) {
            if (o.getOrderId().equals(orderId)) {
                return o;
            }
        }
        return null;
    }
}
