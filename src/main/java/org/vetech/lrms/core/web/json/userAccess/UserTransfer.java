package org.vetech.lrms.core.web.json.userAccess;

import org.vetech.lrms.core.web.json.bean.RoleMapBean;

/**
 * Created by alex on 2/10/15.
 */
public class UserTransfer {
	private final String name;

	private final RoleMapBean roles;

	public UserTransfer(String name, RoleMapBean roles) {
		this.name = name;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public RoleMapBean getRoles() {
		return roles;
	}
}
