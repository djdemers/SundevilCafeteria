package com.domain.model;

/**
 * Represents a single menu item in the Sundevil Cafeteria system.
 * A menu item has a name, type (e.g., Breakfast, Lunch), description, and price.
 */
public class MenuItem {
    private String name;         // Name of the menu item
    private String type;         // Type of the menu item (Breakfast, Lunch, Dinner)
    private String description;  // Description of the menu item
    private double price;        // Price of the menu item

    /**
     * Constructs a MenuItem with the specified attributes.
     *
     * @param name        The name of the menu item.
     * @param type        The type of the menu item (e.g., "Breakfast", "Dinner").
     * @param description A brief description of the menu item.
     * @param price       The price of the menu item.
     */
    public MenuItem(String name, String type, String description, double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
