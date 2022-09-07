package org.gs.model.body;

import static org.gs.core.util.ManipulateUtil.changeItOrNot;

import org.gs.entity.DosenTable;

import lombok.Data;

@Data
public class DosenBody {
    private Long idDosen;
    private String namaDosen;
    private String noTelp;
    private String alamat;

    public static DosenBody fromDosenTable(DosenTable ent){
        var output = new DosenBody();
        output.setIdDosen(ent.id);
        output.setNamaDosen(ent.namaDosen);
        output.setNoTelp(ent.noTelp);
        output.setAlamat(ent.alamat);
        return output;
    }

    public DosenTable updateDosenBody(DosenTable dosen){
        dosen.namaDosen = changeItOrNot(namaDosen, dosen.namaDosen);
        dosen.noTelp = changeItOrNot(noTelp, dosen.noTelp);
        dosen.alamat = changeItOrNot(alamat, dosen.alamat);
        return dosen;
    }
    
}
