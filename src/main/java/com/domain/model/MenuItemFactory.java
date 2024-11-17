package com.domain.model;

/**
 * Factory class for creating instances of MenuItem.
 * This class encapsulates the creation logic for MenuItem objects,
 * providing a centralized way to create menu items with a consistent structure.
 */
public class MenuItemFactory {

    /**
     * Creates a new MenuItem instance with the specified attributes.
     *
     * @param name        The name of the menu item.
     * @param type        The type/category of the menu item
     * @param description A brief description of the menu item.
     * @param price       The price of the menu item.
     *
     * @return A new instance of MenuItem.
     */
    public static MenuItem createMenuItem(String name, String type, String description, double price) {
        return new MenuItem(name, type, description, price);
    }
}
