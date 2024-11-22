package com.domain.controller;

import com.domain.controller.command.CreateOrderCommand;
import com.domain.model.*;
import com.domain.model.Menu;
import com.domain.model.MenuItem;
import com.domain.service.OrderService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerController {

    @FXML
    private ListView<String> menuListView;

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

    private Menu menu;
    private List<MenuItem> cart;
    private Customer customer;

    private OrderFactory orderFactory;

    public CustomerController() {
        this.menu = Menu.getInstance(); // Singleton pattern for menu
        this.cart = new ArrayList<>();
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
    }

    @FXML
    private void displayOrderHistory() {
        //TODO
    }

}
