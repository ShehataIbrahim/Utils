/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shehata.Ibrahim
 */
@Entity
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionId", query = "SELECT t FROM Transactions t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transactions.findByCompanyCode", query = "SELECT t FROM Transactions t WHERE t.companyCode = :companyCode"),
    @NamedQuery(name = "Transactions.findByCreationDate", query = "SELECT t FROM Transactions t WHERE t.creationDate = :creationDate"),
    @NamedQuery(name = "Transactions.findByAccountNumber", query = "SELECT t FROM Transactions t WHERE t.accountNumber = :accountNumber"),
    @NamedQuery(name = "Transactions.findByCustomerName", query = "SELECT t FROM Transactions t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "Transactions.findByCustomerReference", query = "SELECT t FROM Transactions t WHERE t.customerReference = :customerReference"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByBicCode", query = "SELECT t FROM Transactions t WHERE t.bicCode = :bicCode"),
    @NamedQuery(name = "Transactions.findByBranchCode", query = "SELECT t FROM Transactions t WHERE t.branchCode = :branchCode")})
public class Transactions implements Serializable {
    @Basic(optional = false)
    @Column(name = "BATCH_ID")
    private String batchId;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_SERIAL")
    private int transactionSerial;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @Basic(optional = false)
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Basic(optional = false)
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "CUSTOMER_REFERENCE")
    private String customerReference;
    @Basic(optional = false)
    @Column(name = "AMOUNT")
    private float amount;
    @Basic(optional = false)
    @Column(name = "BIC_CODE")
    private String bicCode;
    @Column(name = "BRANCH_CODE")
    private String branchCode;

    public Transactions() {
    }

    public Transactions(String transactionId) {
        this.transactionId = transactionId;
    }

    public Transactions(String transactionId, String companyCode, Date creationDate, String accountNumber, String customerName, String customerReference, float amount, String bicCode) {
        this.transactionId = transactionId;
        this.companyCode = companyCode;
        this.creationDate = creationDate;
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.customerReference = customerReference;
        this.amount = amount;
        this.bicCode = bicCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.Transactions[ transactionId=" + transactionId + " ]";
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getTransactionSerial() {
        return transactionSerial;
    }

    public void setTransactionSerial(int transactionSerial) {
        this.transactionSerial = transactionSerial;
    }
    
}
