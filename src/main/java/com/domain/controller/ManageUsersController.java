package com.domain.controller;

import com.domain.model.User;
import com.domain.model.UserManager;
import com.domain.service.UserService;
import com.domain.ui.CustomDialogBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ManageUsersController {

    private MainController parentController;
    @FXML
    private Button backButton;
    @FXML
    private Button addUserButton;
    @FXML
    private Button editUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    private User user;
    private UserManager userManager;
    private UserService userService;

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }

    public ManageUsersController() {
        this.userManager = UserManager.getInstance();
        this.userService = new UserService();
    }

    @FXML
    public void initialize() {
        usernameColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(0.5));
        roleColumn.prefWidthProperty().bind(usersTable.widthProperty().multiply(0.5));

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadUsers();
    }

    private void loadUsers() {
        List<User> users = userManager.getAllUsers();
        usersTable.setItems(FXCollections.observableArrayList(users));
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleAddUser() {
        String title = "Add New User";
        String message = "Input new user details.";
        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.setEditable(false);
        roleBox.setPromptText("Select Role");
        roleBox.getItems().addAll("MANAGER", "OPERATOR");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        boolean isOkPressed = CustomDialogBox.showCustomDialog(title, message, usernameLabel, usernameField, passwordLabel, passwordField, roleBox);

        if (isOkPressed) {
            String username = usernameField.getText().trim();
            String password = passwordField.getText();
            String role = roleBox.getValue();

            if (username.isEmpty() || password.isEmpty() || role == null) {
                CustomDialogBox.showError("Error", "Please fill in all fields.");
                return;
            }

            boolean success;
            if (role.equals("MANAGER")) {
                System.out.println(user.getUsername());
                System.out.println(username);
                System.out.println(password);
                success = userService.addManager(user.getUsername(), username, password);
                System.out.println(success);
            } else if (role.equals("OPERATOR")) {
                success = userService.addOperator(user.getUsername(), username, password);
            } else {
                success = false;
            }

            if (success) {
                CustomDialogBox.showInfo("Success", "User added successfully.");
                loadUsers();
            } else {
                CustomDialogBox.showError("Error", "Failed to add user.");
            }
        }
    }

    @FXML
    private void handleDeleteUser() {
        // Handle deleting an existing user
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/main-view.fxml"));
            Scene scene = new Scene(loader.load());

            MainController mainController = loader.getController();
            mainController.setUser(parentController.getUser());

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sundevil Cafeteria - Manager Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
