// SystemSettings Class
package com.domain.model;

import java.util.HashMap;
import java.util.Map;

public class SystemSettings {
    private Map<String, String> settings;

    public SystemSettings() {
        settings = new HashMap<>();
        settings.put("orderProcessingSettings", "default");
        settings.put("menuPermissions", "default");
    }

    public void adjustSettings(String setting, String value) {
        if (settings.containsKey(setting)) {
            settings.put(setting, value);
        } else {
            System.out.println("Setting not found: " + setting);
        }
    }

    public String getSetting(String setting) {
        return settings.getOrDefault(setting, "Setting not found");
    }
}
