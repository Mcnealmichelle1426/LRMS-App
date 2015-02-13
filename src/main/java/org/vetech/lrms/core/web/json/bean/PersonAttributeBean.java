package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/3/15.
 */
public class PersonAttributeBean {
	int personAttributeID = 0;
	int personID = 0;
	int idNumber = 0;
	String phoneNumber = "";
	String email = "";
	String imageURL = "";
	PersonBean nextOfKin = null;
	IdTypeBean idTypeBean = null;
	NextOfKinBean nextOfKinBean = null;

	public NextOfKinBean getNextOfKinBean() {
		return nextOfKinBean;
	}

	public void setNextOfKinBean(NextOfKinBean nextOfKinBean) {
		this.nextOfKinBean = nextOfKinBean;
	}

	public PersonBean getNextOfKin() {
		return nextOfKin;
	}

	public void setNextOfKin(PersonBean nextOfKin) {
		this.nextOfKin = nextOfKin;
	}

	public IdTypeBean getIdTypeBean() {
		return idTypeBean;
	}

	public void setIdTypeBean(IdTypeBean idTypeBean) {
		this.idTypeBean = idTypeBean;
	}

	public int getPersonAttributeID() {
		return personAttributeID;
	}

	public void setPersonAttributeID(int personAttributeID) {
		this.personAttributeID = personAttributeID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
