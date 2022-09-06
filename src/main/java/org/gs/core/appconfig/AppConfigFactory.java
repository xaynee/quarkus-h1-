package org.gs.core.appconfig;

import java.util.Objects;

import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.gs.core.appconfig.implement.CustomConfig;
import org.gs.core.appconfig.implement.MainContactTypeConfig;
import org.gs.core.util.CheckUtil;
import org.gs.exception.FormatException;

@Singleton
public class AppConfigFactory {

    public AppConfig validateThenSave(AppConfig config) {
        validateInput(config);
        var spec = getAppConfigSpec(config.configName);
        var validateConfig = spec.validate(config);
        try {
            var savedConfig = AppConfig.findAppConfig(validateConfig.configName, validateConfig.applicationId);
            savedConfig.value = validateConfig.value;
            savedConfig.status = Objects.requireNonNullElse(validateConfig.status, savedConfig.status);
            return savedConfig;
        }catch (NoResultException nre) {
            // Config belum tersedia, buat baru.
            validateConfig.persist();
            return validateConfig;
        }catch (NonUniqueResultException nue) {
            // Terdapat mulitple config dengan nama sama dan itu tidak boleh
            // hapus semua dan simpan config yang baru.
            AppConfig.deleteAllDuplicateConfig(validateConfig.configName, validateConfig.applicationId);
            validateConfig.persist();
            return validateConfig;
        }

    }

    private void validateInput(AppConfig entity) {
        Objects.requireNonNull(entity, "AppConfig is null");

        if(CheckUtil.isStringBlank(entity.configName)) {
            throw new FormatException("Config name can't blank");
        }

        if(CheckUtil.isStringBlank(entity.value)) {
            throw new FormatException("Config value can't blank");
        }
        
        if(entity.applicationId == null) {
            throw new FormatException("Application Id can't be null");
        }
    }

    private AppConfigSpec getAppConfigSpec(String confName) {
        var configType = AppConfigType.getEnum(confName);
        switch(configType) {
            case MAIN_CONTACT_TYPE :
                return new MainContactTypeConfig();
            default :
                return new CustomConfig();
        }
    }
    


}
