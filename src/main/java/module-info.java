module com.domain {

    //requires javafx.web;

    //requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
    //requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    exports com.domain;
    opens com.domain to javafx.fxml;
    opens com.domain.controller to javafx.fxml;
    opens com.domain.model to com.google.gson;
    //opens com.domain.model to javafx.fxml;

    //exports com.domain.ui;
    //exports com.domain.controller;
    //exports com.domain.model;
}
