package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/6/15.
 */
public class EntityConfigBean {
	String entityName = "";
	String postalAddress = "";
	int postalCode = 0;
	String locationName = "";
	String entityEmail = "";
	String phoneNo = "";
	int nhifNo = 0;
	int nssfNo = 0;
	int vatNo = 0;
	int pinNo = 0;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getEntityEmail() {
		return entityEmail;
	}

	public void setEntityEmail(String entityEmail) {
		this.entityEmail = entityEmail;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getNhifNo() {
		return nhifNo;
	}

	public void setNhifNo(int nhifNo) {
		this.nhifNo = nhifNo;
	}

	public int getNssfNo() {
		return nssfNo;
	}

	public void setNssfNo(int nssfNo) {
		this.nssfNo = nssfNo;
	}

	public int getVatNo() {
		return vatNo;
	}

	public void setVatNo(int vatNo) {
		this.vatNo = vatNo;
	}

	public int getPinNo() {
		return pinNo;
	}

	public void setPinNo(int pinNo) {
		this.pinNo = pinNo;
	}
}
