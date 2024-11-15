package com.domain.util;

/**
 * Utility class to generate hashed passwords using SHA-256 with salt.
 *
 * Usage: Run this class with a password argument to get its hashed version.
 * Example: java com.domain.util.PasswordHasher admin123
 */
public class PasswordHasher {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java com.domain.util.PasswordHasher <password>");
            System.exit(1);
        }

        String plainPassword = args[0];
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(plainPassword, salt);

        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Salt: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
