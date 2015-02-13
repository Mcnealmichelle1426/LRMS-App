package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.ClientManager;
import org.vetech.lrms.core.manager.EmployeeManager;
import org.vetech.lrms.core.web.json.response.RestPayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nessy on 05/02/15.
 */
@Path("/employee")
public class EmployeeController {
    @GET
    @Path("/{employeeID}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("employeeID") int employeeID) {
        EmployeeManager employeeManager = new EmployeeManager();

        return Response.status(200).entity(employeeManager.getEmployee(employeeID)).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees(@Context HttpServletRequest servletRequest) {
        EmployeeManager employeeManager = new EmployeeManager();
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

        restPayload = employeeManager.getEmployees(page, pageSize, startIndex, false, servletRequest.getParameter(""));


        return Response.status(200).entity(restPayload).build();
    }
}
