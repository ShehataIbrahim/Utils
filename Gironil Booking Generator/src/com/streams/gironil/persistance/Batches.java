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
@Table(name = "BATCHES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batches.findAll", query = "SELECT b FROM Batches b"),
    @NamedQuery(name = "Batches.findByBatchId", query = "SELECT b FROM Batches b WHERE b.batchId = :batchId"),
    @NamedQuery(name = "Batches.findByCompanyCode", query = "SELECT b FROM Batches b WHERE b.companyCode = :companyCode"),
    @NamedQuery(name = "Batches.findByCompanyName", query = "SELECT b FROM Batches b WHERE b.companyName = :companyName"),
    @NamedQuery(name = "Batches.findByCreationDate", query = "SELECT b FROM Batches b WHERE b.creationDate = :creationDate"),
    @NamedQuery(name = "Batches.findByNumberOfTransactions", query = "SELECT b FROM Batches b WHERE b.numberOfTransactions = :numberOfTransactions"),
    @NamedQuery(name = "Batches.findByTotalAmount", query = "SELECT b FROM Batches b WHERE b.totalAmount = :totalAmount")})
public class Batches implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BATCH_ID")
    private String batchId;
    @Basic(optional = false)
    @Column(name = "COMPANY_CODE")
    private String companyCode;
    @Basic(optional = false)
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Basic(optional = false)
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "NUMBER_OF_TRANSACTIONS")
    private int numberOfTransactions;
    @Basic(optional = false)
    @Column(name = "TOTAL_AMOUNT")
    private float totalAmount;

    public Batches() {
    }

    public Batches(String batchId) {
        this.batchId = batchId;
    }

    public Batches(String batchId, String companyCode, String companyName, Date creationDate, int numberOfTransactions, float totalAmount) {
        this.batchId = batchId;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.numberOfTransactions = numberOfTransactions;
        this.totalAmount = totalAmount;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(int numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchId != null ? batchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batches)) {
            return false;
        }
        Batches other = (Batches) object;
        if ((this.batchId == null && other.batchId != null) || (this.batchId != null && !this.batchId.equals(other.batchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.Batches[ batchId=" + batchId + " ]";
    }
    
}
