package com.domain.model;

public class Operator extends User {
    private String operatorId;

    public Operator(String username, String password) {
        super(username, password, "operator");
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