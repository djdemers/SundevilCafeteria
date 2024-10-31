package com.domain.model;

public class Operator extends User {
    private String operatorId;

    public Operator(String username, String password, String operatorId) {
        super(username, password);
        this.operatorId = operatorId;
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