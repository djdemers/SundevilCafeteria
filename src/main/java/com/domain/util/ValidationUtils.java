package com.domain.util;

/**
 * Utility class for common validation operations.
 *
 * Purpose:
 * - Provides reusable methods for validating user inputs and data.
 * - Ensures consistent validation logic across the application.
 */
public class ValidationUtils {

    /**
     * Validates that an email address is in the correct format.
     *
     * @param email The email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Validates that a password meets the required criteria.
     *
     * @param password The password to validate.
     * @return true if the password meets the criteria, false otherwise.
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$");
    }

    /**
     * Validates that a username is not empty and contains only alphanumeric characters.
     *
     * @param username The username to validate.
     * @return true if the username is valid, false otherwise.
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.matches("^[A-Za-z0-9]+$");
    }

    /**
     * Validates that a phone number contains only digits and is of a specific length.
     *
     * @param phoneNumber The phone number to validate.
     * @return true if the phone number is valid, false otherwise.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches("^\\d{10}$");
    }

    // TODO: Add more validation methods as needed, such as:
    // - Validating address formats
    // - Checking required fields
    // - Ensuring numeric ranges
}
