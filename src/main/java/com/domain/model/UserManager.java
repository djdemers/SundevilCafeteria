package com.domain.model;

import com.google.gson.Gson;
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


    public boolean addUser(String username, String password, String role) {
        if (username == null || password == null || role == null || users.containsKey(username)) {
            return false;
        }
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        User user = new User(username, hashedPassword, salt, role);
        users.put(username, user);
        saveUsers();
        return true;
    }


    /**
     * Removes an existing user from the user repository and persists the change to users.json.
     *
     * @param username The username of the user to be removed.
     * @return true if the user was successfully removed; false otherwise.
     */
    public boolean removeUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            saveUsers();
            return true;
        }
        return false;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object if found; otherwise, null.
     */
    public User getUser(String username) {
        return users.get(username);
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
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> userList = gson.fromJson(reader, userListType);

            if (userList != null) {
                for (User user : userList) {
                    users.put(user.getUsername(), user);
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
            List<User> userList = new ArrayList<>(users.values());
            gson.toJson(userList, writer);
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
}
