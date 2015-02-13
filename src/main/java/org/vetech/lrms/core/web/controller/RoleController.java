package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.RoleManager;
import org.vetech.lrms.core.web.json.bean.RoleBean;
import org.vetech.lrms.core.web.json.bean.RoleMapBean;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/8/15.
 */
@Path("/role")
public class RoleController {
	RoleManager roleManager = new RoleManager();

	@GET @Path("/") @Produces(MediaType.APPLICATION_JSON) public Response getRoles() {
		return Response.status(200).entity(roleManager.getRoles()).build();
	}

	@GET @Path("/search") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public Response searchRole(@Context HttpServletRequest servletRequest) {
		SearchPayLoad searchPayLoad = new SearchPayLoad();

		String searchContext = "";

		searchContext = servletRequest.getParameter("_q");

		try {
			if (!searchContext.isEmpty()) {
				searchPayLoad = roleManager.searchRole(searchContext);
			}

		} catch (Exception e) {

		}
		return Response.status(200).entity(searchPayLoad.getResults()).build();

	}

	@POST @Path("/") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public Response createRole(RoleBean roleBean) {
		return Response.status(200).entity(roleManager.createRole(roleBean)).build();
	}

	@PUT @Path("/{id}") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public Response updateRole(@PathParam("id") int id, RoleBean roleBean) {
		return Response.status(200).entity(roleManager.updateRoleById(id, roleBean)).build();
	}

	@DELETE @Path("/{id}") @Consumes("application/json") @Produces(MediaType.APPLICATION_JSON)
	public Response deleteRole(@PathParam("id") int id) {
		return Response.status(200).entity(roleManager.activateDeactivateRoles(id)).build();
	}

	@GET @Path("/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getRoleById(@PathParam("id") int id) {
		return Response.status(200).entity(roleManager.getRoleById(id)).build();
	}

	@POST @Path("/group/") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public Response createGroup(RoleMapBean roleMapBean) {
		return Response.status(200).entity(roleManager.createRoleGroup(roleMapBean)).build();
	}

	@GET @Path("/group/{id}") @Produces(MediaType.APPLICATION_JSON)
	public Response getRoleGroupById(@PathParam("id") int id) {
		return Response.status(200).entity(roleManager.getRoleGroupById(id)).build();
	}

	@GET @Path("/group/") @Produces(MediaType.APPLICATION_JSON)
	public Response getAllGroups() {
		return Response.status(200).entity(roleManager.getAllGroups()).build();
	}

	@PUT @Path("/group/") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public Response updateGroup(RoleMapBean roleMapBean) {
		return Response.status(200).entity(roleManager.updateRoleGroupById(roleMapBean)).build();
	}
}
