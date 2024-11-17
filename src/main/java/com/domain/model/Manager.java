package com.domain.model;

import com.domain.util.PasswordUtils;

public class Manager extends User {
    private String managerId;

    /**
     * Constructs a new Manager with a username and plain-text password.
     *
     * @param username      The username of the Manager.
     * @param plainPassword The plain-text password of the Manager.
     */
    public Manager(String username, String plainPassword) {
        // Generate a unique salt and hash the password before calling super
        this(username, PasswordUtils.hashPassword(plainPassword, PasswordUtils.generateSalt()), PasswordUtils.generateSalt());
    }

    /**
     * Overloaded constructor to create a Manager with pre-hashed password and salt.
     * Useful when loading users from persistent storage.
     *
     * @param username       The username of the Manager.
     * @param hashedPassword The already hashed password.
     * @param salt           The salt used for hashing.
     */
    public Manager(String username, String hashedPassword, String salt) {
        super(username, hashedPassword, salt, "CUSTOMER");
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void updateMenu(Menu menu) {
    }
}