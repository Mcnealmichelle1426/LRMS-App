package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/8/15.
 */
public class RoleBean {
	int roleID = 0;
	String role = "";
	String description = "";
	boolean active = false;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
