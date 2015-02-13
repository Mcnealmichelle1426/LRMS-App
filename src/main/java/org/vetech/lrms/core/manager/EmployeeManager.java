package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.*;
import org.vetech.lrms.core.web.json.response.RestPayload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nessy on 05/02/15.
 */
public class EmployeeManager {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    IdGen idGen = new IdGen();

    public EmployeeBean create(EmployeeBean employeeBean) {
        int personID = databaseManager.insertReturnID("Insert into client (uuid) values (" + idGen.UuIDGen() + ")");
        int employeeID = databaseManager.insertReturnID("Insert into employee (uuid) values (" + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Name table
         */
        databaseManager.executeSQL("insert into person_Name (person_ID, preffix, first_Name, other_Name, uuid) values (" + personID + "," + employeeBean.getPersonBean().getPersonNameBean().getPrefix() + "," + employeeBean.getPersonBean().getPersonNameBean().getFirstName() + "," + employeeBean.getPersonBean().getPersonNameBean().getOtherName() + "," + idGen.UuIDGen() + ")");

        /**
         * Insert person_ID and other data into person_Address table
         */
        databaseManager.executeSQL("insert into person_Address (person_ID, postal_Address, postal_Code, postal_Location_ID, country, state, district, location, sublocation, estate, uuid) values "
                + "(" + personID + "," + employeeBean.getPersonBean().getPersonAddressBean().getPostalAddress() + "," + employeeBean.getPersonBean().getPersonAddressBean().getPostalCode() + ", " + employeeBean.getPersonBean().getPersonAddressBean().getLocationBean().getLocationID() + ", " + employeeBean.getPersonBean().getPersonAddressBean().getCountry() + ", " + employeeBean.getPersonBean().getPersonAddressBean().getState() + ","
                + "" + employeeBean.getPersonBean().getPersonAddressBean().getDistrict() + "," + employeeBean.getPersonBean().getPersonAddressBean().getLocation() + "," + employeeBean.getPersonBean().getPersonAddressBean().getSublocation() + "," + employeeBean.getPersonBean().getPersonAddressBean().getEstate() + "," + idGen.UuIDGen() + ")");
        /**
         * now to add a person's personal attributes we now add call the personAttributeBean json model
         */
        databaseManager.executeSQL("insert person_Attribute (person_ID, id_Type, id_Number, phone_No, email, next_Of_Kin_ID, image_URL, uuid) values (" + personID + ", " + employeeBean.getPersonBean().getPersonAttributeBean().getIdTypeBean().getTypeID() + ", " + employeeBean.getPersonBean().getPersonAttributeBean().getIdNumber() + ", " + employeeBean.getPersonBean().getPersonAttributeBean().getPhoneNumber() + "," + employeeBean.getPersonBean().getPersonAttributeBean().getEmail() + ""
                + "," + employeeBean.getPersonBean().getPersonAttributeBean().getNextOfKinBean().getPersonBean().getPersonID() + ", " + employeeBean.getPersonBean().getPersonAttributeBean().getImageURL() + "," + idGen.UuIDGen() + ")");

        return getEmployee(employeeID);
    }

    public EmployeeBean getEmployee (int employeeID) {
        EmployeeBean employeeBean = new EmployeeBean();

        try {
            ResultSet employeeRS = databaseManager.getData("select * from employee where employee_Person_ID ="+employeeID+"");
            while (employeeRS.next()) {
                /**
                 * This will get the person_ID of a specific column from the resultSet.
                 */
                int employeeDataID = employeeRS.getInt("employee_ID");

                /**
                 * This sets the initial json object fro the person list.
                 */
                employeeBean.setEmployeeID(employeeDataID);

                int personID = employeeRS.getInt("employee_person_ID");


                /**
                 * Get a person's from person_Name table
                 */

                ResultSet personRS = databaseManager.getData("select * from person where person_ID = " + personID + "");
                while (personRS.next()) {
                    PersonBean personBean = new PersonBean();
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
                        personAttributeBean.setIdNumber(personAttributeRS.getInt("id_Number"));
                        personAttributeBean.setPhoneNumber(personAttributeRS.getString("phone_No"));
                        personAttributeBean.setEmail(personAttributeRS.getString("email"));
                        personAttributeBean.setImageURL(personAttributeRS.getString("image_URL"));

                        personBean.setPersonAttributeBean(personAttributeBean);
                    }

                    employeeBean.setPersonBean(personBean);

                    /**
                     * * Return job title details
                     */
                    ResultSet jobTitleRS = databaseManager.getData("select * from job_Title where job_Title_Person_ID = " + personID + "");
                    while (jobTitleRS.next()) {
                        JobTitleBean jobTitleBean = new JobTitleBean();

                        jobTitleBean.setJobTitleID(jobTitleRS.getInt("job_Title_ID"));
                        jobTitleBean.setJobName(jobTitleRS.getString("job_Name"));
                        jobTitleBean.setBasicSalary(jobTitleRS.getInt("basic_Salary"));


                        employeeBean.setJobTitleBean(jobTitleBean);

                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null,e);
        }


        return employeeBean;
    }


    public RestPayload getEmployees(int page, int startIndex, int maxRecords, boolean showAll, String active) {
        RestPayload restPayload = new RestPayload();

        restPayload.setPage(page);
        restPayload.setMaxItems(maxRecords);

        List<EmployeeBean> formattedEmployees = new ArrayList<>();

        try {
            ResultSet employeeRS = databaseManager.getData("select * from employee");
            while (employeeRS.next()) {
                EmployeeBean employeeBean = new EmployeeBean();
                /**
                 * This will get the person_ID of a specific column from the resultSet.
                 */
                int employeeDataID = employeeRS.getInt("employee_ID");

                /**
                 * This sets the initial json object fro the person list.
                 */
                employeeBean.setEmployeeID(employeeDataID);

                int personID = employeeRS.getInt("employee_person_ID");


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

                    employeeBean.setPersonBean(personBean);

                    /**
                     * * Return job title details
                     */
                    ResultSet jobTitleRS = databaseManager.getData("select * from job_Title where job_Title_Person_ID = "+personID+"");
                    while (jobTitleRS.next()) {
                        JobTitleBean jobTitleBean = new JobTitleBean();

                        jobTitleBean.setJobTitleID(jobTitleRS.getInt("job_Title_ID"));
                        jobTitleBean.setJobName(jobTitleRS.getString("job_Name"));
                        jobTitleBean.setBasicSalary(jobTitleRS.getInt("basic_Salary"));


                        employeeBean.setJobTitleBean(jobTitleBean);

                    }

                    formattedEmployees.add(employeeBean);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null,e);
        }

        restPayload.setInlineCount(formattedEmployees.size());
        restPayload.setResults(formattedEmployees);

        return restPayload;


    }

    public static void main(String args[]){
        EmployeeManager employeeManager = new EmployeeManager();

        Object employee = employeeManager.getEmployee(1);

        System.out.println(employee);
    }
}
