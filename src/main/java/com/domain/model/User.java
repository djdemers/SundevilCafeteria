package com.domain.model;

public class User {
    private String username;
    private String hashedPassword;
    private String role;

    /**
     * Constructs a User with the specified username, hashed password, and role.
     *
     * @param username The user's username.
     * @param hashedPassword The user's hashed password.
     * @param role The role of the user (e.g., ADMIN, OPERATOR, CUSTOMER).
     */
    public User(String username, String hashedPassword, String role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


