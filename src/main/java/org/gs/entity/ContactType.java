package org.gs.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name = "contact_type")
public class ContactType extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(generator = "dimata_id_gen")
    @Column(name = "CONTACT_TYPE_ID")
    public Long id;

    @Column(name = "XK_APPLICATION_ID")
    @NotNull
    public Long applicationId;

    @Column(name = "TITLE")
    @NotNull
    public String title;

    @Column(name = "STATUS")
    public Boolean status = true;

    @Column(name = "ALLOWED_MULTI_LINK_CONTACT")
    public Boolean allowedMultiLinkContact = true;

    public static ContactType create(long appId, String title) {
        var ent = new ContactType();
        ent.applicationId = appId;
        ent.title = title;
        return ent;
    }

    //--------- ACTIVE RECORD

    public static boolean isTypeExist(String typeName, long appId) {
        var count = count("title = ?1 and applicationId = ?2", typeName, appId);

        return count > 0;
    }

    public static Optional<ContactType> findActiveByName(String typeName, long appId) {
        return find("title = ?1 and applicationId = ?2 and status = true", typeName, appId).singleResultOptional();
    }
    
    public static List<ContactType> findAllActiveByName(List<String> typeNames, long appId) {
    	return find("title in ?1 and applicationId = ?2 and status = true", typeNames, appId).list();
    }

    public static Optional<ContactType> findByName(String typeName, long appId) {
        return find("title = ?1 and applicationId = ?2", typeName, appId).singleResultOptional();
    }
}
