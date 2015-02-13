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
 * Created by nessy on 05/02/15.
 */
public class ClientManager {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    IdGen idGen = new IdGen();

    public ClientBean create(ClientBean clientBean) {
        int personID = databaseManager.insertReturnID("Insert into client (uuid) values (" + idGen.UuIDGen() + ")");
        int clientID = databaseManager.insertReturnID("Insert into client (uuid) values (" + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Name table
         */
        databaseManager.executeSQL("insert into person_Name (person_ID, preffix, first_Name, other_Name, uuid) values (" + personID + "," + clientBean.getPersonBean().getPersonNameBean().getPrefix() + "," + clientBean.getPersonBean().getPersonNameBean().getFirstName() + "," + clientBean.getPersonBean().getPersonNameBean().getOtherName() + "," + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Address table
         */
        databaseManager.executeSQL("insert into person_Address (person_ID, postal_Address, postal_Code, postal_Location_ID, country, state, district, location, sublocation, estate, uuid) values "
                + "(" + personID + "," + clientBean.getPersonBean().getPersonAddressBean().getPostalAddress() + "," + clientBean.getPersonBean().getPersonAddressBean().getPostalCode() + ", " + clientBean.getPersonBean().getPersonAddressBean().getLocationBean().getLocationID() + ", " + clientBean.getPersonBean().getPersonAddressBean().getCountry() + ", " + clientBean.getPersonBean().getPersonAddressBean().getState() + ","
                + "" + clientBean.getPersonBean().getPersonAddressBean().getDistrict() + "," + clientBean.getPersonBean().getPersonAddressBean().getLocation() + "," + clientBean.getPersonBean().getPersonAddressBean().getSublocation() + "," + clientBean.getPersonBean().getPersonAddressBean().getEstate() + "," + idGen.UuIDGen() + ")");
        /**
         * now to add a person's personal attributes we now add call the personAttributeBean json model
         */
        databaseManager.executeSQL("insert person_Attribute (person_ID, id_Type, id_Number, phone_No, email, next_Of_Kin_ID, image_URL, uuid) values (" + personID + ", " + clientBean.getPersonBean().getPersonAttributeBean().getIdTypeBean().getTypeID() + ", " + clientBean.getPersonBean().getPersonAttributeBean().getIdNumber() + ", " + clientBean.getPersonBean().getPersonAttributeBean().getPhoneNumber() + "," + clientBean.getPersonBean().getPersonAttributeBean().getEmail() + ""
                + "," + clientBean.getPersonBean().getPersonAttributeBean().getNextOfKinBean().getPersonBean().getPersonID() + ", " + clientBean.getPersonBean().getPersonAttributeBean().getImageURL() + "," + idGen.UuIDGen() + ")");

        return getClient(clientID);
    }

    public ClientBean getClient (int clientID) {
        ClientBean clientBean = new ClientBean();

        try {
            ResultSet clientRS = databaseManager.getData("select * from client where client_ID ="+clientID+"");
            while (clientRS.next()) {

                /**
                 * This gets person table data using person_ID key.
                 */
                int clientDataID = clientRS.getInt("client_ID");

                clientBean.setClientID(clientDataID);
                int personID = clientRS.getInt("client_person_ID");

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

                    ResultSet personAddressRS = databaseManager.getData("select * from person_Address where person_ID = "+personID+"");
                    while (personAddressRS.next()){
                        PersonAddressBean personAddressBean = new PersonAddressBean();

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
                        personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
                        personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
                        personAttributeBean.setEmail(personAttributeRS.getString("email"));
                        personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

                        personBean.setPersonAttributeBean(personAttributeBean);
                    }

                    clientBean.setPersonBean(personBean);
                }

            }
        } catch (SQLException e) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null,e);
        }

        return clientBean;
    }

    public RestPayload getClients(int page, int startIndex, int maxRecords, boolean showAll, String active) {
        RestPayload restPayload = new RestPayload();

        restPayload.setPage(page);
        restPayload.setMaxItems(maxRecords);

        List<ClientBean> formattedClients = new ArrayList<>();

        try {
            ResultSet clientRS = databaseManager.getData("select * from client");
            while (clientRS.next()) {
                ClientBean clientBean = new ClientBean();
                /**
                 * This will get the person_ID of a specific column from the resultSet.
                 */
                int clientDataID = clientRS.getInt("client_ID");

                /**
                 * This sets the initial json object fro the person list.
                 */
                clientBean.setClientID(clientDataID);
                
                int personID = clientRS.getInt("client_person_ID");

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
                
                ResultSet personAddressRS = databaseManager.getData("select * from person_Address where person_ID = "+personID+"");
                    while (personAddressRS.next()){
                        PersonAddressBean personAddressBean = new PersonAddressBean();

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
                        personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
                        personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
                        personAttributeBean.setEmail(personAttributeRS.getString("email"));
                        personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

                        personBean.setPersonAttributeBean(personAttributeBean);
                    }
                    
                    clientBean.setPersonBean(personBean);

                    formattedClients.add(clientBean);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null,e);
        }

        restPayload.setInlineCount(formattedClients.size());
        restPayload.setResults(formattedClients);

        return restPayload;
    }

    public static void main(String args[]){
        ClientManager clientManager = new ClientManager();

        Object client = clientManager.getClient(1);

        System.out.println(client);
    }
}
