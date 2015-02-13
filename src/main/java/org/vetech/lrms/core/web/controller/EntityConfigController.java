package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.EntityConfigManager;
import org.vetech.lrms.core.web.json.bean.EntityConfigBean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/6/15.
 */
@Path("/entity")
public class EntityConfigController {
	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEntity(EntityConfigBean entityConfigBean) {
		EntityConfigManager entityConfigManager = new EntityConfigManager();

		return Response.status(200).entity(entityConfigManager.createEntity(entityConfigBean)).build();
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntities() {
		EntityConfigManager entityConfigManager = new EntityConfigManager();

		return Response.status(200).entity(entityConfigManager.getEntities()).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	public Response getByEntityID(@PathParam("id") int id) {
		EntityConfigManager entityConfigManager = new EntityConfigManager();

		return Response.status(200).entity(entityConfigManager.getEntityByID(id)).build();
	}
}
