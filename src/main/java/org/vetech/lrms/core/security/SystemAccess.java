package org.vetech.lrms.core.security;

import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.web.json.bean.PersonBean;
import org.vetech.lrms.core.web.json.bean.PersonNameBean;
import org.vetech.lrms.core.web.json.bean.UserBean;
import org.vetech.lrms.core.web.json.response.LoginResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/9/15.
 */
public class SystemAccess {
	DatabaseManager databaseManager = DatabaseManager.getInstance();
//	LoginResponse loginResponse = new LoginResponse();
	UserBean userBean = new UserBean();
	PersonBean personBean = new PersonBean();

	public LoginResponse authenticateUser(LoginResponse loginResponse) {
		String userName = loginResponse.getUsername();
		try {
			ResultSet userRS = databaseManager.getData("select * from user where user_Name = '"+userName+"'");
			while (userRS.next()) {

			}
		} catch (SQLException e) {
			Logger.getLogger(SystemAccess.class.getName()).log(Level.SEVERE, null, e);
		}

		return loginResponse;
	}
}
