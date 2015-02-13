package org.vetech.lrms.core.web.json.response;

/**
 * Created by alex on 2/9/15.
 */
public class SystemResponse {
	String message = "";
	boolean status = false;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
