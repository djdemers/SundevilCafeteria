package com.domain.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.domain.util.PasswordUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

/**
 * Singleton class to manage user authentication and user data with persistent storage (users.json).
 */
public class UserManager {

    private static UserManager instance;
    private Map<String, User> users;
    private static final String USER_FILE_PATH = "src/main/resources/users.json";
    private static final Gson gson = new Gson();

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes the user repository by loading users from users.json.
     */
    private UserManager() {
        users = new HashMap<>();
        loadUsers();
    }

    /**
     * Provides the global point of access to the UserManager instance.
     *
     * @return The singleton instance of UserManager.
     */
    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * Authenticates a user based on username and plain-text password.
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The plain-text password provided by the user.
     * @return The authenticated User object if credentials are valid; otherwise, null.
     */
    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && PasswordUtils.verifyPassword(password, user.getHashedPassword(), user.getSalt())) {
            return user;
        }
        return null; // Authentication failed
    }

    /**
     * Checks if a username or email is already taken.
     *
     * @param username The username to check.
     * @param email    The email to check.
     * @return True if either username or email is taken; false otherwise.
     */
    public boolean isUsernameOrEmailTaken(String username, String email) {
        for (User user : users.values()) {
            // Check for username match
            if (user.getUsername().equals(username)) {
                return true;
            }

            // Check for email match only if the user is a Customer
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                if (customer.getEmail().equals(email)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Registers a new customer.
     *
     * @param username      The customer's username.
     * @param email         The customer's email address.
     * @param plainPassword The customer's plain-text password.
     * @return True if registration is successful; false otherwise.
     */
    public boolean registerCustomer(String username, String email, String plainPassword) {
        if (isUsernameOrEmailTaken(username, email)) {
            return false; // Username or email already taken
        }

        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(plainPassword, salt);

        Customer customer = new Customer(username, hashedPassword, salt, email, null, null); // null for phone/address
        users.put(username, customer);
        saveUsers();
        return true;
    }

    /**
     * Admin adds a manager or operator.
     *
     * @param adminUsername The username of the admin.
     * @param newUser       The new manager or operator user to be added.
     * @return True if the admin has privileges and the user is added; false otherwise.
     */
    public boolean addManagerOrOperator(String adminUsername, User newUser) {
        User admin = users.get(adminUsername);
        if (admin != null && "ADMIN".equals(admin.getRole())) {
            if (!users.containsKey(newUser.getUsername())) {
                users.put(newUser.getUsername(), newUser);
                saveUsers();
                return true;
            }
        }
        return false; // Either not an admin or username already exists
    }

    /**
     * Updates an existing user's password and persists the change to users.json.
     *
     * @param username    The username of the user to update.
     * @param newPassword The new plain-text password.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateUserPassword(String username, String newPassword) {
        User user = users.get(username);
        if (user != null && newPassword != null) {
            String newSalt = PasswordUtils.generateSalt();
            String newHashedPassword = PasswordUtils.hashPassword(newPassword, newSalt);
            user.setSalt(newSalt);
            user.setHashedPassword(newHashedPassword);
            saveUsers();
            return true;
        }
        return false;
    }

    /**
     * Loads users from the users.json file into the users map.
     */
    private void loadUsers() {
        Path path = Paths.get(USER_FILE_PATH);
        if (!Files.exists(path)) {
            System.err.println("users.json not found. A new one will be created upon adding users.");
            return;
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            Type userListType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> userList = gson.fromJson(reader, userListType);

            if (userList != null) {
                for (Map<String, Object> userMap : userList) {
                    String username = (String) userMap.get("username");
                    String hashedPassword = (String) userMap.get("hashedPassword");
                    String salt = (String) userMap.get("salt");
                    String role = (String) userMap.get("role");

                    User user;
                    if ("CUSTOMER".equals(role)) {
                        String email = (String) userMap.get("email");
                        user = new Customer(username, hashedPassword, salt, email, null, null);
                    } else {
                        user = new User(username, hashedPassword, salt, role);
                    }

                    users.put(username, user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

    /**
     * Saves the current users map to the users.json file.
     */
    private void saveUsers() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(USER_FILE_PATH))) {
            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            List<User> userList = new ArrayList<>(users.values());
            prettyGson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Replace with proper logging
            // Handle exception (e.g., notify admin, retry saving, etc.)
        }
    }

    /**
     * Retrieves all users as a list.
     *
     * @return List of all User objects.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    /**
     * Registers a new user with the given details.
     *
     * @param username      The username of the user.
     * @param email         The email of the user.
     * @param plainPassword The plain-text password of the user.
     * @param role          The role of the user (e.g., CUSTOMER).
     * @return True if registration is successful; false otherwise.
     */
    public boolean registerUser(String username, String email, String plainPassword, String role) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }

        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(plainPassword, salt);

        // Create a new user instance (for customers, use Customer class)
        User newUser;
        if ("CUSTOMER".equals(role)) {
            newUser = new Customer(username, hashedPassword, salt, email, null, null);
        } else {
            newUser = new User(username, hashedPassword, salt, role);
        }

        users.put(username, newUser);
        saveUsers();
        return true;
    }

}
