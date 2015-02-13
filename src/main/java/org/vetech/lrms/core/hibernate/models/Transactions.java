package org.vetech.lrms.core.hibernate.models;
// Generated Feb 10, 2015 12:59:31 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Transactions generated by hbm2java
 */
public class Transactions  implements java.io.Serializable {


     private Integer transactionId;
     private Employee employee;
     private EmpExpenditure empExpenditure;
     private String receiptNo;
     private int amount;
     private Date dateOfTransaction;
     private int createdBy;

    public Transactions() {
    }

	
    public Transactions(Employee employee, EmpExpenditure empExpenditure, String receiptNo, int amount, int createdBy) {
        this.employee = employee;
        this.empExpenditure = empExpenditure;
        this.receiptNo = receiptNo;
        this.amount = amount;
        this.createdBy = createdBy;
    }
    public Transactions(Employee employee, EmpExpenditure empExpenditure, String receiptNo, int amount, Date dateOfTransaction, int createdBy) {
       this.employee = employee;
       this.empExpenditure = empExpenditure;
       this.receiptNo = receiptNo;
       this.amount = amount;
       this.dateOfTransaction = dateOfTransaction;
       this.createdBy = createdBy;
    }
   
    public Integer getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public EmpExpenditure getEmpExpenditure() {
        return this.empExpenditure;
    }
    
    public void setEmpExpenditure(EmpExpenditure empExpenditure) {
        this.empExpenditure = empExpenditure;
    }
    public String getReceiptNo() {
        return this.receiptNo;
    }
    
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Date getDateOfTransaction() {
        return this.dateOfTransaction;
    }
    
    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }
    public int getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }




}

