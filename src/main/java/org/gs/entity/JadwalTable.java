package org.gs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jadwal")
public class JadwalTable {
    @Id

    @Column(name="id_jadwal")
    public Long idJadwal;

    @Column(name="tanggal")
    public String tanggal;

    @Column(name="ruangan")
    public String ruangan;
}
