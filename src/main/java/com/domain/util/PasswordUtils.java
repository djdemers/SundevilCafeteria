package com.domain.util;

import org.mindrot.jbcrypt.BCrypt;

//Utility class for password hashing and verification using BCrypt.

public class PasswordUtils {

    /*
     * Hashes a plain-text password using BCrypt.
     *
     * @param plainPassword The plain-text password to hash.
      @return The BCrypt-hashed password.
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    /*
     * Verifies a plain-text password against a hashed password.
     *
     * @param plainPassword  The plain-text password to verify.
     * @param hashedPassword The BCrypt-hashed password to compare against.
     * @return true if the password matches; false otherwise.*/

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hashed password format.");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

