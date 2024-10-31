package com.domain.model;

public class MenuItemFactory {
    public static MenuItem createMenuItem(String name, String type,  String description, double price) {
        return new MenuItem(name, type, description, price);
    }
}