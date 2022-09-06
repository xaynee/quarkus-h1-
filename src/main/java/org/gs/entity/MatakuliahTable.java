package org.gs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="matakuliah")
public class MatakuliahTable {
    @Id

    @Column(name ="id_matakuliah")
    public Long idMatkul;

    @Column(name = "nama_matakuliah")
    public String namaMatkul;

    @Column(name ="sks")
    public String sks;
}
