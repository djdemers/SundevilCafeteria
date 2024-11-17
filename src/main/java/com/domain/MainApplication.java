package com.domain;

import com.domain.exception.GlobalExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point for the Sundevil Cafeteria application.
 *
 * Responsibilities:
 * - Initializes and launches the JavaFX application.
 * - Loads the initial login view from the FXML file.
 * - Handles exceptions gracefully using the GlobalExceptionHandler.
 */
public class MainApplication extends Application {

    /**
     * Starts the JavaFX application.
     * Loads the login view and sets up the primary stage.
     *
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Load the login view from the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 600);

            // Configure the primary stage
            stage.setTitle("Sundevil Cafeteria - Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // Handle any exceptions during initialization
            GlobalExceptionHandler.handleException(e);
        }
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}

