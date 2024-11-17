package com.domain.repository;

import com.domain.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for managing user-related data.
 *
 * This class provides CRUD operations for user data and handles
 * persistence using a JSON file as the underlying storage.
 *
 * Features:
 * - Load users from a JSON file.
 * - Save users to the JSON file.
 * - Retrieve users by username.
 * - Add new users.
 *
 * Advantages:
 * - Separates data management logic from the business logic in other classes.
 * - Provides a single source of truth for user-related operations.
 *
 * TODO:
 * - Add methods for updating and deleting users if required.
 * - Implement validation for duplicate usernames or invalid user data.
 * - Handle file paths dynamically for portability.
 */
public class UserRepository {
    private static final String USER_DATA_FILE = "src/main/resources/com/users.json"; // Path to JSON file
    private List<User> users; // In-memory user list
    private Gson gson = new Gson(); // Gson instance for JSON serialization/deserialization

    /**
     * Constructor initializes the user repository by loading data from the JSON file.
     */
    public UserRepository() {
        loadUsers();
    }

    /**
     * Loads users from the JSON file into memory.
     *
     * - If the file exists, the data is deserialized into the `users` list.
     * - If the file does not exist or is empty, initializes an empty list.
     */
    private void loadUsers() {
        try (FileReader reader = new FileReader(USER_DATA_FILE)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
            if (users == null) { // Handle case where JSON is valid but empty
                users = new ArrayList<>();
            }
        } catch (Exception e) {
            users = new ArrayList<>(); // Fallback for missing or corrupted file
        }
    }

    /**
     * Finds a user by their username.
     *
     * - Searches the in-memory list for a user with a matching username.
     * - Ignores case during the search.
     *
     * @param username The username to search for.
     * @return An Optional containing the user if found, otherwise an empty Optional.
     */
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    /**
     * Adds a new user to the repository and saves the change to the JSON file.
     *
     * @param user The user to add.
     */
    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    /**
     * Saves the current list of users to the JSON file.
     *
     * - Serializes the in-memory `users` list into JSON format.
     * - Writes the JSON to the file specified in `USER_DATA_FILE`.
     */
    public void saveUsers() {
        try (FileWriter writer = new FileWriter(USER_DATA_FILE)) {
            gson.toJson(users, writer);
        } catch (Exception e) {
            e.printStackTrace(); // Log or handle the exception
        }
    }

    // Potential additions:
    // - Update user information
    // - Delete a user
}
