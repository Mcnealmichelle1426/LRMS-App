package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 05/02/15.
 */
public class ClientConfigBean {
    int clientTypeId = 0;
    String clientTypeName = "";
    String clientTypeCode = "";
    int Boolean = 1;

    public int getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(int clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public String getClientTypeName() {
        return clientTypeName;
    }

    public void setClientTypeName(String clientTypeName) {
        this.clientTypeName = clientTypeName;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public int getBoolean() {
        return Boolean;
    }

    public void setBoolean(int aBoolean) {
        Boolean = aBoolean;
    }
}
