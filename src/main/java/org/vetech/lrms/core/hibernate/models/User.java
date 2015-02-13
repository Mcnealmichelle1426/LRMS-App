package org.vetech.lrms.core.hibernate.models;
// Generated Feb 10, 2015 12:59:31 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer userId;
     private Locale locale;
     private Person person;
     private String userName;
     private String password;
     private String securityQuestion;
     private String securityAnswer;
     private String oathToken;
     private Boolean status;
     private String uuid;
     private Set utilities = new HashSet(0);
     private Set clientPayments = new HashSet(0);
     private Set emails = new HashSet(0);
     private Set schedulers = new HashSet(0);

    public User() {
    }

	
    public User(Locale locale, Person person, String userName, String password, String securityQuestion, String securityAnswer, String oathToken, String uuid) {
        this.locale = locale;
        this.person = person;
        this.userName = userName;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.oathToken = oathToken;
        this.uuid = uuid;
    }
    public User(Locale locale, Person person, String userName, String password, String securityQuestion, String securityAnswer, String oathToken, Boolean status, String uuid, Set utilities, Set clientPayments, Set emails, Set schedulers) {
       this.locale = locale;
       this.person = person;
       this.userName = userName;
       this.password = password;
       this.securityQuestion = securityQuestion;
       this.securityAnswer = securityAnswer;
       this.oathToken = oathToken;
       this.status = status;
       this.uuid = uuid;
       this.utilities = utilities;
       this.clientPayments = clientPayments;
       this.emails = emails;
       this.schedulers = schedulers;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Locale getLocale() {
        return this.locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSecurityQuestion() {
        return this.securityQuestion;
    }
    
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    public String getSecurityAnswer() {
        return this.securityAnswer;
    }
    
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    public String getOathToken() {
        return this.oathToken;
    }
    
    public void setOathToken(String oathToken) {
        this.oathToken = oathToken;
    }
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getUuid() {
        return this.uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public Set getUtilities() {
        return this.utilities;
    }
    
    public void setUtilities(Set utilities) {
        this.utilities = utilities;
    }
    public Set getClientPayments() {
        return this.clientPayments;
    }
    
    public void setClientPayments(Set clientPayments) {
        this.clientPayments = clientPayments;
    }
    public Set getEmails() {
        return this.emails;
    }
    
    public void setEmails(Set emails) {
        this.emails = emails;
    }
    public Set getSchedulers() {
        return this.schedulers;
    }
    
    public void setSchedulers(Set schedulers) {
        this.schedulers = schedulers;
    }




}

