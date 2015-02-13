package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 05/02/15.
 */
public class JobTitleBean {
//    PersonBean personBean = null;
    int jobTitleID = 0;
    String jobName = "";
    int basicSalary = 0;
//    RoleBean roleBean = null;
    boolean active = false;

//    public PersonBean getPersonBean() {
//        return personBean;
//    }
//
//    public void setPersonBean(PersonBean personBean) {
//        this.personBean = personBean;
//    }

    public int getJobTitleID() {
        return jobTitleID;
    }

    public void setJobTitleID(int jobTitleID) {
        this.jobTitleID = jobTitleID;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

//    public RoleBean getRoleBean() {
//        return roleBean;
//    }
//
//    public void setRoleBean(RoleBean roleBean) {
//        this.roleBean = roleBean;
//    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
