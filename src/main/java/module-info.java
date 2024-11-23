module com.domain {

    // Required JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;

    // Required external libraries
    requires com.google.gson;
    requires org.slf4j;

    // Exported packages
    exports com.domain;

    // Open packages to JavaFX for reflection (FXML)
    opens com.domain to javafx.fxml;

    // Open controller package to JavaFX for FXML injection
    opens com.domain.controller to javafx.fxml;

    // Open model package to Gson for JSON serialization/deserialization
    opens com.domain.model to com.google.gson, javafx.base;

    // Open additional controller packages if necessary
    opens com.domain.controller.command to javafx.fxml;
}


