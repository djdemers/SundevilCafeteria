package com.domain;

import com.domain.exception.GlobalExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/view/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Sundevil Cafeteria - Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            GlobalExceptionHandler.handleException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

