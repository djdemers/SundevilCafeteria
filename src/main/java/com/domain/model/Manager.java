package com.domain.model;

public class Manager extends User {
    private String managerId;

    /**
     * Constructs a Manager with the specified username and plain-text password.
     * The password is hashed before being passed to the User constructor.
     *
     * @param username The manager's username.
     * @param plainPassword The manager's plain-text password.
     */
    public Manager(String username, String plainPassword) {
        super(username, com.domain.util.PasswordUtils.hashPassword(plainPassword), "ADMIN");
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