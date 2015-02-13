package org.vetech.lrms.core.web.json.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 2/4/15.
 */
public class NextOfKinBean {
	List<PersonBean> personBeanList = new ArrayList<>();

	public List<PersonBean> getPersonBeanList() {
		return personBeanList;
	}

	public void setPersonBeanList(List<PersonBean> personBeanList) {
		this.personBeanList = personBeanList;
	}

	PersonBean personBean = null;

	public PersonBean getPersonBean() {
		return personBean;
	}

	public void setPersonBean(PersonBean personBean) {
		this.personBean = personBean;
	}

	int personID = 0;
	String gender = "";
	Date dateOfBirth = null;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
