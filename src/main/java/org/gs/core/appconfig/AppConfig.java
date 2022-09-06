package org.gs.core.appconfig;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.gs.core.util.TableStatus;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_config")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AppConfig extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "app_config_id", nullable = false)
    public Long id;

    @Column(name = "xk_application_id", nullable = false)
    @NotNull
    public Long applicationId;

    @Column(name = "config_name", nullable = false)
    @NotBlank
    public String configName;

    @Column(name = "value", nullable = false)
    @NotBlank
    public String value;

    @Enumerated
    @Column(name = "status", nullable = false)
    public TableStatus status = TableStatus.ACTIVE;

    public static AppConfig create(long appId, String confName, String value, TableStatus status) {
        var ent = new AppConfig();
        ent.applicationId = appId;
        ent.configName = confName;
        ent.value = value;
        ent.status = status;
        return ent;
    }

    //----------- ACTIVE RECORD

    public static AppConfig findAppConfig(String confName, long appId) {
        return find("configName = ?1 and applicationId = ?2", confName, appId).singleResult();
    }

    public static AppConfig findAppConfig(AppConfigType confType, long appId) {
        Objects.requireNonNull(confType, "Required confType");
        return findAppConfig(confType.toString(), appId);
    }

    public static List<AppConfig> findSelectedAppConfig(List<String> confName, long appId) {
        return find("configName in (?1) and applicationId = ?2", confName, appId).list();
    }

    public static List<AppConfig> findSelectedAppConfigUseType(List<AppConfigType> confNames, long appId) {
        var confNameInString = confNames.stream().map(Object::toString).collect(Collectors.toList());
        return findSelectedAppConfig(confNameInString, appId);
    }

    public static long deleteAllDuplicateConfig(String confName, long appId) {
        return delete("configName = ?1 and applicationId = ?2", confName, appId);
    }

}
