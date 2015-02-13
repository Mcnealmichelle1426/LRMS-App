package org.vetech.lrms.core.web.controller;

import org.vetech.lrms.core.manager.LocationManager;
import org.vetech.lrms.core.web.json.bean.LocationBean;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 2/6/15.
 */
@Path("/location")
public class LocationController {

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocations() {
		LocationManager locationManager = new LocationManager();

		return Response.status(200).entity(locationManager.getLocations()).build();
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLocation(LocationBean locationBean) {
		LocationManager locationManager = new LocationManager();

		return Response.status(200).entity(locationManager.createLocation(locationBean)).build();
	}

	@Path("/search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	public Response searchLocation(@Context HttpServletRequest servletRequest) {
		LocationManager locationManager = new LocationManager();

		SearchPayLoad searchPayLoad = new SearchPayLoad();

		String searchContext = "";

		searchContext = servletRequest.getParameter("_q");

		try {
			if (!searchContext.isEmpty()) {
				searchPayLoad = locationManager.searchLocations(searchContext);
			} else {
				searchPayLoad = locationManager.searchLocations(searchContext);
			}
		} catch (Exception e) {
			searchPayLoad = locationManager.searchLocations(searchContext);
		}

		return Response.status(200).entity(searchPayLoad.getResults()).build();
	}

	@GET
	@Path("/{locID}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLocationByID(@PathParam("locID") int locID) {
		LocationManager locationManager = new LocationManager();

		return Response.status(200).entity(locationManager.getLocationByID(locID)).build();
	}
}
