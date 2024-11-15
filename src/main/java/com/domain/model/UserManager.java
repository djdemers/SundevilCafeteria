package com.domain.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

/**
 * Singleton class to manage user authentication and user data with persistent storage (user.json).
 */
public class UserManager {

    private static UserManager instance;
    private Map<String, User> users;
    private static final String USER_FILE_PATH = "src/main/resources/users.json";
    private static final Gson gson = new Gson();

    /**
     * Private constructor to enforce Singleton pattern.
     * Initializes the user repository by loading users from user.json.
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
        if (user != null && password.equals(user.getHashedPassword())) {
            return user;
        }
        return null; // Authentication failed
    }

    /**
     * Adds a new user to the user repository and persists the change to user.json.
     *
     * @param user The User object to be added.
     * @return true if the user was added successfully; false if the user already exists.
     */
    public boolean addUser(User user) {
        if (user != null && !users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            saveUsers();
            return true;
        }
        return false;
    }

    /**
     * Removes an existing user from the user repository and persists the change to user.json.
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
     * Updates an existing user's information and persists the change to user.json.
     *
     * @param user The User object with updated information.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateUser(User user) {
        if (user != null && users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            saveUsers();
            return true;
        }
        return false;
    }

    /**
     * Loads users from the user.json file into the users map.
     */
    private void loadUsers() {
        try (InputStream is = getClass().getResourceAsStream("/users.json")) {
            if (is == null) {
                System.err.println("user.json not found in resources.");
                return;
            }
            Reader reader = new InputStreamReader(is);
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> userList = gson.fromJson(reader, userListType);
            reader.close();

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
     * Saves the current users map to the user.json file.
     */
    private void saveUsers() {
        try {
            Path path = Paths.get(USER_FILE_PATH);
            Writer writer = Files.newBufferedWriter(path);
            List<User> userList = new ArrayList<>(users.values());
            gson.toJson(userList, writer);
            writer.close();
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
