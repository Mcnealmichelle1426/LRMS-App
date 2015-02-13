package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/3/15.
 */
public class LocationBean {
	int locationID = 0;
	String locationName = "";

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
