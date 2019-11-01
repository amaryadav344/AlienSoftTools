package com.webstudio.connectionhub.models;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM_SETTINGS")
public class SystemSetting {
    private long SystemSettingID;
    private String SettingName;
    private String SettingValue;

    public SystemSetting() {
    }

    public SystemSetting(long systemSettingID, String settingName, String settingValue) {
        SystemSettingID = systemSettingID;
        SettingName = settingName;
        SettingValue = settingValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getSystemSettingID() {
        return SystemSettingID;
    }

    public void setSystemSettingID(long systemSettingID) {
        SystemSettingID = systemSettingID;
    }
    @Column(name = "SETTING_NAME", nullable = false)
    public String getSettingName() {
        return SettingName;
    }

    public void setSettingName(String settingName) {
        SettingName = settingName;
    }
    @Column(name = "SETTING_VALUE", nullable = false)
    public String getSettingValue() {
        return SettingValue;
    }

    public void setSettingValue(String settingValue) {
        SettingValue = settingValue;
    }
}
