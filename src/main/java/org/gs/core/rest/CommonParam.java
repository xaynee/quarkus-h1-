package org.gs.core.rest;

import io.quarkus.panache.common.Page;
import lombok.Data;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
public class CommonParam {
    @QueryParam("page")
    @DefaultValue("1")
    private int page;
    @QueryParam("limit")
    @DefaultValue("10")
    private int limit;
    @QueryParam("noLmt")
    @DefaultValue("false")
    private boolean noLmt;
    @QueryParam("sortBy")
    @DefaultValue("")
    private String sortBy;
    @QueryParam("asc")
    @DefaultValue("true")
    private boolean asc = true;

    /**
     * ambil page untuk query panache.
     * @return kalo nolmt = true return null;
     */
    public Page getPage() {
        if(noLmt) {
            return null;
        }
        return Page.of(page - 1, limit);
    }
}
