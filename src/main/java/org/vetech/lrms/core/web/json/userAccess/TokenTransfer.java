package org.vetech.lrms.core.web.json.userAccess;

/**
 * Created by alex on 2/10/15.
 */
public class TokenTransfer {
	private final String token;

	public TokenTransfer(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
