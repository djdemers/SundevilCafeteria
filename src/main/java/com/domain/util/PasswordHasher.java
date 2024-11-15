package com.domain.util;

/**
 * Utility class to generate hashed passwords using SHA-256 with salt.
 *
 * Purpose:
 * - This class is a standalone utility for generating secure, salted hashes of passwords.
 * - Useful for testing and populating initial hashed password data in the application.
 *
 * Usage: SEE README.md FOR DETAILS ON HOW TO RUN
 *
 * Output:
 * - The plain password, its hashed version, and the generated salt.
 *
 * Example Output:
 * Plain Password: admin123
 * "hashedPassword": "dF+dC5y3bHYTEEBIMvw6jdE7T6+DXChz13HnlodioSY=",
 * "salt": "fdohYTiImaW3PGOG+P25gQ==",
 *
 * TODO:
 * - Add error handling for invalid inputs or missing dependencies.
 * - Expand functionality to support batch processing of multiple passwords.
 * - Provide an option to read passwords from a file for bulk hashing.
 */
public class PasswordHasher {

    /**
     * Main method to hash a password using SHA-256 with salt.
     *
     * @param args Command-line arguments. The first argument is the plain-text password to hash.
     */
    public static void main(String[] args) {
        // Validate input
        if (args.length < 1) {
            System.out.println("Usage: java com.domain.util.PasswordHasher <password>");
            System.exit(1);
        }

        // Generate hashed password with salt
        String plainPassword = args[0];
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(plainPassword, salt);

        // Display results
        System.out.println("Plain Password: " + plainPassword);
        System.out.println("\"hashedPassword\": " + "\"" + hashedPassword + "\",");
        System.out.println("\"salt\": " + "\"" + salt + "\",");
    }
}

