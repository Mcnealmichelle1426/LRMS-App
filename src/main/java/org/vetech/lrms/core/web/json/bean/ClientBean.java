package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 05/02/15.
 */
public class ClientBean {
    int clientID = 0;
    PersonBean personBean = null;
    ClientConfigBean clientConfigBean = null;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public ClientConfigBean getClientConfigBean() {
        return clientConfigBean;
    }

    public void setClientConfigBean(ClientConfigBean clientConfigBean) {
        this.clientConfigBean = clientConfigBean;
    }
}
