package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/4/15.
 */
public class IdTypeBean {
	int typeID = 0;
	String typeName = "";
	String typeCode = "";
	boolean active = false;

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
