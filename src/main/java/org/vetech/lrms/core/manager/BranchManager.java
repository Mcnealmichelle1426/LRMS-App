package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.CRUD;
import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.HibernateUtilHelper;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.*;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/8/15.
 */
public class BranchManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	IdGen idGen = new IdGen();
	BranchBean branchBean = new BranchBean();
	EmployeeBean employeeBean = new EmployeeBean();
	LocationBean locationBean = new LocationBean();
	PersonBean personBean = new PersonBean();
	PersonNameBean personNameBean = new PersonNameBean();

	public BranchBean createBranch (BranchBean branchBean) {

		int branchID = databaseManager.insertReturnID("insert into branch (branch_Name, branch_Code, branch_Postal_Address, branch_Postal_Code, branch_Location_ID, branch_Mail, branch_Head_Id, active, uuid) "
				+ "values ('"+branchBean.getBranchName()+"', '"+branchBean.getBranchCode()+"','"+branchBean.getPostalAddress()+"','"+branchBean.getPostalCode()+"','"+branchBean.getBranchLocation().getLocationID()+"', '"+branchBean.getBranchMail()+"','"+branchBean.getBranchHead().getEmployeeID()+"',"+branchBean.isActive()+",'"+idGen.UuIDGen()+"')");

		return getBranchById(branchID);
	}

	public BranchBean getBranchById(int id) {
		try {
			ResultSet branchRS = databaseManager.getData("select * from branch where branch_ID = '"+id+"'");
			while (branchRS.next()) {
				int locID = branchRS.getInt("branch_Location_ID");
				int branchHead = branchRS.getInt("branch_Head_Id");
				branchBean.setBranchName(branchRS.getString("branch_Name"));
				branchBean.setBranchCode(branchRS.getString("branch_Code"));
				branchBean.setPostalAddress(branchRS.getString("branch_Postal_Address"));
				branchBean.setPostalCode(branchRS.getInt("branch_Postal_Code"));
				branchBean.setBranchMail(branchRS.getString("branch_Mail"));
				branchBean.setActive(branchRS.getBoolean("active"));

				ResultSet locationRS = databaseManager.getData("select * from location where location_ID = '"+locID+"'");
				while (locationRS.next()) {
					locationBean.setLocationID(locationRS.getInt("location_ID"));
					locationBean.setLocationName(locationRS.getString("location_Name"));

					branchBean.setBranchLocation(locationBean);
				}

				ResultSet employeeRS = databaseManager.getData("select * from employee where employee_ID = '"+branchHead+"'");
				while (employeeRS.next()) {
					int jobLocID = employeeRS.getInt("job_Location_ID");
					int personID = employeeRS.getInt("employee_Person_ID");

					ResultSet personNameRS = databaseManager.getData("select * from person_Name where person_ID ='"+personID+"'");
					while (personNameRS.next()) {
						personNameBean.setPersonID(personID);
						personNameBean.setPrefix(personNameRS.getString("preffix"));
						personNameBean.setSurname(personNameRS.getString("surname"));
						personNameBean.setFirstName(personNameRS.getString("first_Name"));
						personNameBean.setOtherName(personNameRS.getString("other_Name"));

						personBean.setPersonNameBean(personNameBean);
						employeeBean.setPersonBean(personBean);
						branchBean.setBranchHead(employeeBean);

					}
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(BranchManager.class.getName()).log(Level.SEVERE, null,e);
		}
		return branchBean;
	}

	public BranchBean updateBranch(BranchBean branchBean) {
		int branchID = branchBean.getBranchID();
		databaseManager.executeSQL("update branch set branch_Name = '"+branchBean.getBranchName()+"', branch_Code = '"+branchBean.getBranchCode()+"', branch_Postal_Address = '"+branchBean.getPostalAddress()+"',"
				+ " branch_Postal_Code = '"+branchBean.getPostalCode()+"', branch_Location_ID = '"+branchBean.getBranchLocation().getLocationID()+"', branch_Mail = '"+branchBean.getBranchMail()+"', branch_Head_Id = '"+branchBean.getBranchHead().getEmployeeID()+"', active = "+branchBean.isActive()+" where branch_ID = '"+branchID+"'");

		return getBranchById(branchID);
	}

//	public SearchPayLoad searchPayLoad() {
//
//	}
}
