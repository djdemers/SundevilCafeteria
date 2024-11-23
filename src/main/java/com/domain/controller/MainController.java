package com.domain.controller;

import com.domain.model.User;
import com.domain.exception.GlobalExceptionHandler;
import com.domain.util.NavigationUtils;
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
    private Button manageUsersButton; // Placeholder for system settings functionality.

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

    public User getUser(){
        return this.user;
    }

    /**
     * Configures buttons and UI elements based on the user's role.
     * Ensures that only appropriate actions are accessible.
     */
    private void configureUIBasedOnRole() {
        String role = user.getRole();
        if (!role.equalsIgnoreCase("MANAGER")) {
            manageMenuButton.setDisable(true); // Disable manage menu for non-managers.
            manageUsersButton.setDisable(true); // Disable system settings for non-managers.
        }
    }

    /**
     * Navigates to the Manage Menu view.
     */
    @FXML
    private void handleManageMenu() {
        navigateToView("/com/view/manager-view.fxml", "Manage Menu");
    }

    @FXML
    private void handleManageUsers() {
        navigateToView("/com/view/manage-users-view.fxml", "Manage Users");
    }

    /**
     * Navigates to a specified view.
     *
     * @param fxmlPath The path to the FXML file.
     * @param title    The title of the new stage.
     */
    private void navigateToView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Pass the user back to the target controller
            if (title.equals("Manage Menu")) {
                ManagerController controller = loader.getController();
                controller.setParentController(this); // Link back to MainController
            } else if (title.equals("Manage Users")) {
                ManageUsersController controller = loader.getController();
                controller.setParentController(this); // Link back to MainController
                controller.setUser(user);
            }

            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - " + title);
            stage.show();
        } catch (IOException e) {
            GlobalExceptionHandler.handleException(e);
        }
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

