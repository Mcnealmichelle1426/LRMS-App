package org.vetech.lrms.core.hibernate.models;
// Generated Feb 10, 2015 12:59:31 PM by Hibernate Tools 3.6.0



/**
 * Salaries generated by hbm2java
 */
public class Salaries  implements java.io.Serializable {


     private int salaryId;
     private Employee employee;
     private int salary;

    public Salaries() {
    }

    public Salaries(int salaryId, Employee employee, int salary) {
       this.salaryId = salaryId;
       this.employee = employee;
       this.salary = salary;
    }
   
    public int getSalaryId() {
        return this.salaryId;
    }
    
    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public int getSalary() {
        return this.salary;
    }
    
    public void setSalary(int salary) {
        this.salary = salary;
    }




}

