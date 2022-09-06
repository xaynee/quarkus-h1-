package org.gs.core.appconfig.implement;

import org.gs.core.appconfig.AppConfig;
import org.gs.core.appconfig.AppConfigSpec;
import org.gs.entity.ContactType;
import org.gs.exception.DataNotFoundException;
import org.gs.exception.ExceptionCode;

public class MainContactTypeConfig implements AppConfigSpec {

    @Override
    public AppConfig validate(AppConfig entity) {
        entity.value = entity.value
            .trim()
            .replace(" ", "_")
            .toUpperCase();
        var isExist = ContactType.isTypeExist(entity.value, entity.applicationId);
        if(!isExist) {
            throw new DataNotFoundException(ExceptionCode.CFG_VALUE_NO_VALID, "No contact type with name : " + entity.value);
        }
        return entity;
    }
    

}
