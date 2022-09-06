package org.gs.core.appconfig;

import java.util.Objects;

/**
 * Konfigurasi ada dua jenis : pre define (yang disediakan sistem)
 * dan custom (Diinput oleh user sendiri). Implement ini bertujuan
 * untuk config yang pre define agar config sesuai dengan apa yang
 * seharusnya terjadi.
 * 
 * @author Hariyogi
 **/
public interface AppConfigSpec {
    /**
     * Validasi AppConfig sebelum di simpan ke DB.
     * 
     * @param entity Entity config.
     * @return config yang diupdate atau disimpan.
     */
    AppConfig validate(AppConfig entity);

    /**
     * Validasi input entity sebelum menyimpan kedalam DB.
     * 
     * @param entity Entity.
     */
    default void validateInput(AppConfig entity) {
        Objects.requireNonNull(entity, "AppConfig is null");
        Objects.requireNonNull(entity.value, "Value can't be null");
        Objects.requireNonNull(entity.applicationId, "ApplicationId can't be null");
    }


}
