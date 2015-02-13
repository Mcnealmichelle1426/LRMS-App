package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/3/15.
 */
public class PersonAddressBean {
	int personAddressID = 0;
	int personID = 0;
	String postalAddress;
	int postalCode = 0;
	LocationBean locationBean = null;
	String country = "";
	String state = "";
	String district = "";
	String location = "";
	String sublocation = "";
	String estate = "";

	public int getPersonAddressID() {
		return personAddressID;
	}

	public void setPersonAddressID(int personAddressID) {
		this.personAddressID = personAddressID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public LocationBean getLocationBean() {
		return locationBean;
	}

	public void setLocationBean(LocationBean locationBean) {
		this.locationBean = locationBean;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSublocation() {
		return sublocation;
	}

	public void setSublocation(String sublocation) {
		this.sublocation = sublocation;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}
}
