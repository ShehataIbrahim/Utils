/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shehata.Ibrahim
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batch.findAll", query = "SELECT b FROM Batch b"),
    @NamedQuery(name = "Batch.findByBatchKey", query = "SELECT b FROM Batch b WHERE b.batchKey = :batchKey"),
    @NamedQuery(name = "Batch.findByGroupId", query = "SELECT b FROM Batch b WHERE b.groupId = :groupId"),
    @NamedQuery(name = "Batch.findByBatchType", query = "SELECT b FROM Batch b WHERE b.batchType = :batchType"),
    @NamedQuery(name = "Batch.findByCreationDatetime", query = "SELECT b FROM Batch b WHERE b.creationDatetime = :creationDatetime"),
    @NamedQuery(name = "Batch.findByControlSum", query = "SELECT b FROM Batch b WHERE b.controlSum = :controlSum"),
    @NamedQuery(name = "Batch.findByBatchBooking", query = "SELECT b FROM Batch b WHERE b.batchBooking = :batchBooking"),
    @NamedQuery(name = "Batch.findByNrOfTransactions", query = "SELECT b FROM Batch b WHERE b.nrOfTransactions = :nrOfTransactions"),
    @NamedQuery(name = "Batch.findByGrouping", query = "SELECT b FROM Batch b WHERE b.grouping = :grouping"),
    @NamedQuery(name = "Batch.findByInitiatingParty", query = "SELECT b FROM Batch b WHERE b.initiatingParty = :initiatingParty"),
    @NamedQuery(name = "Batch.findByReqExecutionDate", query = "SELECT b FROM Batch b WHERE b.reqExecutionDate = :reqExecutionDate"),
    @NamedQuery(name = "Batch.findByOriginatingFile", query = "SELECT b FROM Batch b WHERE b.originatingFile = :originatingFile"),
    @NamedQuery(name = "Batch.findByInterbankValDate", query = "SELECT b FROM Batch b WHERE b.interbankValDate = :interbankValDate"),
    @NamedQuery(name = "Batch.findByIntStatus", query = "SELECT b FROM Batch b WHERE b.intStatus = :intStatus"),
    @NamedQuery(name = "Batch.findByExtStatus", query = "SELECT b FROM Batch b WHERE b.extStatus = :extStatus"),
    @NamedQuery(name = "Batch.findByExtStatusReason", query = "SELECT b FROM Batch b WHERE b.extStatusReason = :extStatusReason"),
    @NamedQuery(name = "Batch.findByValKey", query = "SELECT b FROM Batch b WHERE b.valKey = :valKey"),
    @NamedQuery(name = "Batch.findByInsertionDatetime", query = "SELECT b FROM Batch b WHERE b.insertionDatetime = :insertionDatetime"),
    @NamedQuery(name = "Batch.findByLooseFlow", query = "SELECT b FROM Batch b WHERE b.looseFlow = :looseFlow"),
    @NamedQuery(name = "Batch.findByReadyForArchiving", query = "SELECT b FROM Batch b WHERE b.readyForArchiving = :readyForArchiving"),
    @NamedQuery(name = "Batch.findByReadyForDeletion", query = "SELECT b FROM Batch b WHERE b.readyForDeletion = :readyForDeletion"),
    @NamedQuery(name = "Batch.findByAuthenticationMethod", query = "SELECT b FROM Batch b WHERE b.authenticationMethod = :authenticationMethod")})
public class Batch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BATCH_KEY")
    private Long batchKey;
    @Basic(optional = false)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Basic(optional = false)
    @Column(name = "BATCH_TYPE")
    private String batchType;
    @Basic(optional = false)
    @Column(name = "CREATION_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDatetime;
    @Basic(optional = false)
    @Column(name = "CONTROL_SUM")
    private long controlSum;
    @Basic(optional = false)
    @Column(name = "BATCH_BOOKING")
    private boolean batchBooking;
    @Basic(optional = false)
    @Column(name = "NR_OF_TRANSACTIONS")
    private int nrOfTransactions;
    @Basic(optional = false)
    private boolean grouping;
    @Basic(optional = false)
    @Column(name = "INITIATING_PARTY")
    private String initiatingParty;
    @Column(name = "REQ_EXECUTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reqExecutionDate;
    @Basic(optional = false)
    @Column(name = "ORIGINATING_FILE")
    private String originatingFile;
    @Column(name = "INTERBANK_VAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date interbankValDate;
    @Basic(optional = false)
    @Column(name = "INT_STATUS")
    private String intStatus;
    @Column(name = "EXT_STATUS")
    private String extStatus;
    @Column(name = "EXT_STATUS_REASON")
    private String extStatusReason;
    @Column(name = "VAL_KEY")
    private BigInteger valKey;
    @Basic(optional = false)
    @Column(name = "INSERTION_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertionDatetime;
    @Column(name = "LOOSE_FLOW")
    private Boolean looseFlow;
    @Column(name = "READY_FOR_ARCHIVING")
    private Boolean readyForArchiving;
    @Column(name = "READY_FOR_DELETION")
    private Boolean readyForDeletion;
    @Column(name = "AUTHENTICATION_METHOD")
    private String authenticationMethod;
    @JoinColumn(name = "AUTHORIZATION_KEY", referencedColumnName = "AUTHORIZATION_KEY")
    @ManyToOne
    private AuthorizationRequest authorizationKey;

    public Batch() {
    }

    public Batch(Long batchKey) {
        this.batchKey = batchKey;
    }

    public Batch(Long batchKey, String groupId, String batchType, Date creationDatetime, long controlSum, boolean batchBooking, int nrOfTransactions, boolean grouping, String initiatingParty, String originatingFile, String intStatus, Date insertionDatetime) {
        this.batchKey = batchKey;
        this.groupId = groupId;
        this.batchType = batchType;
        this.creationDatetime = creationDatetime;
        this.controlSum = controlSum;
        this.batchBooking = batchBooking;
        this.nrOfTransactions = nrOfTransactions;
        this.grouping = grouping;
        this.initiatingParty = initiatingParty;
        this.originatingFile = originatingFile;
        this.intStatus = intStatus;
        this.insertionDatetime = insertionDatetime;
    }

    public Long getBatchKey() {
        return batchKey;
    }

    public void setBatchKey(Long batchKey) {
        this.batchKey = batchKey;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public long getControlSum() {
        return controlSum;
    }

    public void setControlSum(long controlSum) {
        this.controlSum = controlSum;
    }

    public boolean getBatchBooking() {
        return batchBooking;
    }

    public void setBatchBooking(boolean batchBooking) {
        this.batchBooking = batchBooking;
    }

    public int getNrOfTransactions() {
        return nrOfTransactions;
    }

    public void setNrOfTransactions(int nrOfTransactions) {
        this.nrOfTransactions = nrOfTransactions;
    }

    public boolean getGrouping() {
        return grouping;
    }

    public void setGrouping(boolean grouping) {
        this.grouping = grouping;
    }

    public String getInitiatingParty() {
        return initiatingParty;
    }

    public void setInitiatingParty(String initiatingParty) {
        this.initiatingParty = initiatingParty;
    }

    public Date getReqExecutionDate() {
        return reqExecutionDate;
    }

    public void setReqExecutionDate(Date reqExecutionDate) {
        this.reqExecutionDate = reqExecutionDate;
    }

    public String getOriginatingFile() {
        return originatingFile;
    }

    public void setOriginatingFile(String originatingFile) {
        this.originatingFile = originatingFile;
    }

    public Date getInterbankValDate() {
        return interbankValDate;
    }

    public void setInterbankValDate(Date interbankValDate) {
        this.interbankValDate = interbankValDate;
    }

    public String getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(String intStatus) {
        this.intStatus = intStatus;
    }

    public String getExtStatus() {
        return extStatus;
    }

    public void setExtStatus(String extStatus) {
        this.extStatus = extStatus;
    }

    public String getExtStatusReason() {
        return extStatusReason;
    }

    public void setExtStatusReason(String extStatusReason) {
        this.extStatusReason = extStatusReason;
    }

    public BigInteger getValKey() {
        return valKey;
    }

    public void setValKey(BigInteger valKey) {
        this.valKey = valKey;
    }

    public Date getInsertionDatetime() {
        return insertionDatetime;
    }

    public void setInsertionDatetime(Date insertionDatetime) {
        this.insertionDatetime = insertionDatetime;
    }

    public Boolean getLooseFlow() {
        return looseFlow;
    }

    public void setLooseFlow(Boolean looseFlow) {
        this.looseFlow = looseFlow;
    }

    public Boolean getReadyForArchiving() {
        return readyForArchiving;
    }

    public void setReadyForArchiving(Boolean readyForArchiving) {
        this.readyForArchiving = readyForArchiving;
    }

    public Boolean getReadyForDeletion() {
        return readyForDeletion;
    }

    public void setReadyForDeletion(Boolean readyForDeletion) {
        this.readyForDeletion = readyForDeletion;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public AuthorizationRequest getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(AuthorizationRequest authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchKey != null ? batchKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batch)) {
            return false;
        }
        Batch other = (Batch) object;
        if ((this.batchKey == null && other.batchKey != null) || (this.batchKey != null && !this.batchKey.equals(other.batchKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.persistance.Batch[ batchKey=" + batchKey + " ]";
    }
    
}
