package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/3/15.
 */
public class PersonNameBean {
	int personNameID = 0;
	int personID = 0;
	String prefix = "";
	String surname = "";
	String firstName = "";
	String otherName = "";

	public int getPersonNameID() {
		return personNameID;
	}

	public void setPersonNameID(int personNameID) {
		this.personNameID = personNameID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
}
