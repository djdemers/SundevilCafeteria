package com.domain.controller;

import com.domain.controller.command.CreateOrderCommand;
import com.domain.model.*;
import com.domain.model.Menu;
import com.domain.model.MenuItem;
import com.domain.service.OrderService;
import com.domain.ui.CustomDialogBox;
import com.domain.util.NavigationUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerController {

    @FXML
    private ListView<String> menuListView;

    @FXML
    private AnchorPane mainView;

    @FXML
    private AnchorPane orderHistoryView;

    @FXML
    private VBox cartVBox;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private Label totalLabel;

    @FXML
    private Button breakfastButton;

    @FXML
    private Button lunchButton;

    @FXML
    private Button dinnerButton;

    @FXML
    private Button beveragesButton;

    @FXML
    private Button checkoutButton;

    @FXML
    private ListView<String> orderHistoryListView;

    @FXML
    private Button logoutButton;


    private Menu menu;
    private List<MenuItem> cart;
    private Customer customer;

    private OrderService orderService;
    private OrderManager orderManager;

    public CustomerController() {
        this.menu = Menu.getInstance(); // Singleton pattern for menu
        this.cart = new ArrayList<>();
        this.orderService = new OrderService();
        this.orderManager = OrderManager.getInstance();
    }

    /**
     * Sets the authenticated customer and updates the UI.
     *
     * @param customer The authenticated customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     */

    @FXML
    private void initialize() {
        breakfastButton.setOnAction(event -> loadMenuItems("Breakfast"));
        lunchButton.setOnAction(event -> loadMenuItems("Lunch"));
        dinnerButton.setOnAction(event -> loadMenuItems("Dinner"));
        beveragesButton.setOnAction(event -> loadMenuItems("Beverages"));

        menuListView.setOnMouseClicked(event -> handleAddToCart());
    }

    /**
     * Loads menu items based on the selected menu type.
     *
     * @param menuType The type of menu to load (e.g., Breakfast, Lunch).
     */
    private void loadMenuItems(String menuType) {
        menuListView.getItems().clear();
        for (MenuItem item : menu.getMenuItemsByType(menuType)) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice() + "\n" + item.getDescription());
        }
    }

    /**
     * Loads the customer's order history.
     */
    private void loadOrderHistory() {
        orderHistoryListView.getItems().clear();
        for (Order order : customer.getOrderHistory()) {
            orderHistoryListView.getItems().add(order.getOrderId() + " - " + order.getStatus());
        }
    }

    /**
     * Adds the selected menu item to the customer's cart.
     */
    private void handleAddToCart() {
        String selectedItem = menuListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String itemName = selectedItem.split(" - \\$")[0];
            MenuItem menuItem = menu.getMenuItemByName(itemName);

            if (menuItem != null) {
                cart.add(menuItem);
                cartListView.getItems().add(menuItem.getName() + " - $" + menuItem.getPrice());
                updateTotal();
            }
        }
    }

    /**
     * Updates the total price label based on the items in the cart.
     */
    private void updateTotal() {
        double total = cart.stream().mapToDouble(MenuItem::getPrice).sum();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    /**
     * Handles the checkout process by creating an order and clearing the cart.
     */
    @FXML
    private void handleCheckout() {
        if (cart.isEmpty()) {
            totalLabel.setText("Cart is empty. Please add items.");
            return;
        }

        // Generate a unique order ID
        String orderId = "ORDER-" + System.currentTimeMillis();

        // Convert cart items to a single string for order details
        String orderDetails = cart.stream()
                .map(MenuItem::getName) // Assuming cart contains MenuItem objects
                .collect(Collectors.joining(", "));


        // Use the command to create and execute the order
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                customer.getOrderHistory(),  // Pass the customer's order history as the order list
                new OrderFactory(),          // Use the factory to create the order
                orderId,                     // Order ID
                customer.getUsername(),      // Customer's username
                orderDetails                 // Order details
        );



        createOrderCommand.execute();

        // Clear the cart and update the UI
        cart.clear();
        cartListView.getItems().clear();
        updateTotal();

        totalLabel.setText("Order placed successfully!");
        displayOrderHistory();
    }

    @FXML
    private void manageOrder() {
        String selectedOrder = orderHistoryListView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null && !selectedOrder.isEmpty()) {
            String finalSelectedOrder = selectedOrder.split(" ")[0].trim();
            Order order = null;
            for (Order o : customer.getOrderHistory()) {
                if (o.getOrderId().equalsIgnoreCase(finalSelectedOrder)) {
                    order = o;
                }
            }
            if (order != null) {
                Label orderNameLabel = new Label("Customer Name: " + order.getCustomerName());
                Label orderStatusLabel = new Label("Order Status: " + order.getStatus());
                Label orderDetailsLabel = new Label("Order Details: " + order.getOrderDetails());
                Button cancelOrderButton = new Button("Cancel Order");
                cancelOrderButton.setOnAction(e -> {
                    orderService.cancelOrder(finalSelectedOrder);
                    loadOrderHistory();
                });
                CustomDialogBox.showCustomDialog("Order Details", "Details for order: " + finalSelectedOrder,
                        orderNameLabel, orderStatusLabel, orderDetailsLabel, cancelOrderButton);
            } else {

            }
        } else {

        }


    }

    @FXML
    private void displayOrderHistory() {
        mainView.setVisible(false);
        loadOrderHistory();
        orderHistoryView.setVisible(true);
    }

    @FXML
    private void handleBack() {
        orderHistoryView.setVisible(false);
        mainView.setVisible(true);
    }

    /**
     * Logs out the user and navigates back to the login view.
     */
    @FXML
    private void handleLogout() {
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();
        NavigationUtils.navigateToLogin(currentStage);
    }

}
