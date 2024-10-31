// SettingsProxy Class for Role-Based Access Control
package com.domain.model;

public class SettingsProxy {
    private SystemSettings systemSettings;
    private String currentRole;

    public SettingsProxy() {
        this.systemSettings = new SystemSettings();
    }

    public void setRole(String role) {
        this.currentRole = role;
    }

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

    public String getSettings(String setting) {
        return systemSettings.getSetting(setting);
    }
}