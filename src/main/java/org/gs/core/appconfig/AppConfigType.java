package org.gs.core.appconfig;

//TODO : Ini berbeda setiap service.
public enum AppConfigType {
    MAIN_CONTACT_TYPE,
    // Hanya ini yang sama setiap service.
    CUSTOM;

    public static AppConfigType getEnum(String confName) {
        try {
            return AppConfigType.valueOf(confName);
        }catch (Exception e) {
            return CUSTOM;
        }
    }
}
