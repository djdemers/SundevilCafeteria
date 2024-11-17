// Menu Class to manage menu items in the application
package com.domain.model;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class representing the menu of the Sundevil Cafeteria.
 * Manages menu items such as adding, removing, editing, and retrieving items by type or name.
 */
public class Menu {
    private static Menu instance; // Singleton instance of Menu
    private List<MenuItem> menuItems; // List of all menu items

    /**
     * Private constructor to enforce the Singleton pattern.
     * Initializes the menu by loading items from a JSON file or falling back to default items.
     */
    private Menu() {
        menuItems = new ArrayList<>();
        initializeMenu();
    }

    /**
     * Provides global access to the singleton instance of Menu.
     *
     * @return The singleton instance of the Menu.
     */
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    /**
     * Initializes the menu items by loading them from a JSON file.
     * If loading fails, fallback to predefined default menu items.
     */
    private void initializeMenu() {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/menu_items.json"))) {
            Gson gson = new Gson();
            MenuWrapper menuWrapper = gson.fromJson(reader, MenuWrapper.class);
            menuItems = menuWrapper.getMenuItems();
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback default menu items
            menuItems.add(new MenuItem("Pancakes", "Breakfast", "Fluffy pancakes with syrup", 5.99));
            menuItems.add(new MenuItem("Cheeseburger", "Lunch", "Juicy cheeseburger with lettuce", 8.99));
            menuItems.add(new MenuItem("Steak", "Dinner", "Grilled steak with mashed potatoes", 19.99));
            menuItems.add(new MenuItem("Coffee", "Beverages", "Hot brewed coffee", 2.49));
        }
    }

    /**
     * Retrieves the complete list of menu items.
     *
     * @return A list of all menu items.
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Retrieves menu items filtered by type (e.g., Breakfast, Lunch).
     *
     * @param type The type of menu items to retrieve.
     * @return A list of menu items matching the specified type.
     */
    public List<MenuItem> getMenuItemsByType(String type) {
        return menuItems.stream()
                .filter(item -> item.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific menu item by its name.
     *
     * @param name The name of the menu item to retrieve.
     * @return The menu item if found; otherwise, null.
     */
    public MenuItem getMenuItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null; // Return null if no item is found
    }

    /**
     * Adds a new menu item to the menu.
     *
     * @param item The menu item to be added.
     */
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    /**
     * Removes a menu item by its name.
     *
     * @param name The name of the menu item to remove.
     * @return True if the item was removed; otherwise, false.
     */
    public boolean removeMenuItem(String name) {
        return menuItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    /**
     * Edits the description of a menu item.
     *
     * @param name The name of the menu item to edit.
     * @param newDescription The new description for the item.
     * @return True if the item was updated; otherwise, false.
     */
    public boolean editMenuItemDescription(String name, String newDescription) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    /**
     * Edits the price of a menu item.
     *
     * @param name The name of the menu item to edit.
     * @param newPrice The new price for the item.
     * @return True if the item was updated; otherwise, false.
     */
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

