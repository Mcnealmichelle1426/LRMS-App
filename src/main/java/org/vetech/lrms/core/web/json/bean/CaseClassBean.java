package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/3/15.
 */
public class CaseClassBean {
	int id = 0;
	String code = "";
	String name = "";
	String description = "";
	boolean active = false;
	boolean ifRoot = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIfRoot() {
		return ifRoot;
	}

	public void setIfRoot(boolean ifRoot) {
		this.ifRoot = ifRoot;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
