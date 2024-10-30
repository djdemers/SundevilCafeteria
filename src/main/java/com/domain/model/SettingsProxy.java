package com.domain.model;

public class SettingsProxy {
    private SystemSettings settings;
    private String currentRole;

    public SettingsProxy(SystemSettings settings, String currentRole) {
        this.settings = settings;
        this.currentRole = currentRole;
    }

    public String getSettingsValue() {
        if (currentRole.equals("Manager")) {
            return settings.getSettingValue();
        } else {
            throw new SecurityException("Access Denied");
        }
    }

    public void updateSettingsValue(String value) {
        if (currentRole.equals("Manager")) {
            settings.setSettingValue(value);
        } else {
            throw new SecurityException("Access Denied");
        }
    }
}