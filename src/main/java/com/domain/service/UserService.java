package com.domain.service;

import com.domain.model.User;
import com.domain.model.UserManager;

import java.util.Optional;

/**
 * Service layer for user-related operations.
 */
public class UserService {
    private UserManager userManager;

    public UserService() {
        this.userManager = UserManager.getInstance();
    }

    /**
     * Authenticates a user based on username and password.
     *
     * @param username The username.
     * @param password The plain-text password.
     * @return An Optional containing the User if authentication is successful; otherwise, empty.
     */
    public Optional<User> authenticate(String username, String password) {
        User user = userManager.authenticateUser(username, password);
        return Optional.ofNullable(user);
    }

    /**
     * Checks if a username or email is already taken.
     *
     * @param username The username to check.
     * @param email The email to check.
     * @return True if either is taken; false otherwise.
     */
    public boolean isUsernameOrEmailTaken(String username, String email) {
        return userManager.isUsernameOrEmailTaken(username, email);
    }

    /**
     * Registers a new customer.
     *
     * @param username      The username of the customer.
     * @param email         The email of the customer.
     * @param plainPassword The plain-text password of the customer.
     * @return True if registration is successful; false otherwise.
     */
    public boolean registerCustomer(String username, String email, String plainPassword) {
        // Validate input parameters
        if (username == null || email == null || plainPassword == null ||
                username.isBlank() || email.isBlank() || plainPassword.isBlank()) {
            System.err.println("Invalid input: All fields must be filled.");
            return false;
        }

        // Check if username or email is already taken
        if (userManager.isUsernameOrEmailTaken(username, email)) {
            System.err.println("Registration failed: Username or email is already in use.");
            return false;
        }

        // Register the new customer
        return userManager.registerUser(username, email, plainPassword, "CUSTOMER");
    }

}
