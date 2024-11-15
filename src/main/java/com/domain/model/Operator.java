package com.domain.model;

public class Operator extends User {
    private String operatorId;

    /**
     * Constructs an Operator with the specified username and plain-text password.
     * The password is hashed before being passed to the User constructor.
     *
     * @param username The operator's username.
     * @param plainPassword The operator's plain-text password.
     */
    public Operator(String username, String plainPassword) {
        super(username, com.domain.util.PasswordUtils.hashPassword(plainPassword), "OPERATOR");
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