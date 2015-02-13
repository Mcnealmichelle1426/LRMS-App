package org.vetech.lrms.core.web.json.bean;

import java.util.Date;

/**
 * Created by nessy on 06/02/15.
 */
public class UserBean {
    int userID = 0;
    PersonBean personBean = null;
    String userName = "";
    String password = "";
    String securityQuestion = "";
    String securityAnswer = "";
    String oathToken = "";
    LocaleBean localeBean = null;
    boolean active = false;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getOathToken() {
        return oathToken;
    }

    public void setOathToken(String oathToken) {
        this.oathToken = oathToken;
    }

    public LocaleBean getLocaleBean() {
        return localeBean;
    }

    public void setLocaleBean(LocaleBean localeBean) {
        this.localeBean = localeBean;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
