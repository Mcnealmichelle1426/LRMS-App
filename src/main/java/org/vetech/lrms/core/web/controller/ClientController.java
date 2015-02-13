package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.ClientManager;
import org.vetech.lrms.core.web.json.response.RestPayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
* Created by nessy on 05/02/15.
*/
@Path("/client")
public class ClientController {
    @GET
    @Path("/{clientID}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@PathParam("clientID") int clientID) {
        ClientManager clientManager = new ClientManager();

        return Response.status(200).entity(clientManager.getClient(clientID)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients(@Context HttpServletRequest servletRequest) {
        ClientManager clientManager = new ClientManager();
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

        restPayload = clientManager.getClients(page, pageSize, startIndex, false, servletRequest.getParameter("") );


        return Response.status(200).entity(restPayload).build();
    }
}
