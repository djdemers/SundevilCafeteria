package com.domain.model;

public class SystemSettings {
    private String settingName;
    private String settingValue;

    public SystemSettings(String settingName, String settingValue) {
        this.settingName = settingName;
        this.settingValue = settingValue;
    }

    public String getSettingName() {
        return settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}