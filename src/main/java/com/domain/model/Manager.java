package com.domain.model;

public class Manager extends User {
    private String managerId;

    public Manager(String username, String password) {
        super(username, password, "manager");

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