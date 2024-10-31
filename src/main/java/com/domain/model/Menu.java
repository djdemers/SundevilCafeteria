// Menu Class with getMenuItemsByType Method
package com.domain.model;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private static Menu instance;
    private List<MenuItem> menuItems;

    private Menu() {
        menuItems = new ArrayList<>();
        initializeMenu();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private void initializeMenu() {
        // Load the menu items from a JSON file
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/menu_items.json"))) {
            Gson gson = new Gson();
            MenuWrapper menuWrapper = gson.fromJson(reader, MenuWrapper.class);
            menuItems = menuWrapper.getMenuItems();
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to default menu items if JSON loading fails
            menuItems.add(new MenuItem("Pancakes", "Breakfast", "Fluffy pancakes with syrup", 5.99));
            menuItems.add(new MenuItem("Cheeseburger", "Lunch", "Juicy cheeseburger with lettuce", 8.99));
            menuItems.add(new MenuItem("Steak", "Dinner", "Grilled steak with mashed potatoes", 19.99));
            menuItems.add(new MenuItem("Coffee", "Beverages", "Hot brewed coffee", 2.49));
        }
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuItem> getMenuItemsByType(String type) {
        return menuItems.stream()
                .filter(item -> item.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public boolean removeMenuItem(String name) {
        return menuItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }
    public boolean editMenuItemDescription(String name, String newDescription) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    public boolean editMenuItemPrice(String name, double newPrice) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }
}
