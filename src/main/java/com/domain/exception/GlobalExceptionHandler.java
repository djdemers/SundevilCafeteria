package com.domain.exception;

import javafx.scene.control.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global exception handler for the application.
 *
 * This class centralizes error handling and provides user feedback for unexpected errors.
 * It utilizes JavaFX Alert for GUI-based error reporting and SLF4J for logging.
 */
public class GlobalExceptionHandler {

    // Logger for recording exceptions in logs
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles exceptions by:
     * 1. Displaying an error alert to the user.
     * 2. Logging the exception for debugging and tracking purposes.
     *
     * @param e The exception to handle.
     */
    public static void handleException(Exception e) {
        // Create a JavaFX Alert dialog to notify the user about the error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Application Error");
        alert.setHeaderText("An unexpected error occurred.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();

        // Log the exception with its stack trace for debugging purposes
        logger.error("An exception occurred", e);
    }
}


