// SystemSettings Class
package com.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the system's configurable settings.
 * This class allows for the management of settings like order processing preferences,
 * menu permissions, or other system-level configurations.
 *
 * Provides methods to adjust and retrieve settings. Used in conjunction with the
 * SettingsProxy for role-based access control (RBAC).
 */
public class SystemSettings {
    private Map<String, String> settings; // Stores the system settings as key-value pairs

    /**
     * Constructor initializes default system settings.
     */
    public SystemSettings() {
        settings = new HashMap<>();
        settings.put("orderProcessingSettings", "default");
        settings.put("menuPermissions", "default");
    }

    /**
     * Updates the value of a specific system setting.
     *
     * @param setting The name of the setting to update.
     * @param value   The new value to set.
     */
    public void adjustSettings(String setting, String value) {
        if (settings.containsKey(setting)) {
            settings.put(setting, value);
        } else {
            System.out.println("Setting not found: " + setting);
        }
    }

    /**
     * Retrieves the value of a specific system setting.
     *
     * @param setting The name of the setting to retrieve.
     * @return The current value of the setting, or a default message if the setting is not found.
     */
    public String getSetting(String setting) {
        return settings.getOrDefault(setting, "Setting not found");
    }
}
