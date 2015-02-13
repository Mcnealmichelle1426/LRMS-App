package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.MiscExpManager;
import org.vetech.lrms.core.web.json.response.RestPayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nessy on 06/02/15.
 */
@Path("/misc_exp")
public class MiscExpController {
    @GET
    @Path("/{miscID}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMiscExp(@PathParam("miscID") int miscID) {
        MiscExpManager miscExpManager = new MiscExpManager();

        return Response.status(200).entity(miscExpManager.getMiscExp(miscID)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMiscExps(@Context HttpServletRequest servletRequest) {
        MiscExpManager miscExpManager = new MiscExpManager();
        int page = 1;
        int pageSize = 12;
        int startIndex = 1;

        if(page > 1)
        {
            startIndex = (page * pageSize) - pageSize;
        }


        try
        {
            page = Integer.parseInt(servletRequest.getParameter("page"));
            pageSize = Integer.parseInt(servletRequest.getParameter("pageSize"));
            //            query = request.getParameter("_q");
        }
        catch(NumberFormatException e)
        {

        }

        RestPayload restPayload = new RestPayload();

        restPayload = miscExpManager.getMiscExps(page, pageSize, startIndex, false, servletRequest.getParameter("") );


        return Response.status(200).entity(restPayload).build();
    }
}
