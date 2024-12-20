package com.domain.controller;

import com.domain.model.Menu;
import com.domain.model.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {

    @FXML
    private ListView<String> menuListView;

    @FXML
    private Button breakfastButton;

    @FXML
    private Button lunchButton;

    @FXML
    private Button dinnerButton;

    @FXML
    private Button beveragesButton;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private TextField itemDescriptionField;

    @FXML
    private Label statusLabel;
    private MainController parentController;
    @FXML
    private Button backButton; // Back button for returning to MainController

    private Menu menu;

    public ManagerController() {
        this.menu = Menu.getInstance(); // Singleton pattern for menu
    }

    /**
     * Sets the parent controller reference.
     *
     * @param parentController The MainController instance.
     */
    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    /**
     * Handles the "Back" button action to return to the main view.
     */
    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/main-view.fxml"));
            Scene scene = new Scene(loader.load());

            MainController mainController = loader.getController();
            mainController.setUser(parentController.getUser());

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - Manager Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log or display the exception
        }
    }

    @FXML
    private void initialize() {
        breakfastButton.setOnAction(event -> loadMenuItems("Breakfast"));
        lunchButton.setOnAction(event -> loadMenuItems("Lunch"));
        dinnerButton.setOnAction(event -> loadMenuItems("Dinner"));
        beveragesButton.setOnAction(event -> loadMenuItems("Beverages"));
    }

    private void loadMenuItems(String menuType) {
        menuListView.getItems().clear();
        for (MenuItem item : menu.getMenuItemsByType(menuType)) {
            menuListView.getItems().add(item.getName() + " - $" + item.getPrice() + "\n" + item.getDescription());
        }
    }

    @FXML
    private void handleAddItem() {
        String name = itemNameField.getText();
        String description = itemDescriptionField.getText();
        String priceText = itemPriceField.getText();

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
            statusLabel.setText("All fields must be filled to add an item.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            MenuItem newItem = new MenuItem(name, "General", description, price);
            menu.addMenuItem(newItem);
            statusLabel.setText("Item added successfully: " + name);
            loadMenuItems("General");
        } catch (NumberFormatException e) {
            statusLabel.setText("Price must be a valid number.");
        }
    }

    @FXML
    private void handleRemoveItem() {
        String name = itemNameField.getText();

        if (name.isEmpty()) {
            statusLabel.setText("Please enter the item name to remove.");
            return;
        }

        boolean removed = menu.removeMenuItem(name);
        if (removed) {
            statusLabel.setText("Item removed successfully: " + name);
            loadMenuItems("General");
        } else {
            statusLabel.setText("Item not found: " + name);
        }
    }

    @FXML
    private void handleEditDescription() {
        String name = itemNameField.getText();
        String newDescription = itemDescriptionField.getText();

        if (name.isEmpty() || newDescription.isEmpty()) {
            statusLabel.setText("Please enter both the item name and the new description.");
            return;
        }

        boolean updated = menu.editMenuItemDescription(name, newDescription);
        if (updated) {
            statusLabel.setText("Description updated successfully for item: " + name);
            loadMenuItems("General");
        } else {
            statusLabel.setText("Item not found: " + name);
        }
    }

    @FXML
    private void handleEditPrice() {
        String name = itemNameField.getText();
        String newPriceText = itemPriceField.getText();

        if (name.isEmpty() || newPriceText.isEmpty()) {
            statusLabel.setText("Please enter both the item name and the new price.");
            return;
        }

        try {
            double newPrice = Double.parseDouble(newPriceText);
            boolean updated = menu.editMenuItemPrice(name, newPrice);
            if (updated) {
                statusLabel.setText("Price updated successfully for item: " + name);
                loadMenuItems("General");
            } else {
                statusLabel.setText("Item not found: " + name);
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Price must be a valid number.");
        }
    }


}

