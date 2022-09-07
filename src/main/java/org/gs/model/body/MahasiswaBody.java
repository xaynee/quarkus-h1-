package org.gs.model.body;

import static org.gs.core.util.ManipulateUtil.changeItOrNot;


import org.gs.entity.MahasiswaTable;

import lombok.Data;

@Data
public class MahasiswaBody {
    private Long idMahasiswa;
    private String namaMahasiswa;
    private String jenisKelamin;
    private String noTelp;
    private String alamat;

    public static MahasiswaBody fromMahasiswaTable(MahasiswaTable ent){
        var output= new MahasiswaBody();
        output.setIdMahasiswa(ent.id);
        output.setNamaMahasiswa(ent.namaMahasiswa);
        output.setJenisKelamin(ent.jenisKelamin);
        output.setNoTelp(ent.noTelp);
        return output;
    }

    public MahasiswaTable updateMahasiswaBody(MahasiswaTable mahasiswa){
        mahasiswa.namaMahasiswa = changeItOrNot(namaMahasiswa, mahasiswa.namaMahasiswa);
        mahasiswa.jenisKelamin = changeItOrNot(jenisKelamin, mahasiswa.jenisKelamin);
        mahasiswa.noTelp = changeItOrNot(noTelp, mahasiswa.noTelp);
        return mahasiswa;
    }
}
