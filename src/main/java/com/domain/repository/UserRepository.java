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

public class UserRepository {
    private static final String USER_DATA_FILE = "src/main/resources/com/users.json";
    private List<User> users;
    private Gson gson = new Gson();

    public UserRepository() {
        loadUsers();
    }

    private void loadUsers() {
        try (FileReader reader = new FileReader(USER_DATA_FILE)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, userListType);
            if (users == null) {
                users = new ArrayList<>();
            }
        } catch (Exception e) {
            users = new ArrayList<>();
        }
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public void saveUsers() {
        try (FileWriter writer = new FileWriter(USER_DATA_FILE)) {
            gson.toJson(users, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
