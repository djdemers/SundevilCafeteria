package com.domain.model;

public class Manager {
    private String managerId;
    private String name;

    public Manager(String managerId, String name) {
        this.managerId = managerId;
        this.name = name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateMenu(Menu menu) {
    }
}