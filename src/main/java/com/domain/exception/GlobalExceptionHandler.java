package com.domain.exception;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static void handleException(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Application Error");
        alert.setHeaderText("An unexpected error occurred.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();

        logger.error("An exception occurred", e);
    }
}

