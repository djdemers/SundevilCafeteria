package com.domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.domain.model.User;

public class MainApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApplication.primaryStage = primaryStage;
        showLoginScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Load Login Screen
    private void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/login-view.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();

            // Get the LoginController instance and set up the callback for successful login
            com.domain.controller.Login controller = loader.getController();
            controller.setLoginCallback(this::handleSuccessfulLogin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle successful login by redirecting based on the user's role
    private void handleSuccessfulLogin(User user) {
        switch (user.getRole().toLowerCase()) {
            case "manager":
                showManagerScreen();
                break;
            case "operator":
                showOperatorScreen();
                break;
            case "customer":
                showCustomerScreen();
                break;
            default:
                System.out.println("Role not recognized. Unable to redirect.");
        }
    }

    // Load Manager Screen
    private void showManagerScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/menu-manager-view.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Manager Dashboard");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error loading Manager UI:");
            e.printStackTrace();
        }
    }

    // Load Operator Screen
    private void showOperatorScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/order-manager-view.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Operator Dashboard");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error loading Operator UI:");
            e.printStackTrace();
        }
    }

    // Load Customer Screen (You might need to create one if not done yet)
    private void showCustomerScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/customer-view.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Customer Dashboard");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error loading Customer UI:");
            e.printStackTrace();
        }
    }
}

