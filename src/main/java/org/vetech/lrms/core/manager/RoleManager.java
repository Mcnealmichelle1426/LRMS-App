package org.vetech.lrms.core.manager;

import org.vetech.lrms.core.hibernate.models.Role;
import org.vetech.lrms.core.hibernate.models.RoleMap;
import org.vetech.lrms.core.system.CRUD;
import org.vetech.lrms.core.system.DatabaseManager;
import org.vetech.lrms.core.system.HibernateUtilHelper;
import org.vetech.lrms.core.system.IdGen;
import org.vetech.lrms.core.web.json.bean.RoleBean;
import org.vetech.lrms.core.web.json.bean.RoleMapBean;
import org.vetech.lrms.core.web.json.response.SearchPayLoad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alex on 2/8/15.
 */
public class RoleManager {
	HibernateUtilHelper hibernateUtilHelper = new HibernateUtilHelper();
	CRUD crud = new CRUD(hibernateUtilHelper.getMerchandisingHU());
	DatabaseManager databaseManager = DatabaseManager.getInstance();
	IdGen idGen = new IdGen();

	public RoleBean getRoleById(int id) {
		RoleBean roleBean = new RoleBean();
		@SuppressWarnings("unchecked")
		List<Object> existing = crud.findByPrimaryKey(id,"from Role where roleId = :pk");
		if (existing.size() > 0) {
			Role role = (Role) existing.get(0);

			roleBean.setRoleID(role.getRoleId());
			roleBean.setRole(role.getRole());
			roleBean.setDescription(role.getRoleDescription());
			roleBean.setActive(role.getActive());
		}
		return roleBean;
	}

	public RoleBean updateRoleById(int id, RoleBean roleBean) {
		Role role = null;
//		RoleBean roleBean = new RoleBean();

		@SuppressWarnings("unchecked") List<Object> existing = crud.findByPrimaryKey(id, "from Role where roleId = :pk");
		if (existing.size() > 0) {
			role = (Role) existing.get(0);

			role.setRole(roleBean.getRole());
			role.setRoleDescription(roleBean.getDescription());
			crud.saveOrUpdate(role);
		}

		return getRoleById(id);
	}

	public RoleBean createRole(RoleBean roleBean) {
		Role role = new Role();

		role.setRoleId(idGen.randomIdGen());
		role.setRole(roleBean.getRole());
		role.setRoleDescription(roleBean.getDescription());
		role.setActive(true);
		role.setUuid(idGen.UuIDGen());

		return getRoleById((int) crud.save(role));
	}

	public List<RoleBean> getRoles() {
		List<RoleBean> formattedRoles = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Role> roleList = crud.getObject("from Role");
		for (Object object : roleList) {
			Role role = (Role) object;
			RoleBean roleBean = new RoleBean();

			roleBean.setRoleID(role.getRoleId());
			roleBean.setRole(role.getRole());
			roleBean.setDescription(role.getRoleDescription());
			roleBean.setActive(role.getActive());

			formattedRoles.add(roleBean);
		}

		return formattedRoles;
	}

	public SearchPayLoad searchRole(String role) {
		SearchPayLoad searchPayLoad = new SearchPayLoad();

		@SuppressWarnings("unchecked")
		List<Role> roleList = crud.getObject("from Role where role like '%"+role+"%' or roleDescription like '%"+role+"%'");

		List<RoleBean> formattedResult = new ArrayList<>();

		for (Object object : roleList) {
			Role roles = (Role) object;

			RoleBean roleBean = new RoleBean();

			roleBean.setRoleID(roles.getRoleId());
			roleBean.setRole(roles.getRole());
			roleBean.setDescription(roles.getRoleDescription());
			roleBean.setActive(roles.getActive());

			formattedResult.add(roleBean);
		}
		searchPayLoad.setResults(formattedResult);

		return searchPayLoad;
	}

	public int activateDeactivateRoles(int id) {

		Role role = null;

		RoleBean roleBean = new RoleBean();

		@SuppressWarnings("unchecked")List<Object> existing = crud.findByPrimaryKey(id, "from Role where roleId = :pk");

		if (existing.size() > 0) {
			role = (Role) existing.get(0);

			role.setActive(roleBean.isActive());
		    crud.saveOrUpdate(role);
		}

		return returnID(id);
	}

	public int returnID(int id) {
		@SuppressWarnings("unchecked")
		List<Object> existing = crud.findByPrimaryKey(id,"from Role where roleId = :pk");
		if (existing.size() > 0) {
			Role role = (Role)existing.get(0);

			id = role.getRoleId();
		}
		return id;
	}

	public RoleMapBean createRoleGroup(RoleMapBean roleMapBean) {
		RoleBean roleBean = new RoleBean();
		String groupName = "";
		int roleOne = 0;
		int roleTwo = 0;
		int roleThree = 0;
		try {
			ResultSet roleOneRS = databaseManager.getData("select * from role where '"+roleBean.getRole()+"'");
			while (roleOneRS.next()) {
				roleOne = roleOneRS.getInt("role_ID");
				roleBean.setRoleID(roleOne);
			}
			ResultSet roleTwoRS = databaseManager.getData("select * from role where '"+roleBean.getRole()+"'");
			while (roleTwoRS.next()) {
				roleTwo = roleTwoRS.getInt("role_ID");
				roleBean.setRoleID(roleTwo);
			}
			ResultSet roleThreeRS = databaseManager.getData("select * from role where '"+roleBean.getRole()+"'");
			while (roleThreeRS.next()) {
				roleThree = roleThreeRS.getInt("role_ID");
				roleBean.setRoleID(roleThree);
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, null,e);
		}
		int roleMapID = databaseManager.insertReturnID(
				"insert into role_Map (role_One, role_Two, role_Three, uuid, active, role_Group) values ('" + roleMapBean
						.getRoleOne().getRoleID() + "', '" + roleMapBean.getRoleTwo().getRoleID() + "','" + roleMapBean.getRoleThree().getRoleID() + "','"
						+ idGen.UuIDGen() + "', 1, '" + roleMapBean.getRoleGroup() + "')");
		
		return getRoleGroupById(roleMapID);
	}

	/**
	 * Update user access groups
	 */
	public RoleMapBean updateRoleGroupById(RoleMapBean roleMapBean) {
		int id = roleMapBean.getRoleMapId();

		databaseManager.executeSQL("update role_Map set role_One = '"+roleMapBean.getRoleOne().getRoleID()+"', role_Two = '"+roleMapBean.getRoleTwo().getRoleID()+"', role_Three = '"+roleMapBean.getRoleThree().getRoleID()+"'"
				+ ", active = "+roleMapBean.isActive()+", role_Group = '"+roleMapBean.getRoleGroup()+"' where role_Map_ID = '"+id+"'");

		return getRoleGroupById(id);
	}
	
	public RoleMapBean getRoleGroupById(int id) {
		RoleMapBean roleMapBean = new RoleMapBean();
		int roleOne = 0;
		int roleTwo = 0;
		int roleThree = 0;
		
		try {
			ResultSet roleGroupRS = databaseManager.getData("select * from role_Map where role_Map_ID = '"+id+"'");
			while (roleGroupRS.next()) {
				roleOne = roleGroupRS.getInt("role_One");
				roleTwo = roleGroupRS.getInt("role_Two");
				roleThree = roleGroupRS.getInt("role_Three");
				roleMapBean.setActive(roleGroupRS.getBoolean("active"));
				roleMapBean.setRoleGroup(roleGroupRS.getString("role_Group"));
				roleMapBean.setRoleMapId(roleGroupRS.getInt("role_Map_ID"));
				
				ResultSet roleOneRs = databaseManager.getData("select * from role where role_ID = '"+roleOne+"'");
				while (roleOneRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleOneName = roleOneRs.getString("role");
					roleBean.setRole(roleOneName);
					roleBean.setRoleID(roleOneRs.getInt("role_ID"));
					roleBean.setDescription(roleOneRs.getString("role_Description"));
					roleBean.setActive(roleOneRs.getBoolean("active"));
					
					roleMapBean.setRoleOne(roleBean);
				}

				ResultSet roleTwoRs = databaseManager.getData("select * from role where role_ID = '"+roleTwo+"'");
				while (roleTwoRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleTwoName = roleTwoRs.getString("role");
					roleBean.setRole(roleTwoName);
					roleBean.setRoleID(roleTwoRs.getInt("role_ID"));
					roleBean.setDescription(roleTwoRs.getString("role_Description"));
					roleBean.setActive(roleTwoRs.getBoolean("active"));

					roleMapBean.setRoleTwo(roleBean);
				}

				ResultSet roleThreeRs = databaseManager.getData("select * from role where role_ID = '"+roleThree+"'");
				while (roleThreeRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleThreeName = roleThreeRs.getString("role");
					roleBean.setRole(roleThreeName);
					roleBean.setRoleID(roleThreeRs.getInt("role_ID"));
					roleBean.setDescription(roleThreeRs.getString("role_Description"));
					roleBean.setActive(roleThreeRs.getBoolean("active"));

					roleMapBean.setRoleThree(roleBean);
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, null,e);
		}
		return roleMapBean;
	}

	public List<RoleMapBean> getAllGroups() {
		List<RoleMapBean> formattedGroups = new ArrayList<>();

		int roleOne = 0;
		int roleTwo = 0;
		int roleThree = 0;

		try {
			ResultSet roleGroupRS = databaseManager.getData("select * from role_Map");
			while (roleGroupRS.next()) {
				RoleMapBean roleMapBean = new RoleMapBean();
				roleOne = roleGroupRS.getInt("role_One");
				roleTwo = roleGroupRS.getInt("role_Two");
				roleThree = roleGroupRS.getInt("role_Three");
				roleMapBean.setActive(roleGroupRS.getBoolean("active"));
				roleMapBean.setRoleGroup(roleGroupRS.getString("role_Group"));
				roleMapBean.setRoleMapId(roleGroupRS.getInt("role_Map_ID"));

				ResultSet roleOneRs = databaseManager.getData("select * from role where role_ID = '"+roleOne+"'");
				while (roleOneRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleOneName = roleOneRs.getString("role");
					roleBean.setRole(roleOneName);
					roleBean.setRoleID(roleOneRs.getInt("role_ID"));
					roleBean.setDescription(roleOneRs.getString("role_Description"));
					roleBean.setActive(roleOneRs.getBoolean("active"));

					roleMapBean.setRoleOne(roleBean);
				}

				ResultSet roleTwoRs = databaseManager.getData("select * from role where role_ID = '"+roleTwo+"'");
				while (roleTwoRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleTwoName = roleTwoRs.getString("role");
					roleBean.setRole(roleTwoName);
					roleBean.setRoleID(roleTwoRs.getInt("role_ID"));
					roleBean.setDescription(roleTwoRs.getString("role_Description"));
					roleBean.setActive(roleTwoRs.getBoolean("active"));

					roleMapBean.setRoleTwo(roleBean);
				}

				ResultSet roleThreeRs = databaseManager.getData("select * from role where role_ID = '"+roleThree+"'");
				while (roleThreeRs.next()) {
					RoleBean roleBean = new RoleBean();
					String roleThreeName = roleThreeRs.getString("role");
					roleBean.setRole(roleThreeName);
					roleBean.setRoleID(roleThreeRs.getInt("role_ID"));
					roleBean.setDescription(roleThreeRs.getString("role_Description"));
					roleBean.setActive(roleThreeRs.getBoolean("active"));

					roleMapBean.setRoleThree(roleBean);
				}

				formattedGroups.add(roleMapBean);
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleManager.class.getName()).log(Level.SEVERE, null,e);
		}

		return formattedGroups;
	}
}
