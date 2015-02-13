package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.CaseClassManager;
import org.vetech.lrms.core.web.json.bean.CaseClassBean;
import org.vetech.lrms.core.web.json.response.RestPayload;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/3/15.
 */
@Path("/caseClass")
public class CaseClassController {

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCaseClasses(@Context HttpServletRequest servletRequest) {
		CaseClassManager caseClassManager = new CaseClassManager();
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

		try {
			if (servletRequest.getParameter("_active").equalsIgnoreCase("true")) {
				restPayload = caseClassManager
						.getCaseClasses(page, pageSize, startIndex, false, servletRequest.getParameter("_active"));
			}
		} catch (Exception e) {
			restPayload = caseClassManager.getCaseClasses(pageSize, page, startIndex, false, "");
		}

		return Response.status(200).entity(restPayload).build();
	}

	@GET
	@Path("/caseClass_type_ahead")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSearchResult(@Context HttpServletRequest servletRequest) {
		CaseClassManager caseClassManager = new CaseClassManager();

		SearchPayLoad searchPayLoad = new SearchPayLoad();

		String searchContext = "";

		searchContext = servletRequest.getParameter("_q");

		try {
			if (!searchContext.isEmpty()) {
				searchPayLoad = caseClassManager.getAutoCaseClass(searchContext);
			} else {
				searchPayLoad = caseClassManager.getAutoCaseClass(searchContext);
			}
		} catch (Exception e) {
			searchPayLoad = caseClassManager.getAutoCaseClass(searchContext);
		}
		return Response.status(200).entity(searchPayLoad.getResults()).build();
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCaseClass(CaseClassBean caseClassBean) {
		CaseClassManager caseClassManager = new CaseClassManager();

		return Response.status(200).entity(caseClassManager.createCaseClass(caseClassBean)).build();
	}

	@Path("/{code}")
	@PUT
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCaseClass(@PathParam("code") String code, CaseClassBean caseClassBean) {
		CaseClassManager caseClassManager = new CaseClassManager();

		return Response.status(200).entity(caseClassManager.updateCaseClass(caseClassBean)).build();
	}

	@GET
	@Path("/{code}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCaseClassById(@PathParam("code") String code) {
		CaseClassManager caseClassManager = new CaseClassManager();

		return Response.status(200).entity(caseClassManager.getCaseClass(code)).build();
	}

//	@PUT @Path("/") @Consumes("application/json") @Produces(MediaType.APPLICATION_JSON)
//	public Response activateDeactivateCaseClasses(CaseClassBean caseClassBean) {
//		CaseClassManager caseClassManager = new CaseClassManager();
//
//		return Response.status(200).entity(caseClassManager.activateDeactivate(caseClassBean)).build();
//	}

}
