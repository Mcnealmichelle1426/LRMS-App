package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.*;
import org.vetech.lrms.core.web.json.bean.PersonAddressBean;
import org.vetech.lrms.core.web.json.bean.PersonAttributeBean;
import org.vetech.lrms.core.web.json.bean.PersonBean;
import org.vetech.lrms.core.web.json.bean.PersonNameBean;
import org.vetech.lrms.core.web.json.response.RestPayload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/3/15.
 */
public class PersonManager {
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	IdGen idGen = new IdGen();

	/**
	 * In order to create a person we need to also add the person's
	 * address, names, attributes and also location and identification details.
	 *
	 * This is achieved by inserting data into Person Table and return the generated person_ID.
	 */
	public PersonBean create(PersonBean personBean) {
		String dob = personBean.getDateOfBirth();

		java.sql.Date dateDB = null;

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			java.util.Date date = dateFormat.parse(dob);

			dateDB = new java.sql.Date(date.getTime());
		} catch (Exception e) {
			Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null,e);
		}

		java.sql.Date dateOfBirth = (dateDB);

		int personID = databaseManager.insertReturnID("Insert into person (gender, date_Of_Birth, uuid) values ('"+personBean.getGender()+"','"+dateOfBirth+"','"+idGen.UuIDGen()+"')");

		/**
		 * This will insert data into person_Name table and also insert the person_ID into the table
		 */
		databaseManager.executeSQL("insert into person_Name (person_ID, preffix, surname, first_Name, other_Name, uuid) values ('"+personID+"','"+personBean.getPersonNameBean().getPrefix()+"', '"+personBean.getPersonNameBean().getSurname()+"','"+personBean.getPersonNameBean().getFirstName()+"','"+personBean.getPersonNameBean().getOtherName()+"','"+idGen.UuIDGen()+"')");

		/**
		 * Insert data into the person address table and also insert the matching personID
		 */
		databaseManager.executeSQL("insert into person_Address (person_ID, postal_Address, postal_Code, postal_Location_ID, country, state, district, location, sublocation, estate, uuid) values "
				+ "('"+personID+"','"+personBean.getPersonAddressBean().getPostalAddress()+"','"+personBean.getPersonAddressBean().getPostalCode()+"', '"+personBean.getPersonAddressBean().getLocationBean().getLocationID()+"', '"+personBean.getPersonAddressBean().getCountry()+"', '"+personBean.getPersonAddressBean().getState()+"',"
				+ "'"+personBean.getPersonAddressBean().getDistrict()+"','"+personBean.getPersonAddressBean().getLocation()+"','"+personBean.getPersonAddressBean().getSublocation()+"','"+personBean.getPersonAddressBean().getEstate()+"','"+idGen.UuIDGen()+"')");
		/**
		 * now to add a person's personal attributes we now add call the personAttributeBean json model
		 */
		databaseManager.executeSQL("insert person_Attribute (person_ID, id_Type, id_Number, phone_No, email, next_Of_Kin_ID, image_URL, uuid) values ('"+personID+"', '"+personBean.getPersonAttributeBean().getIdTypeBean().getTypeID()+"', '"+personBean.getPersonAttributeBean().getIdNumber()+"', '"+personBean.getPersonAttributeBean().getPhoneNumber()+"','"+personBean.getPersonAttributeBean().getEmail()+"'"
				+ ",'"+personBean.getPersonAttributeBean().getNextOfKinBean().getPersonID()+"', '"+personBean.getPersonAttributeBean().getImageURL()+"','"+idGen.UuIDGen()+"')");

		return getPerson(personID);
	}

	public PersonBean getPerson (int personID) {
		PersonBean personBean = new PersonBean();

		try {
			ResultSet personRS = databaseManager.getData("select * from person where person_ID ="+personID+"");
			while (personRS.next()) {

				/**
				 * This gets person table data using person_ID key.
				 */
				int personDataID = personRS.getInt("person_ID");

				personBean.setPersonID(personDataID);
				personBean.setGender(personRS.getString("gender"));
				personBean.setDateOfBirth(personRS.getString("date_Of_Birth"));

				/**
				 * Now we get other person details from the other tables related to person
				 */
				ResultSet personNameRS = databaseManager.getData("select * from person_Name where person_ID ="+personDataID+"");
				while (personNameRS.next()) {
					PersonNameBean personNameBean = new PersonNameBean();

					personNameBean.setPersonID(personNameRS.getInt("person_ID"));
					personNameBean.setPrefix(personNameRS.getString("preffix"));
					personNameBean.setSurname(personNameRS.getString("surname"));
					personNameBean.setFirstName(personNameRS.getString("first_Name"));
					personNameBean.setOtherName(personNameRS.getString("other_Name"));

					personBean.setPersonNameBean(personNameBean);
				}

				/**
				 * person address data
				 */
				ResultSet personAddressRS = databaseManager.getData("select * from person_Address where person_ID = "+personDataID+"");
				while (personAddressRS.next()) {
					PersonAddressBean personAddressBean = new PersonAddressBean();

					personAddressBean.setPersonID(personAddressRS.getInt("person_ID"));
					personAddressBean.setPostalAddress(personAddressRS.getString("postal_Address"));
					personAddressBean.setPostalCode(personAddressRS.getInt("postal_Code"));
//					personAddressBean.setLocationBean();
					personAddressBean.setCountry(personAddressRS.getString("country"));
					personAddressBean.setState(personAddressRS.getString("state"));
					personAddressBean.setDistrict(personAddressRS.getString("district"));
					personAddressBean.setLocation(personAddressRS.getString("location"));
					personAddressBean.setSublocation(personAddressRS.getString("sublocation"));
					personAddressBean.setEstate(personAddressRS.getString("estate"));

					personBean.setPersonAddressBean(personAddressBean);
				}

				/**
				 * Return person attribute data
				 */
				ResultSet personAttributeRS = databaseManager.getData("select * from person_Attribute where person_ID = "+personDataID+"");
				while (personAttributeRS.next()) {
					PersonAttributeBean personAttributeBean = new PersonAttributeBean();

					personAttributeBean.setPersonID(personAttributeRS.getInt("person_ID"));
					personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
					personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
					personAttributeBean.setEmail(personAttributeRS.getString("email"));
//					personAttributeBean.setNextOfKin(personBean);
					personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

					personBean.setPersonAttributeBean(personAttributeBean);
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null,e);
		}

		return personBean;
	}

	public RestPayload getPersons(int page, int startIndex, int maxRecords, boolean showAll, String active) {
		RestPayload restPayload = new RestPayload();

		restPayload.setPage(page);
		restPayload.setMaxItems(maxRecords);

		List<PersonBean> formattedPersons = new ArrayList<>();

		try {
			ResultSet personRS = databaseManager.getData("select * from person");
			while (personRS.next()) {
				PersonBean personBean = new PersonBean();
				/**
				 * This will get the person_ID of a specific column from the resultSet.
				 */
				int personDataID = personRS.getInt("person_ID");

				/**
				 * This sets the initial json object fro the person list.
				 */
				personBean.setPersonID(personDataID);
				personBean.setGender(personRS.getString("gender"));
				personBean.setDateOfBirth(personRS.getString("date_Of_Birth"));

				/**
				 * Get a person's from person_Name table
				 */
				ResultSet personNameRS = databaseManager.getData("select * from person_Name where person_ID = "+personDataID+"");
				while (personNameRS.next()) {
					PersonNameBean personNameBean = new PersonNameBean();

					/**
					 * This will return a single column from person_Name table that has a person_ID equal to the one on person table.
					 */
					personNameBean.setPersonID(personDataID);
					personNameBean.setPrefix(personNameRS.getString("preffix"));
					personNameBean.setSurname(personNameRS.getString("surname"));
					personNameBean.setFirstName(personNameRS.getString("first_Name"));
					personNameBean.setOtherName(personNameRS.getString("other_Name"));

					personBean.setPersonNameBean(personNameBean);
				}

				/**
				 * Get details from attributes tables
				 */
				ResultSet personAttributesRS = databaseManager.getData("select * from person_Attribute where person_ID = "+personDataID+"");
				while (personAttributesRS.next()){
					PersonAttributeBean personAttributeBean = new PersonAttributeBean();

					personAttributeBean.setPersonID(personDataID);
					personAttributeBean.setIdNumber(personAttributesRS.getInt("id_NUmber"));
					personAttributeBean.setPhoneNumber(personAttributesRS.getString("phone_No"));
					personAttributeBean.setEmail(personAttributesRS.getString("email"));
					personAttributeBean.setImageURL(personAttributesRS.getString("image_URL"));

					personBean.setPersonAttributeBean(personAttributeBean);
				}

				/**
				 * Get details from person_Address table
				 */
				ResultSet personAddressRS = databaseManager.getData("select * from person_Address where person_ID = "+personDataID+"");
				while (personAddressRS.next()){
					PersonAddressBean personAddressBean = new PersonAddressBean();

					personAddressBean.setPersonID(personDataID);
					personAddressBean.setPostalAddress(personAddressRS.getString("postal_Address"));
					personAddressBean.setPostalCode(personAddressRS.getInt("postal_Code"));
					personAddressBean.setCountry(personAddressRS.getString("country"));
					personAddressBean.setState(personAddressRS.getString("state"));
					personAddressBean.setDistrict(personAddressRS.getString("district"));
					personAddressBean.setLocation(personAddressRS.getString("location"));
					personAddressBean.setSublocation(personAddressRS.getString("sublocation"));
					personAddressBean.setEstate(personAddressRS.getString("estate"));

					personBean.setPersonAddressBean(personAddressBean);
				}


				formattedPersons.add(personBean);
			}

		} catch (SQLException e) {
			Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null,e);
		}

		restPayload.setInlineCount(formattedPersons.size());
		restPayload.setResults(formattedPersons);

		return restPayload;
	}

	public static void main(String args[]){
		PersonManager personManager = new PersonManager();

		Object person = personManager.getPerson(3);

		System.out.println(person);
	}
}
//	public PersonBean getPerson(int personID) {
//
//	}
//}
