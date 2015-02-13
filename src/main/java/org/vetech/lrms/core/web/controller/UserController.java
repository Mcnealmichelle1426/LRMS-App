package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.ClientManager;
import org.vetech.lrms.core.manager.UserManager;
import org.vetech.lrms.core.web.json.response.RestPayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nessy on 06/02/15.
 */
@Path("/user")
public class UserController {
    @GET
    @Path("/{userID}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("userID") int userID) {
        UserManager userManager = new UserManager();

        return Response.status(200).entity(userManager.getUser(userID)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@Context HttpServletRequest servletRequest) {
        UserManager userManager = new UserManager();
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

        restPayload = userManager.getUsers(page, pageSize, startIndex, false, servletRequest.getParameter("") );


        return Response.status(200).entity(restPayload).build();
    }
}

