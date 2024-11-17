package com.domain.controller;

import com.domain.model.Order;
import com.domain.model.OrderManager;
import com.domain.model.OrderStatusObserver;
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

    private OrderManager orderManagerModel;

    public OperatorController() {
        this.orderManagerModel = OrderManager.getInstance(); // Singleton pattern for managing orders
    }

    @FXML
    private void initialize() {
        loadOrders();
    }


    private void loadOrders() {
        List<Order> orders = orderManagerModel.getAllOrders();
        orderListView.setItems(FXCollections.observableArrayList(
                orders.stream().map(Order::toString).collect(Collectors.toList())
        ));
    }


    @FXML
    private void handleUpdateOrderStatus(ActionEvent event) {
        String selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            Order order = orderManagerModel.getOrderById(selectedOrder);
            if (order != null) {
                // Add an observer for demonstration purposes
                OrderStatusObserver observer = new OrderStatusObserver("OrderManager");
                order.addObserver(observer);

                // Update the order status and notify observers
                order.updateStatus("Ready");
                statusLabel.setText("Order status updated to 'Ready'.");

                loadOrders(); // Refresh order list
            }
        } else {
            statusLabel.setText("Please select an order to update.");
        }
    }

    @FXML
    private void handleViewOrderDetails(ActionEvent event) {
        String selectedOrder = orderListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            statusLabel.setText("Viewing details for: " + selectedOrder);
        } else {
            statusLabel.setText("Please select an order to view details.");
        }
    }
}

