package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/8/15.
 */
public class RoleMapBean {
	int roleMapId = 0;
	String roleGroup = "";
	RoleBean roleOne = null;
	RoleBean roleTwo = null;
	RoleBean roleThree = null;
	boolean active = false;

	public int getRoleMapId() {
		return roleMapId;
	}

	public void setRoleMapId(int roleMapId) {
		this.roleMapId = roleMapId;
	}

	public RoleBean getRoleOne() {
		return roleOne;
	}

	public void setRoleOne(RoleBean roleOne) {
		this.roleOne = roleOne;
	}

	public RoleBean getRoleTwo() {
		return roleTwo;
	}

	public void setRoleTwo(RoleBean roleTwo) {
		this.roleTwo = roleTwo;
	}

	public RoleBean getRoleThree() {
		return roleThree;
	}

	public void setRoleThree(RoleBean roleThree) {
		this.roleThree = roleThree;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
}
