/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Shehata.Ibrahim
 */
@Entity
@Table(name = "AUTHORIZATION_REQUEST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorizationRequest.findAll", query = "SELECT a FROM AuthorizationRequest a"),
    @NamedQuery(name = "AuthorizationRequest.findByAuthorizationKey", query = "SELECT a FROM AuthorizationRequest a WHERE a.authorizationKey = :authorizationKey"),
    @NamedQuery(name = "AuthorizationRequest.findByBatchKey", query = "SELECT a FROM AuthorizationRequest a WHERE a.batchKey = :batchKey"),
    @NamedQuery(name = "AuthorizationRequest.findByBic", query = "SELECT a FROM AuthorizationRequest a WHERE a.bic = :bic"),
    @NamedQuery(name = "AuthorizationRequest.findByCreationDatetime", query = "SELECT a FROM AuthorizationRequest a WHERE a.creationDatetime = :creationDatetime"),
    @NamedQuery(name = "AuthorizationRequest.findByLastRequestDateTime", query = "SELECT a FROM AuthorizationRequest a WHERE a.lastRequestDateTime = :lastRequestDateTime"),
    @NamedQuery(name = "AuthorizationRequest.findByStatus", query = "SELECT a FROM AuthorizationRequest a WHERE a.status = :status"),
    @NamedQuery(name = "AuthorizationRequest.findByResponseAmount", query = "SELECT a FROM AuthorizationRequest a WHERE a.responseAmount = :responseAmount"),
    @NamedQuery(name = "AuthorizationRequest.findByResponse", query = "SELECT a FROM AuthorizationRequest a WHERE a.response = :response"),
    @NamedQuery(name = "AuthorizationRequest.findByResponseDatetime", query = "SELECT a FROM AuthorizationRequest a WHERE a.responseDatetime = :responseDatetime"),
    @NamedQuery(name = "AuthorizationRequest.findByEmployee", query = "SELECT a FROM AuthorizationRequest a WHERE a.employee = :employee"),
    @NamedQuery(name = "AuthorizationRequest.findByRefusalReason", query = "SELECT a FROM AuthorizationRequest a WHERE a.refusalReason = :refusalReason")})
public class AuthorizationRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AUTHORIZATION_KEY")
    private Long authorizationKey;
    @Basic(optional = false)
    @Column(name = "BATCH_KEY")
    private long batchKey;
    @Basic(optional = false)
    private String bic;
    @Basic(optional = false)
    @Column(name = "CREATION_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDatetime;
    @Basic(optional = false)
    @Column(name = "LAST_REQUEST_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRequestDateTime;
    @Basic(optional = false)
    private String status;
    @Column(name = "RESPONSE_AMOUNT")
    private BigInteger responseAmount;
    private String response;
    @Column(name = "RESPONSE_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDatetime;
    @Basic(optional = false)
    private String employee;
    @Column(name = "REFUSAL_REASON")
    private String refusalReason;
    @OneToMany(mappedBy = "authorizationKey")
    private Collection<Batch> batchCollection;

    public AuthorizationRequest() {
    }

    public AuthorizationRequest(Long authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public AuthorizationRequest(Long authorizationKey, long batchKey, String bic, Date creationDatetime, Date lastRequestDateTime, String status, String employee) {
        this.authorizationKey = authorizationKey;
        this.batchKey = batchKey;
        this.bic = bic;
        this.creationDatetime = creationDatetime;
        this.lastRequestDateTime = lastRequestDateTime;
        this.status = status;
        this.employee = employee;
    }

    public Long getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(Long authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public long getBatchKey() {
        return batchKey;
    }

    public void setBatchKey(long batchKey) {
        this.batchKey = batchKey;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Date getLastRequestDateTime() {
        return lastRequestDateTime;
    }

    public void setLastRequestDateTime(Date lastRequestDateTime) {
        this.lastRequestDateTime = lastRequestDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getResponseAmount() {
        return responseAmount;
    }

    public void setResponseAmount(BigInteger responseAmount) {
        this.responseAmount = responseAmount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getResponseDatetime() {
        return responseDatetime;
    }

    public void setResponseDatetime(Date responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getRefusalReason() {
        return refusalReason;
    }

    public void setRefusalReason(String refusalReason) {
        this.refusalReason = refusalReason;
    }

    @XmlTransient
    public Collection<Batch> getBatchCollection() {
        return batchCollection;
    }

    public void setBatchCollection(Collection<Batch> batchCollection) {
        this.batchCollection = batchCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorizationKey != null ? authorizationKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorizationRequest)) {
            return false;
        }
        AuthorizationRequest other = (AuthorizationRequest) object;
        if ((this.authorizationKey == null && other.authorizationKey != null) || (this.authorizationKey != null && !this.authorizationKey.equals(other.authorizationKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.persistance.AuthorizationRequest[ authorizationKey=" + authorizationKey + " ]";
    }
    
}
