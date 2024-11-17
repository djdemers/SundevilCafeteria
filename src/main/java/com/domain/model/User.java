package com.domain.model;

/**
 * Represents a generic user in the system.
 */
public class User {
    private String username;
    private String hashedPassword;
    private String salt;
    private String role;

    /**
     * Default constructor required by Gson or other serialization libraries.
     */
    public User() {}

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param username       The username of the user.
     * @param hashedPassword The hashed password of the user.
     * @param salt           The salt used for hashing the password.
     * @param role           The role of the user (e.g., ADMIN, OPERATOR, CUSTOMER).
     */
    public User(String username, String hashedPassword, String salt, String role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

