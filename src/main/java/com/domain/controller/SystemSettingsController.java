package com.domain.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the System Settings view.
 */
public class SystemSettingsController {

    private MainController parentController;
    @FXML
    private Button backButton; // Back button for returning to MainController


    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/main-view.fxml"));
            Scene scene = new Scene(loader.load());

            // Restore parent controller
            MainController mainController = loader.getController();
            mainController.setUser(parentController.getUser());

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - Manager Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle logging or exception display
        }
    }
}
