package org.vetech.lrms.core.web.json.bean;

import java.util.Date;

/**
 * Created by alex on 2/3/15.
 */
public class PersonBean {
	int personID = 0;
	String gender = "";
//	Date dateOfBirth = null;
	String dateOfBirth = "";
	PersonAddressBean personAddressBean = null;
	PersonAttributeBean personAttributeBean = null;
	PersonNameBean personNameBean = null;

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//	public Date getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(Date dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public PersonAddressBean getPersonAddressBean() {
		return personAddressBean;
	}

	public void setPersonAddressBean(PersonAddressBean personAddressBean) {
		this.personAddressBean = personAddressBean;
	}

	public PersonAttributeBean getPersonAttributeBean() {
		return personAttributeBean;
	}

	public void setPersonAttributeBean(PersonAttributeBean personAttributeBean) {
		this.personAttributeBean = personAttributeBean;
	}

	public PersonNameBean getPersonNameBean() {
		return personNameBean;
	}

	public void setPersonNameBean(PersonNameBean personNameBean) {
		this.personNameBean = personNameBean;
	}
}
