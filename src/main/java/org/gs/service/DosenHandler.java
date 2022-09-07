package org.gs.service;

import org.gs.entity.DosenTable;
import org.gs.exception.DataNotFoundException;
import org.gs.exception.ExceptionCode;
import org.gs.model.body.DosenBody;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@ApplicationScoped
public class DosenHandler {
    public List<DosenBody> getDosenTable(long id){
        return DosenTable.findById(id)
                .stream()
                .map(DosenBody::fromDosenTable)
                .collect(Collectors.toList());
    }

    public static List<DosenBody> getAllDosenTable(){
        return DosenTable.getAllData()
                .stream()
                .map(DosenBody::fromDosenTable)
                .collect(Collectors.toList());
    }

    public DosenTable updateDosenTable(DosenBody body){
        DosenTable dosen = DosenTable.findById(body.getIdDosen());
        if(dosen == null){
            throw new DataNotFoundException(ExceptionCode.GROUP_NOT_FOUND, "Table not found");
        }
        body.updateDosenBody(dosen);
        return dosen;
    }

    public DosenBody createDosenTable(DosenBody body){
        var dosenTable = saveNewDosenTable(body);
        return DosenBody.fromDosenTable(dosenTable);
    }

    private DosenTable saveNewDosenTable(DosenBody body){
        var dosenTable = new DosenTable();
        dosenTable.id = body.getIdDosen();
        dosenTable.namaDosen = body.getNamaDosen();
        dosenTable.noTelp = body.getNoTelp();
        dosenTable.alamat = body.getAlamat();
        dosenTable.persist();
        return dosenTable;
    }
}
