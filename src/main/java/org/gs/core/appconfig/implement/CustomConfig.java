package org.gs.core.appconfig.implement;

import org.gs.core.appconfig.AppConfig;
import org.gs.core.appconfig.AppConfigSpec;


public class CustomConfig implements AppConfigSpec {

    @Override
    public AppConfig validate(AppConfig entity) {
        validateInput(entity);
        return entity;
    }
    
}
