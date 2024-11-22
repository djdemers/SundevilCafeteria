package com.domain.controller;

import com.domain.model.Order;
import com.domain.model.OrderManager;
import com.domain.model.OrderStatusObserver;
import com.domain.service.OrderService;
import com.domain.ui.CustomDialogBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.stream.Collectors;

public class OperatorController {

    @FXML
    private ListView<String> orderListView;

    @FXML
    private Button updateOrderStatusButton;

    @FXML
    private Button viewOrderDetailsButton;

    @FXML
    private Label statusLabel;

    private Label nameLabel;
    private Label orderIdLabel;
    private Label orderDetailsLabel;
    private Label orderStatusLabel;


    private OrderManager orderManagerModel;
    private OrderService orderService;

    public OperatorController() {
        this.orderManagerModel = OrderManager.getInstance(); // Singleton pattern for managing orders
        this.orderService = new OrderService();
    }

    @FXML
    private void initialize() {
        loadOrders();
    }


    private void loadOrders() {
        List<Order> orders = orderManagerModel.getAllOrders();
        orderListView.setItems(FXCollections.observableArrayList(
                orders.stream().map(this::formatOrderString).collect(Collectors.toList())
        ));
    }

    private String formatOrderString(Order order) {
        return String.format("%-15s Status: %-10s",
                order.getOrderId(), order.getStatus());
    }


    @FXML
    private void handleUpdateOrderStatus(ActionEvent event) {
        String selectedOrder = orderListView.getSelectionModel().getSelectedItem().split(" ")[0].trim();
        if (selectedOrder != null) {
            Order order = orderManagerModel.getOrderById(selectedOrder);
            if (order != null) {

                Button pendingButton = new Button("Set Pending");
                pendingButton.setOnAction(e -> {
                    orderService.updateOrderStatus(selectedOrder, "Pending");
                    statusLabel.setText("Order status updated to Pending.");
                    loadOrders(); // Refresh order list
                });
                Button preparingButton = new Button("Set Preparing");
                preparingButton.setOnAction(e -> {
                    orderService.updateOrderStatus(selectedOrder, "Preparing");
                    statusLabel.setText("Order status updated to Preparing.");
                    loadOrders(); // Refresh order list
                });
                Button completedButton = new Button("Set Completed");
                completedButton.setOnAction(e -> {
                    orderService.updateOrderStatus(selectedOrder, "Completed");
                    statusLabel.setText("Order status updated to Completed.");
                    loadOrders(); // Refresh order list
                });
                CustomDialogBox.showCustomDialog("Update Order Status", "Select a new status for " + selectedOrder + ": ",
                        pendingButton, preparingButton, completedButton);

            }
        } else {
            statusLabel.setText("Please select an order to update.");
        }
    }

    @FXML
    private void handleViewOrderDetails(ActionEvent event) {
        String selectedOrder = orderListView.getSelectionModel().getSelectedItem().split(" ")[0].trim();
        if (selectedOrder != null) {
            statusLabel.setText("Viewing details for: " + selectedOrder);
            Order order = orderManagerModel.getOrderById(selectedOrder);
            if (order != null) {
                nameLabel = new Label("Customer Name: " + order.getCustomerName());
                orderIdLabel = new Label("Order ID: " + order.getOrderId());
                orderDetailsLabel = new Label("Order Details: " + order.getOrderDetails());
                orderStatusLabel = new Label("Order Status: " + order.getStatus());
                CustomDialogBox.showCustomDialog("Order Details", "Details for order: " + selectedOrder,
                        nameLabel, orderIdLabel, orderDetailsLabel, orderStatusLabel);
            }
        } else {
            statusLabel.setText("Please select an order to view details.");
        }
    }
}

