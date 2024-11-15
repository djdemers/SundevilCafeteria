package com.domain.service;

import com.domain.model.User;
import com.domain.model.UserManager;

import java.util.Optional;

/**
 * Service layer for user-related operations.
 *
 * The UserService class acts as an intermediary between the controller
 * layer and the UserManager model. It provides methods to handle user-related
 * functionality, such as authentication, registration, and validation.
 *
 * Purpose:
 * - Encapsulate business logic related to user management.
 * - Promote reuse of user-related operations.
 * - Decouple the controller and model layers.
 *
 * TODO:
 * - Add role-based user registration for managers and operators.
 * - Implement password strength validation during registration.
 * - Add support for user profile updates.
 */
public class UserService {
    private UserManager userManager;

    /**
     * Constructor to initialize the service with the singleton instance of UserManager.
     */
    public UserService() {
        this.userManager = UserManager.getInstance();
    }

    /**
     * Authenticates a user based on username and password.
     *
     * This method validates user credentials and retrieves the corresponding user if
     * authentication is successful.
     *
     * @param username The username.
     * @param password The plain-text password.
     *
     * @return An Optional containing the User if authentication is successful; otherwise, empty.
     *
     */
    public Optional<User> authenticate(String username, String password) {
        User user = userManager.authenticateUser(username, password);
        return Optional.ofNullable(user);
    }

    /**
     * Checks if a username or email is already taken.
     *
     * This method is used during registration to ensure unique user accounts.
     *
     * @param username The username to check.
     * @param email    The email to check.
     *
     * @return True if either the username or email is taken; false otherwise.
     *
     */
    public boolean isUsernameOrEmailTaken(String username, String email) {
        return userManager.isUsernameOrEmailTaken(username, email);
    }

    /**
     * Registers a new customer.
     *
     * This method validates input parameters, checks for duplicate accounts,
     * and registers the new customer if all conditions are met.
     *
     * @param username      The username of the customer.
     * @param email         The email of the customer.
     * @param plainPassword The plain-text password of the customer.
     *
     * @return True if registration is successful; false otherwise.
     *
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

    /**
     * TODO: Add support for manager and operator registration by admin.
     * Example:
     * - Validate admin privileges before registering managers or operators.
     * - Ensure unique usernames and emails for new users.
     */
}

