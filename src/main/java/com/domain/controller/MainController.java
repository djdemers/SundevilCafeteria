package com.domain.controller;

import com.domain.model.User;
import com.domain.exception.GlobalExceptionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Menu manageMenu;

    private User user;

    /**
     * Sets the authenticated user and updates the UI accordingly.
     *
     * @param user The authenticated user.
     */
    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
        configureMenuBasedOnRole();
    }

    /**
     * Configures menu items based on the user's role.
     */
    private void configureMenuBasedOnRole() {
        String role = user.getRole();
        if (role.equalsIgnoreCase("ADMIN")) {
            manageMenu.setVisible(true);
        } else if (role.equalsIgnoreCase("OPERATOR")) {
            // Operators can manage orders but not menu items or users
            manageMenu.setVisible(true);
            // Hide specific menu items if needed
        } else {
            // Customers or other roles have limited access
            manageMenu.setVisible(false);
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/login-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - Login");
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleManageOrders() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/operator-view.fxml"));
            Scene scene = new Scene(loader.load());

            OperatorController operatorController = loader.getController();
            // Pass necessary data if needed

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Orders");
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    @FXML
    private void handleManageMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/manager-view.fxml"));
            Scene scene = new Scene(loader.load());

            ManagerController managerController = loader.getController();
            // Pass necessary data if needed

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Menu");
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    @FXML
    private void handleManageUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/customer-view.fxml"));
            Scene scene = new Scene(loader.load());

            CustomerController customerController = loader.getController();
            // Pass necessary data if needed

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Users");
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}
