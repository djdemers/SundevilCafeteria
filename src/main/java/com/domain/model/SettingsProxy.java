// SettingsProxy Class for Role-Based Access Control
package com.domain.model;

/**
 * Proxy class for controlling access to system settings based on the user's role.
 * Ensures that only users with appropriate privileges can modify settings.
 * Implements the Proxy design pattern.
 */
public class SettingsProxy {
    private SystemSettings systemSettings; // The real subject being proxied
    private String currentRole; // The role of the user accessing the settings

    /**
     * Constructor to initialize the SettingsProxy with an instance of SystemSettings.
     */
    public SettingsProxy() {
        this.systemSettings = new SystemSettings();
    }

    /**
     * Sets the current user's role for access control.
     *
     * @param role The role of the user (Manager, Operator).
     */
    public void setRole(String role) {
        this.currentRole = role;
    }

    /**
     * Updates a system setting if the user has sufficient privileges.
     * Only users with the "Manager" role are allowed to modify settings.
     *
     * @param setting The name of the setting to update.
     * @param value   The new value for the setting.
     */
    public void updateSettings(String setting, String value) {
        if (currentRole == null) {
            System.out.println("Access denied: No role assigned");
        } else if (currentRole.equals("Manager")) {
            systemSettings.adjustSettings(setting, value);
            System.out.println("Settings updated by Manager: " + setting + " set to " + value);
        } else {
            System.out.println("Access denied: Insufficient privileges to modify settings");
        }
    }

    /**
     * Retrieves the value of a system setting.
     *
     * @param setting The name of the setting to retrieve.
     * @return The current value of the setting.
     */
    public String getSettings(String setting) {
        return systemSettings.getSetting(setting);
    }
}
