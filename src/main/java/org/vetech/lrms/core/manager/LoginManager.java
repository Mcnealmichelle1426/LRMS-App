package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.response.LoginResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 2/8/15.
 */
public class LoginManager {
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	IdGen idGen = new IdGen();

//	public LoginResponse userLogin(String userName) {
//		LoginResponse loginResponse = new LoginResponse();
//
//		try {
//			ResultSet userRS = databaseManager.getData("select * from user where user_Name = "+userName+"");
//			while (userRS.next()) {
//				String oathToken = userRS.getString("oath_Token") + idGen.UuIDGen();
//			}
//		} catch (SQLException e) {
//
//		}
//	}
}
