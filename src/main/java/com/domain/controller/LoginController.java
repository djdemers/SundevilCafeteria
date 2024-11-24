package com.domain.controller;

import com.domain.exception.GlobalExceptionHandler;
import com.domain.model.Customer;
import com.domain.model.User;
import com.domain.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
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

    @FXML
    private TextField signUpUsernameField;

    @FXML
    private TextField signUpEmailField;

    @FXML
    private PasswordField signUpPasswordField;

    @FXML
    private Region dummyRegion;

    @FXML
    private Label signUpErrorLabel;

    private UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    public void initialize() {
        if (dummyRegion != null) {
            dummyRegion.sceneProperty().addListener((observable, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                        if (newWindow != null) {
                            dummyRegion.requestFocus();
                        }
                    });
                }
            });
        }
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

    @FXML
    private void handleSignUp() {
        String username = signUpUsernameField.getText().trim();
        String email = signUpEmailField.getText().trim();
        String password = signUpPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            signUpErrorLabel.setText("All fields are required.");
            logger.warn("Sign-Up attempt with missing fields.");
            return;
        }

        if (userService.isUsernameOrEmailTaken(username, email)) {
            signUpErrorLabel.setText("Username or email already exists.");
            logger.warn("Sign-Up attempt with existing username or email '{}'.", username);
            return;
        }

        try {
            boolean success = userService.registerCustomer(username, email, password);
            if (success) {
                signUpErrorLabel.setText("Sign-Up successful!");
                logger.info("New customer '{}' registered successfully.", username);
            } else {
                signUpErrorLabel.setText("Sign-Up failed. Please try again.");
                logger.error("Sign-Up failed for username '{}'.", username);
            }
        } catch (Exception e) {
            logger.error("Exception during Sign-Up for username '{}'.", username, e);
            GlobalExceptionHandler.handleException(e);
        }
    }

    private void loadMainView(User user) {
        try {
            String viewPath;
            switch (user.getRole().toUpperCase()) {
                case "MANAGER":
                    viewPath = "/com/view/main-view.fxml";
                    break;
                case "OPERATOR":
                    viewPath = "/com/view/operator-view.fxml";
                    break;
                case "CUSTOMER":
                    viewPath = "/com/view/customer-view.fxml";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown role: " + user.getRole());
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            Scene scene = new Scene(loader.load());

            // Pass the user to the role-specific controller
            Object controller = loader.getController();
            if (controller instanceof MainController) {
                ((MainController) controller).setUser(user);
            } else if (controller instanceof CustomerController) {
                if (user instanceof Customer) {
                    ((CustomerController) controller).setCustomer((Customer) user);
                } else {
                    throw new IllegalArgumentException("Invalid user type for CustomerController: " + user.getClass());
                }
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - " + user.getRole());
            stage.show();
            logger.info("Loaded view for user '{}' with role '{}'.", user.getUsername(), user.getRole());
        } catch (IOException e) {
            logger.error("Failed to load view for user '{}' with role '{}'.", user.getUsername(), user.getRole(), e);
            GlobalExceptionHandler.handleException(e);
        }
    }
}




