package com.domain.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.domain.exception.GlobalExceptionHandler;

public class NavigationUtils {

    /**
     * Navigates to the login screen.
     *
     * @param currentStage The current stage to replace with the login scene.
     */
    public static void navigateToLogin(Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtils.class.getResource("/com/view/login-view.fxml"));
            Scene loginScene = new Scene(loader.load(), 400, 600);
            currentStage.setScene(loginScene);
            currentStage.setTitle("Sundevil Cafeteria - Login");
            currentStage.show();
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }
}
