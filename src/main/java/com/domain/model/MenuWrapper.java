package com.domain.model;

import java.util.List;

/**
 * Wrapper class for a collection of MenuItem objects.
 * Primarily used for loading or saving a list of menu items,
 * particularly when interacting with JSON files or other data sources.
 */
public class MenuWrapper {

    /**
     * List of MenuItem objects that this wrapper holds.
     * Represents a complete menu or a subset of menu items.
     */
    private List<MenuItem> menuItems;

    /**
     * Retrieves the list of MenuItem objects.
     *
     * @return A list of MenuItem objects.
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets the list of MenuItem objects.
     *
     * @param menuItems The list of MenuItem objects to set.
     */
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}

