package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.IdTypeManager;
import org.vetech.lrms.core.web.json.bean.IdTypeBean;
import org.vetech.lrms.core.web.json.response.RestPayload;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/6/15.
 */
@Path("/id")
public class IdTypeController {
	private String _corsHeaders;

	private Response makeCORS(Response.ResponseBuilder req, String returnMethod) {
		Response.ResponseBuilder rb = req.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

		if (!"".equals(returnMethod)) {
			rb.header("Access-Control-Allow-Headers", returnMethod);
		}

		return rb.build();
	}

	private Response makeCORS(Response.ResponseBuilder req) {
		return makeCORS(req, _corsHeaders);
	}

	@OPTIONS
	@Path("/")
	public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH) {
		_corsHeaders = requestH;
		return makeCORS(Response.ok(), requestH);
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllIdTypes() {
		IdTypeManager idTypeManager = new IdTypeManager();

		return Response.status(200).entity(idTypeManager.getIdTypes()).build();
	}

//	@GET
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getIdTypes(@Context HttpServletRequest servletRequest) {
//		IdTypeManager idTypeManager = new IdTypeManager();
//		int page = 1;
//		int pageSize = 12;
//		int startIndex = 1;
//
//		if(page > 1)
//		{
//			startIndex = (page * pageSize) - pageSize;
//		}
//
//
//		try
//		{
//			page = Integer.parseInt(servletRequest.getParameter("page"));
//			pageSize = Integer.parseInt(servletRequest.getParameter("pageSize"));
//			//            query = request.getParameter("_q");
//		}
//		catch(NumberFormatException e)
//		{
//
//		}
//
//		RestPayload restPayload = new RestPayload();
//
//		try {
//			if (servletRequest.getParameter("_active").equalsIgnoreCase("true")) {
//				restPayload = idTypeManager.getIdTypes(page,startIndex, startIndex, false, servletRequest.getParameter("_active"));
//			}
//		} catch (Exception e) {
//
//		}
//
//		return Response.status(200).entity(restPayload).build();
//	}

	@GET
	@Path("/rs")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchIdTypes(@Context HttpServletRequest servletRequest) {
		IdTypeManager idTypeManager = new IdTypeManager();

		SearchPayLoad searchPayLoad = new SearchPayLoad();

		String searchContext = "";

		searchContext = servletRequest.getParameter("_q");

		try {
			if (!searchContext.isEmpty()) {
				searchPayLoad = idTypeManager.searchId(searchContext);
			} else {
				searchPayLoad = idTypeManager.searchId(searchContext);
			}
		} catch (Exception e) {

		}

		return Response.status(200).entity(searchPayLoad.getResults()).build();
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createIdType(IdTypeBean idTypeBean) {
		IdTypeManager idTypeManager = new IdTypeManager();

		return Response.status(200).entity(idTypeManager.createIdType(idTypeBean)).build();
	}

	@GET
	@Path("/{id}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIdType(@PathParam("id") int id) {
		IdTypeManager idTypeManager = new IdTypeManager();

		return Response.status(200).entity(idTypeManager.getIdTypeByKey(id)).build();
	}
}
