package org.vetech.lrms.core.web.json.bean;

/**
 * Created by alex on 2/6/15.
 */
public class InstructionBean {
	int insID = 0;
	String insName = "";
	String insCode = "";
	String insDesc  = "";
	int insCharge = 0;
	boolean active = false;

	public int getInsID() {
		return insID;
	}

	public void setInsID(int insID) {
		this.insID = insID;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getInsCode() {
		return insCode;
	}

	public void setInsCode(String insCode) {
		this.insCode = insCode;
	}

	public String getInsDesc() {
		return insDesc;
	}

	public void setInsDesc(String insDesc) {
		this.insDesc = insDesc;
	}

	public int getInsCharge() {
		return insCharge;
	}

	public void setInsCharge(int insCharge) {
		this.insCharge = insCharge;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
