package com.domain;

import com.domain.model.UserManager;

public class AddUserExample {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();

        boolean isAdded = userManager.addUser("newCustomer", "password123", "CUSTOMER");
        if (isAdded) {
            System.out.println("Customer 'newCustomer' added successfully.");
        } else {
            System.out.println("Failed to add customer 'newCustomer'. It may already exist.");
        }
    }
}
