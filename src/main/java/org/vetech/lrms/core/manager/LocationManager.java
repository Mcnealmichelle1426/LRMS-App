package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.hibernate.models.Location;
import org.vetech.lrms.core.system.*;
import org.vetech.lrms.core.web.json.bean.LocationBean;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 2/6/15.
 */
public class LocationManager {
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	IdGen idGen = new IdGen();

	public LocationBean createLocation (LocationBean locationBean){
		Location location = new Location();

		location.setLocationId(idGen.randomIdGen());
		location.setLocationName(locationBean.getLocationName());
		location.setUuid(idGen.UuIDGen());

		return getLocationByID((int) crud.save(location));
	}

	public LocationBean getLocationByID(int locID) {
		@SuppressWarnings("unchecked")
		List<Location> existing = crud.findByPrimaryKey(locID, "from Location where locationId = :pk");

		LocationBean locationBean = new LocationBean();

		if (existing.size() > 0) {
			Location location = existing.get(0);

			locationBean.setLocationID(location.getLocationId());
			locationBean.setLocationName(location.getLocationName());
		}

		return locationBean;
	}

	public List<LocationBean> getLocations() {
		@SuppressWarnings("unchecked")
		List<Object> objectList = crud.getObject("from Location");

		List<LocationBean> formattedLocations = new ArrayList<>();

		LocationBean locationBean = new LocationBean();
		for (Object object : objectList) {
			Location location = (Location) object;
			locationBean.setLocationID(location.getLocationId());
			locationBean.setLocationName(locationBean.getLocationName());

			formattedLocations.add(locationBean);
		}

		return formattedLocations;
	}

	public SearchPayLoad searchLocations(String searchKey) {
		SearchPayLoad searchPayLoad = new SearchPayLoad();
		@SuppressWarnings("unchecked")
		List<Object> existing = crud.getObject("From Location where locationName like '%"+searchKey+"%'");

		List<LocationBean> locationList = new ArrayList<>();

		LocationBean locationBean = new LocationBean();

		for (Object object : existing) {
			Location location = (Location) object;

			locationBean.setLocationID(location.getLocationId());
			locationBean.setLocationName(location.getLocationName());

			locationList.add(locationBean);
		}
		searchPayLoad.setResults(locationList);

		return searchPayLoad;
	}
}
