package org.gs.service;

import org.gs.entity.MahasiswaTable;
import org.gs.exception.DataNotFoundException;
import org.gs.exception.ExceptionCode;
import org.gs.model.body.MahasiswaBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MahasiswaHandler {
    public List<MahasiswaBody> getMahasiswaTable(long id){
        return MahasiswaTable.findById(id)
                .stream()
                .map(MahasiswaBody::fromMahasiswaTable)
                .collect(Collectors.toList());
    }

    public static List<MahasiswaBody> getAllMahasiswaTable(){
        return MahasiswaTable.getAllData()
                .stream()
                .map(MahasiswaBody::fromMahasiswaTable)
                .collect(Collectors.toList());
    }

    public MahasiswaTable updateMahasiswaTable(MahasiswaBody body){
        MahasiswaTable mahasiswa = MahasiswaTable.findById(body.getIdMahasiswa());
        if(mahasiswa == null){
            throw new DataNotFoundException(ExceptionCode.GROUP_NOT_FOUND,"Table not found");
        }
        body.updateMahasiswaBody(mahasiswa);
        return mahasiswa;
    }

    public MahasiswaBody createMahasiswaTable(MahasiswaBody body){
        var mahasiswaTable = saveNewMahasiswaTable(body);
        return MahasiswaBody.fromMahasiswaTable(mahasiswaTable);
    }
    
    private MahasiswaTable saveNewMahasiswaTable(MahasiswaBody body){
        var mahasiswaTable = new MahasiswaTable();
        mahasiswaTable.id = body.getIdMahasiswa();
        mahasiswaTable.namaMahasiswa = body.getNamaMahasiswa();
        mahasiswaTable.jenisKelamin = body.getJenisKelamin();
        mahasiswaTable.noTelp = body.getNoTelp();
        mahasiswaTable.persist();
        return mahasiswaTable;
    }
}
