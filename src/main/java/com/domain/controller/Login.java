package com.domain.controller;

import com.domain.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import com.domain.model.UserManager;

import java.util.function.Consumer;

public class Login {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label loginStatusLabel;

    private UserManager userManager;

    // Callback to be used for successful login redirection
    private Consumer<User> loginCallback;

    public Login() {
        userManager = UserManager.getInstance(); // Get the singleton instance of UserManager
    }

    // This method is used to set the callback from MainApplication for handling redirections
    public void setLoginCallback(Consumer<User> loginCallback) {
        this.loginCallback = loginCallback;
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginStatusLabel.setText("Username and Password must not be empty.");
            return;
        }

        User user = userManager.authenticateUser(username, password);
        if (user != null) {
            loginStatusLabel.setText("Login successful. Welcome " + user.getRole() + "!");
            // Use the callback to pass the authenticated user back to MainApplication
            if (loginCallback != null) {
                loginCallback.accept(user);
            }
        } else {
            loginStatusLabel.setText("Invalid Username or Password.");
        }
    }
}

