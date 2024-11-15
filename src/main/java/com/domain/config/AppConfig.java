package com.domain.config;

/**
 * Configuration class for the application.
 *
 * This class is intended to hold application-wide configurations,
 * such as constants, environment-specific settings, or initialization
 * logic. This could include database configuration, file paths, or
 * third-party service keys.
 */
public class AppConfig {

    // TODO: Define static constants or methods to provide access to configuration values.

    /**
     * Example: Add constants for file paths.
     */
    public static final String USERS_JSON_PATH = "src/main/resources/users.json";
    public static final String MENU_ITEMS_JSON_PATH = "src/main/resources/menu_items.json";

    /**
     * Example: Add methods to load properties or settings from a configuration file.
     */
    public static String getProperty(String key) {
        // Placeholder for logic to fetch properties.
        return System.getProperty(key);
    }

    // Future ideas:
    // - Database connection configurations if transitioning from JSON to a database.
    // - Initialization logic for third-party libraries or APIs.
    // - Environment-specific toggles for features.
}
