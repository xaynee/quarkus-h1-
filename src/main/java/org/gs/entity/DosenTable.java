package org.gs.entity;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="dosen")
public class DosenTable extends PanacheEntityBase {
    @Id

    @Column(name="id_dosen")
    public Long id;

    @Column(name="nama_dosen")
    public String namaDosen;

    @Column(name="no_tlpn")
    public String noTelp;

    @Column(name="alamat")
    public String alamat;

    public static Optional<DosenTable> findById(long id){
        return find("id =?1",id).firstResultOptional();
    }

    public static List<DosenTable> getAllData(){
        return DosenTable.listAll();
    }
}
