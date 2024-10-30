package com.domain.model;

public class Operator {
    private String operatorId;
    private String name;

    public Operator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateOrderStatus() {
    }
}