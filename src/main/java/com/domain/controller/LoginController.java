package com.domain.controller;

import com.domain.model.User;
import com.domain.service.UserService;
import com.domain.exception.GlobalExceptionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both username and password.");
            logger.warn("Login attempt with empty username or password.");
            return;
        }

        try {
            Optional<User> userOpt = userService.authenticate(username, password);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                logger.info("User '{}' authenticated successfully as '{}'.", username, user.getRole());
                loadMainView(user);
            } else {
                errorLabel.setText("Invalid username or password.");
                logger.warn("Failed login attempt for username '{}'.", username);
            }
        } catch (Exception e) {
            logger.error("Exception during authentication for username '{}'.", username, e);
            GlobalExceptionHandler.handleException(e);
        }
    }

    private void loadMainView(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/main-view.fxml"));
            Scene scene = new Scene(loader.load());

            // Pass the user to the main controller
            MainController mainController = loader.getController();
            mainController.setUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - Main");
            stage.show();
            logger.info("Main view loaded for user '{}'.", user.getUsername());
        } catch (IOException e) {
            logger.error("Failed to load main view for user '{}'.", user.getUsername(), e);
            GlobalExceptionHandler.handleException(e);
        }
    }
}




