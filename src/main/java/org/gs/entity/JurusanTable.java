package org.gs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jurusan")
public class JurusanTable {
    @Id

    @Column(name="id_jurusan")
    public Long idJurusan;

    @Column(name="nama_jurusan")
    public String namaJurusan;

    
}
