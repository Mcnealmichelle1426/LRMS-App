package org.vetech.lrms.core.web.json.bean;

/**
 * Created by nessy on 05/02/15.
 */
public class EmployeeBean {
    int employeeID = 0;
    PersonBean personBean = null;
    JobTitleBean jobTitleBean = null;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    public JobTitleBean getJobTitleBean() {
        return jobTitleBean;
    }

    public void setJobTitleBean(JobTitleBean jobTitleBean) {
        this.jobTitleBean = jobTitleBean;
    }
}
