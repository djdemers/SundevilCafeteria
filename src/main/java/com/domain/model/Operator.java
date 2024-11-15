package com.domain.model;

import com.domain.util.PasswordUtils;

public class Operator extends User {
    private String operatorId;

    /**
     * Constructs a new Operator with a username and plain-text password.
     *
     * @param username      The username of the Operator.
     * @param plainPassword The plain-text password of the Operator.
     */
    public Operator(String username, String plainPassword) {
        // Generate a unique salt and hash the password before calling super
        this(username, PasswordUtils.hashPassword(plainPassword, PasswordUtils.generateSalt()), PasswordUtils.generateSalt());
    }

    /**
     * Overloaded constructor to create a Customer with pre-hashed password and salt.
     * Useful when loading users from persistent storage.
     *
     * @param username       The username of the Operator.
     * @param hashedPassword The already hashed password.
     * @param salt           The salt used for hashing.
     */
    public Operator(String username, String hashedPassword, String salt) {
        super(username, hashedPassword, salt, "CUSTOMER");
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public void updateOrderStatus() {
    }
}