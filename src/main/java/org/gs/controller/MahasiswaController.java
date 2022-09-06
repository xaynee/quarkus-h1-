package org.gs.controller;


import org.gs.entity.MahasiswaTable;
import org.gs.model.body.MahasiswaBody;
import org.gs.service.MahasiswaHandler;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import java.util.List;

@Path("api/v1/mahasiswa")
public class MahasiswaController {
    @Inject
    MahasiswaHandler mahasiswaHandler;


@GET
public List<MahasiswaBody> getMahasiswa(@QueryParam long id){
    return mahasiswaHandler.getMahasiswa(id);
}

@GET
@Path("/get -all")
@Transactional
public List<MahasiswaBody> getAllMahasiswa(){
    return MahasiswaHandler.getAllMahasiswaTable();
}

@GET
@Path("/update")
@Transactional
public MahasiswaTable updateMahasiswa(MahasiswaBody body){
    return mahasiswaHandler.updateMahasiswaTable(body);
}

@GET
@Path("/create")
@Transactional
public MahasiswaBody createMahasiswa(MahasiswaBody body){
    return mahasiswaHandler.createMahasiswaTable(body);
}
}

