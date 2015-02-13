package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.PersonManager;
import org.vetech.lrms.core.web.json.bean.PersonBean;
import org.vetech.lrms.core.web.json.response.RestPayload;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/4/15.
 */
@Path("/person")
public class PersonController {
	@GET
	@Path("/{personID}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("personID") int personID) {
		PersonManager personManager = new PersonManager();

		return Response.status(200).entity(personManager.getPerson(personID)).build();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersons(@Context HttpServletRequest servletRequest) {
		PersonManager personManager = new PersonManager();
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

		restPayload = personManager.getPersons(page, pageSize, startIndex, false, servletRequest.getParameter("") );


		return Response.status(200).entity(restPayload).build();
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerson(PersonBean personBean) {
		PersonManager personManager = new PersonManager();

		return Response.status(200).entity(personManager.create(personBean)).build();
	}
}
