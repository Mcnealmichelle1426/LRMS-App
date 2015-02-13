package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/8/15.
 */
public class BranchBean {
	int branchID = 0;
	String branchName = "";
	String branchCode = "";
	String postalAddress = "";
	int postalCode = 0;
	String branchMail = "";
	LocationBean branchLocation = null;
	EmployeeBean branchHead = null;
	boolean active = false;

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getBranchMail() {
		return branchMail;
	}

	public void setBranchMail(String branchMail) {
		this.branchMail = branchMail;
	}

	public LocationBean getBranchLocation() {
		return branchLocation;
	}

	public void setBranchLocation(LocationBean branchLocation) {
		this.branchLocation = branchLocation;
	}

	public EmployeeBean getBranchHead() {
		return branchHead;
	}

	public void setBranchHead(EmployeeBean branchHead) {
		this.branchHead = branchHead;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
