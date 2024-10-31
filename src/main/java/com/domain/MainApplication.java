package com.domain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/menu-manager-view.fxml"));
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/com/view/order-manager-view.fxml"));
        //FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/view/user-manager-view.fxml"));
        Parent root = loader1.load();
        primaryStage.setTitle("Menu Management");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
