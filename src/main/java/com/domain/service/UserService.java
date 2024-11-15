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

    // Additional user-related methods can be added here
}
