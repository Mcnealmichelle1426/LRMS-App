package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.*;
import org.vetech.lrms.core.web.json.response.RestPayload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nessy on 06/02/15.
 */
public class UserManager {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    IdGen idGen = new IdGen();

    public UserBean create(UserBean userBean) {
        int personID = databaseManager.insertReturnID("Insert into person (uuid) values (" + idGen.UuIDGen() + ")");
        int userID = databaseManager.insertReturnID("Insert into user (uuid) values (" + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Name table
         */
        databaseManager.executeSQL("insert into person_Name (person_ID, preffix, first_Name, other_Name, uuid) values (" + personID + "," + userBean.getPersonBean().getPersonNameBean().getPrefix() + "," + userBean.getPersonBean().getPersonNameBean().getFirstName() + "," + userBean.getPersonBean().getPersonNameBean().getOtherName() + "," + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Address table
         */
        databaseManager.executeSQL("insert into person_Address (person_ID, postal_Address, postal_Code, postal_Location_ID, country, state, district, location, sublocation, estate, uuid) values "
                + "(" + personID + "," + userBean.getPersonBean().getPersonAddressBean().getPostalAddress() + "," + userBean.getPersonBean().getPersonAddressBean().getPostalCode() + ", " + userBean.getPersonBean().getPersonAddressBean().getLocationBean().getLocationID() + ", " + userBean.getPersonBean().getPersonAddressBean().getCountry() + ", " + userBean.getPersonBean().getPersonAddressBean().getState() + ","
                + "" + userBean.getPersonBean().getPersonAddressBean().getDistrict() + "," + userBean.getPersonBean().getPersonAddressBean().getLocation() + "," + userBean.getPersonBean().getPersonAddressBean().getSublocation() + "," + userBean.getPersonBean().getPersonAddressBean().getEstate() + "," + idGen.UuIDGen() + ")");
        /**
         * now to add a person's personal attributes we now add call the personAttributeBean json model
         */
        databaseManager.executeSQL("insert person_Attribute (person_ID, id_Type, id_Number, phone_No, email, next_Of_Kin_ID, image_URL, uuid) values (" + personID + ", " + userBean.getPersonBean().getPersonAttributeBean().getIdTypeBean().getTypeID() + ", " + userBean.getPersonBean().getPersonAttributeBean().getIdNumber() + ", " + userBean.getPersonBean().getPersonAttributeBean().getPhoneNumber() + "," + userBean.getPersonBean().getPersonAttributeBean().getEmail() + ""
                + "," + userBean.getPersonBean().getPersonAttributeBean().getNextOfKinBean().getPersonBean().getPersonID() + ", " + userBean.getPersonBean().getPersonAttributeBean().getImageURL() + "," + idGen.UuIDGen() + ")");

        return getUser(userID);
    }

    public UserBean getUser (int userID) {
        UserBean userBean = new UserBean();

        try {
            ResultSet userRS = databaseManager.getData("select * from user where user_Person_ID ="+userID+"");
            while (userRS.next()) {
                /**
                 * This will get the person_ID of a specific column from the resultSet.
                 */
                int userDataID = userRS.getInt("user_ID");

                /**
                 * This sets the initial json object fro the person list.
                 */
                userBean.setUserID(userDataID);

                int personID = userRS.getInt("user_person_ID");

                String userName = userRS.getString("user_Name");
                String password = userRS.getString("password");
                String securityQuestion = userRS.getString("security_Question");
                String securityAnswer = userRS.getString("security_Answer");
                String oathToken = userRS.getString("oath_Token");

                userBean.setUserName(userName);
                userBean.setPassword(password);
                userBean.setSecurityQuestion(securityQuestion);
                userBean.setSecurityAnswer(securityAnswer);
                userBean.setOathToken(oathToken);

                /**
                 * Get a person's from person_Name table
                 */

                ResultSet personRS = databaseManager.getData("select * from person where person_ID = " + personID + "");
                while (personRS.next()) {
                    PersonBean personBean = new PersonBean();

                    personBean.setGender(personRS.getString("gender"));
                    personBean.setDateOfBirth(personRS.getString("date_Of_Birth"));

                    ResultSet personNameRS = databaseManager.getData("select * from person_Name where person_ID = " + personID + "");
                    while (personNameRS.next()) {
                        PersonNameBean personNameBean = new PersonNameBean();

                        /**
                         * person name data
                         */
                        personNameBean.setPersonID(personID);
                        personNameBean.setPrefix(personNameRS.getString("preffix"));
                        personNameBean.setSurname(personNameRS.getString("surname"));
                        personNameBean.setFirstName(personNameRS.getString("first_Name"));
                        personNameBean.setOtherName(personNameRS.getString("other_Name"));


                        personBean.setPersonNameBean(personNameBean);
                    }
                    /**
                     * person address data
                     */

                    ResultSet personAddressRS = databaseManager.getData("select * from person_Address where Person_ID = " + personID + "");
                    while (personAddressRS.next()) {
                        PersonAddressBean personAddressBean = new PersonAddressBean();

                        personAddressBean.setPersonID(personID);
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

                    /**
                     * Return person attribute data
                     */
                    ResultSet personAttributeRS = databaseManager.getData("select * from person_Attribute where person_ID = " + personID + "");
                    while (personAttributeRS.next()) {
                        PersonAttributeBean personAttributeBean = new PersonAttributeBean();

                        personAttributeBean.setPersonID(personID);
                        personAttributeBean.setPersonID(personID);
                        personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
                        personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
                        personAttributeBean.setEmail(personAttributeRS.getString("email"));
                        personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

                        personBean.setPersonAttributeBean(personAttributeBean);
                    }

                    /**
                     * *Return locale details
                     */
                    ResultSet localeRS = databaseManager.getData("select * from locale where locales_ID = "+personID+"");
                    while (localeRS.next()) {
                        LocaleBean localeBean = new LocaleBean();

                        int localeID = localeRS.getInt("locales_ID");
                        localeBean.setLocalesID(localeID);
                        localeBean.setLocaleName(localeRS.getString("locale_Name"));
                        localeBean.setLocaleCode(localeRS.getString("locale_Code"));

                        userBean.setLocaleBean(localeBean);
                    }

                    userBean.setPersonBean(personBean);


                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null,e);
        }


        return userBean;
    }


    public RestPayload getUsers(int page, int startIndex, int maxRecords, boolean showAll, String active) {
        RestPayload restPayload = new RestPayload();

        restPayload.setPage(page);
        restPayload.setMaxItems(maxRecords);

        List<UserBean> formattedUsers = new ArrayList<>();

        try {
            ResultSet userRS = databaseManager.getData("select * from user");
            while (userRS.next()) {
                UserBean userBean = new UserBean();
                /**
                 * This will get the person_ID of a specific column from the resultSet.
                 */
                int userDataID = userRS.getInt("user_ID");

                /**
                 * This sets the initial json object fro the person list.
                 */
                userBean.setUserID(userDataID);

                int personID = userRS.getInt("user_person_ID");

                String userName = userRS.getString("user_Name");
                String password = userRS.getString("password");
                String securityQuestion = userRS.getString("security_Question");
                String securityAnswer = userRS.getString("security_Answer");
                String oathToken = userRS.getString("oath_Token");

                userBean.setUserName(userName);
                userBean.setPassword(password);
                userBean.setSecurityQuestion(securityQuestion);
                userBean.setSecurityAnswer(securityAnswer);
                userBean.setOathToken(oathToken);
                /**
                 * Get a person's from person_Name table
                 */

                ResultSet personRS = databaseManager.getData("select * from person where person_ID = " + personID + "");
                while (personRS.next()) {
                    PersonBean personBean = new PersonBean();

                    personBean.setGender(personRS.getString("gender"));
                    personBean.setDateOfBirth(personRS.getString("date_Of_Birth"));

                    ResultSet personNameRS = databaseManager.getData("select * from person_Name where person_ID = "+personID+"");
                    while (personNameRS.next()){
                        PersonNameBean personNameBean = new PersonNameBean();

                        /**
                         * person name data
                         */
                        personNameBean.setPersonID(personID);
                        personNameBean.setPrefix(personNameRS.getString("preffix"));
                        personNameBean.setSurname(personNameRS.getString("surname"));
                        personNameBean.setFirstName(personNameRS.getString("first_Name"));
                        personNameBean.setOtherName(personNameRS.getString("other_Name"));


                        personBean.setPersonNameBean(personNameBean);
                    }
                    /**
                     * person address data
                     */

                    ResultSet personAddressRS = databaseManager.getData("select * from person_Address where Person_ID = "+personID+"");
                    while (personAddressRS.next()){
                        PersonAddressBean personAddressBean = new PersonAddressBean();

                        personAddressBean.setPersonID(personID);
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

                    /**
                     * Return person attribute data
                     */
                    ResultSet personAttributeRS = databaseManager.getData("select * from person_Attribute where person_ID = "+personID+"");
                    while (personAttributeRS.next()) {
                        PersonAttributeBean personAttributeBean = new PersonAttributeBean();

                        personAttributeBean.setPersonID(personID);
                        personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
                        personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
                        personAttributeBean.setEmail(personAttributeRS.getString("email"));
                        personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

                        personBean.setPersonAttributeBean(personAttributeBean);
                    }

                    userBean.setPersonBean(personBean);

                    ResultSet localeRS = databaseManager.getData("select * from locale where locales_ID = "+personID+"");
                    while (localeRS.next()) {
                        LocaleBean localeBean = new LocaleBean();

                        int localeID = localeRS.getInt("locales_ID");
                        localeBean.setLocalesID(localeID);
                        localeBean.setLocaleName(localeRS.getString("locale_Name"));
                        localeBean.setLocaleCode(localeRS.getString("locale_Code"));

                        userBean.setLocaleBean(localeBean);
                    }


                    formattedUsers.add(userBean);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null,e);
        }

        restPayload.setInlineCount(formattedUsers.size());
        restPayload.setResults(formattedUsers);

        return restPayload;


    }

    public static void main(String args[]){
        UserManager userManager = new UserManager();

        Object user = userManager.getUser(1);

        System.out.println(user);
    }
}
