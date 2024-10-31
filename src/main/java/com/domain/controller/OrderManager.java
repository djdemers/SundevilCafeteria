package com.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.domain.model.Customer;
import com.domain.model.Order;

public class OrderManager {
    @FXML
    private TextField customerNameField;

    @FXML
    private TextField orderDetailsField;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private void handlePlaceOrder() {
        String customerName = customerNameField.getText();
        String orderDetails = orderDetailsField.getText();

        if (!customerName.isEmpty() && !orderDetails.isEmpty()) {
            // Test data
            String customerId = "custId";
            String password = "defaultPassword"; // Placeholder password
            Customer customer = new Customer(customerName, password, customerId);
            Order newOrder = new Order("orderId", customer);
            orderStatusLabel.setText("Order placed for: " + customerName);
        } else {
            orderStatusLabel.setText("Please enter customer name and order details");
        }
    }

    @FXML
    private void handleCancelOrder() {
        String customerName = customerNameField.getText();
        // Implement logic to find and cancel the order
        orderStatusLabel.setText("Order for " + customerName + " has been cancelled");
    }
}
