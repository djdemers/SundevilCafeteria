package com.domain.model;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static UserManager instance;
    private Map<String, User> users;

    private UserManager() {
        users = new HashMap<>();
        // Pre-populate some test users
        users.put("admin", new Manager("admin", "password"));
        users.put("operator1", new Operator("operator1", "password"));
        users.put("customer1", new Customer("customer1", "password"));
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Authentication failed
    }
}

