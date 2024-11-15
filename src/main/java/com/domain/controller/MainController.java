package com.domain.controller;

import com.domain.model.User;
import com.domain.exception.GlobalExceptionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Controller for the main-view.fxml.
 * Handles role-based navigation and interaction for managers.
 */
public class MainController {

    @FXML
    private Label welcomeLabel; // Displays a welcome message for the user.

    @FXML
    private Button manageMenuButton; // Button to navigate to menu management.

    @FXML
    private Button systemSettingsButton; // Placeholder for system settings functionality.

    @FXML
    private Button logoutButton; // Button to log out and return to the login view.

    private User user; // Holds the currently authenticated user.

    /**
     * Sets the authenticated user and updates the UI accordingly.
     *
     * @param user The authenticated user.
     */
    public void setUser(User user) {
        this.user = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + "!");
        configureUIBasedOnRole();
    }

    /**
     * Configures buttons and UI elements based on the user's role.
     * Ensures that only appropriate actions are accessible.
     */
    private void configureUIBasedOnRole() {
        String role = user.getRole();
        if (!role.equalsIgnoreCase("MANAGER")) {
            manageMenuButton.setDisable(true); // Disable manage menu for non-managers.
            systemSettingsButton.setDisable(true); // Disable system settings for non-managers.
        }
    }

    /**
     * Handles navigation to the Manage Menu view.
     * Accessible only to users with the "MANAGER" role.
     */
    @FXML
    private void handleManageMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/manager-view.fxml"));
            Scene scene = new Scene(loader.load());

            ManagerController managerController = loader.getController();
            // Pass user information if necessary (e.g., to log actions).

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manage Menu");
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    /**
     * Placeholder method for handling system settings.
     * This can be implemented to open a new settings view.
     */
    @FXML
    private void handleSystemSettings() {
        // TODO: Implement system settings functionality.
        System.out.println("System Settings functionality is under construction.");
    }

    /**
     * Logs out the user and navigates back to the login view.
     */
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
}

