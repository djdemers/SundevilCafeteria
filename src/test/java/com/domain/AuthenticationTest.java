package com.domain;

import com.domain.model.User;
import com.domain.model.UserManager;

public class AuthenticationTest {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();

        // Test Admin Login
        User admin = userManager.authenticateUser("admin", "admin123");
        if (admin != null) {
            System.out.println("Admin authenticated successfully.");
        } else {
            System.out.println("Admin authentication failed.");
        }

        // Test Operator Login
        User operator = userManager.authenticateUser("operator1", "operator123");
        if (operator != null) {
            System.out.println("Operator authenticated successfully.");
        } else {
            System.out.println("Operator authentication failed.");
        }

        // Test Customer Login
        User customer = userManager.authenticateUser("customer1", "customer123");
        if (customer != null) {
            System.out.println("Customer authenticated successfully.");
        } else {
            System.out.println("Customer authentication failed.");
        }

        // Test Failed Login
        User invalid = userManager.authenticateUser("admin", "wrongpassword");
        if (invalid != null) {
            System.out.println("Invalid login authenticated (Unexpected).");
        } else {
            System.out.println("Invalid login failed as expected.");
        }
    }
}
