package org.gs.controller;


import org.gs.entity.DosenTable;
import org.gs.model.body.DosenBody;
import org.gs.service.DosenHandler;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import java.util.List;

@Path("api/v1/dosen")
public class DosenController {
    @Inject
    DosenHandler dosenHandler;

@GET 
public List<DosenBody> getDosen(@QueryParam long id){
    return dosenHandler.getDosenTable(id);
}

@GET
@Path("/get -all")
@Transactional
public List<DosenBody> getAllDosen(){
    return DosenHandler.getAllDosenTable();
}

@GET
@Path("/update")
@Transactional
public DosenTable updateDosen(DosenBody body){
    return dosenHandler.updateDosenTable(body);
}

@GET
@Path("/create")
@Transactional
public DosenBody createDosenBody(DosenBody body){
    return dosenHandler.createDosenTable(body);
}
}
