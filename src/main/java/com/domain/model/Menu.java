package com.domain.model;

import java.util.ArrayList;
import java.util.List;

// Menu Class (Singleton Pattern)
public class Menu {
    private static Menu instance = null;
    private List<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    // Public method to provide access to the single instance
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menuItems.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}