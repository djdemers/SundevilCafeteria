package com.domain.model;

public class MenuItemFactory {
    public static MenuItem createMenuItem(String name, double price, String description) {
        return new MenuItem(name, price, description);
    }
}