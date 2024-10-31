package com.domain.controller;

import com.domain.model.Menu;
import com.domain.model.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MenuManager {
    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private Label statusLabel;

    private Menu menu;

    public MenuManager() {
        menu = new Menu();
    }

    @FXML
    private void handleAddItem() {
        String itemName = itemNameField.getText();
        String itemPriceText = itemPriceField.getText();

        try {
            double itemPrice = Double.parseDouble(itemPriceText);
            MenuItem newItem = new MenuItem(itemName, itemPrice, "Description");
            menu.addMenuItem(newItem);
            statusLabel.setText("Item added: " + itemName);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid price format");
        }
    }

    @FXML
    private void handleRemoveItem() {
        String itemName = itemNameField.getText();
        boolean success = menu.getMenuItems().removeIf(item -> item.getName().equalsIgnoreCase(itemName));
        if (success) {
            statusLabel.setText("Item removed: " + itemName);
        } else {
            statusLabel.setText("Item not found: " + itemName);
        }
    }
}