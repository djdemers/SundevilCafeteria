package com.domain.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for password hashing and verification using SHA-256 with salt.
 */
public class PasswordUtils {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16; // 16 bytes = 128 bits

    /**
     * Generates a random salt using SecureRandom.
     *
     * @return The generated salt as a Base64-encoded string.
     */
    public static String generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes a password with the provided salt using SHA-256.
     *
     * @param password The plain-text password.
     * @param salt     The salt as a Base64-encoded string.
     * @return The hashed password as a Base64-encoded string.
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(Base64.getDecoder().decode(salt)); // Decode the salt
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm not found.", e);
        }
    }

    /**
     * Verifies a plain-text password against the stored hash and salt.
     *
     * @param password       The plain-text password to verify.
     * @param storedHash     The stored hashed password (Base64-encoded).
     * @param storedSalt     The stored salt (Base64-encoded).
     * @return true if the password matches; false otherwise.
     */
    public static boolean verifyPassword(String password, String storedHash, String storedSalt) {
        String hashedInput = hashPassword(password, storedSalt);
        return hashedInput.equals(storedHash);
    }
}


