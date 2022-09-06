package org.gs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dosen")
public class DosenTable {
    @Id

    @Column(name="id_dosen")
    public Long idDosen;

    @Column(name="nama_dosen")
    public String namaDosen;

    @Column(name="no_tlpn")
    public String noTlpn;

    @Column(name="alamat")
    public String alamat;
}
