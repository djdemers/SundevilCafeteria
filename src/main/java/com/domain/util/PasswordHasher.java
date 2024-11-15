package com.domain.util;

import java.util.Scanner;


 //Utility class to generate BCrypt-hashed passwords with interactive input.


public class PasswordHasher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plain password: ");
        String plainPassword = scanner.nextLine();
        String hashedPassword = PasswordUtils.hashPassword(plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);
        scanner.close();
    }
}
