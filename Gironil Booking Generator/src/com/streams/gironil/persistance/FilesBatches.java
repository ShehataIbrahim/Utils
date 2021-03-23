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
@Table(name = "FILES_BATCHES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilesBatches.findAll", query = "SELECT f FROM FilesBatches f"),
    @NamedQuery(name = "FilesBatches.findByFileName", query = "SELECT f FROM FilesBatches f WHERE f.fileName = :fileName"),
    @NamedQuery(name = "FilesBatches.findByBatchId", query = "SELECT f FROM FilesBatches f WHERE f.batchId = :batchId"),
    @NamedQuery(name = "FilesBatches.findByInsertionTime", query = "SELECT f FROM FilesBatches f WHERE f.insertionTime = :insertionTime")})
public class FilesBatches implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "FILE_NAME")
    private String fileName;
    @Id
    @Basic(optional = false)
    @Column(name = "BATCH_ID")
    private String batchId;
    @Basic(optional = false)
    @Column(name = "INSERTION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertionTime;

    public FilesBatches() {
    }

    public FilesBatches(String batchId) {
        this.batchId = batchId;
    }

    public FilesBatches(String batchId, String fileName, Date insertionTime) {
        this.batchId = batchId;
        this.fileName = fileName;
        this.insertionTime = insertionTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Date getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Date insertionTime) {
        this.insertionTime = insertionTime;
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
        if (!(object instanceof FilesBatches)) {
            return false;
        }
        FilesBatches other = (FilesBatches) object;
        if ((this.batchId == null && other.batchId != null) || (this.batchId != null && !this.batchId.equals(other.batchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.persistance.FilesBatches[ batchId=" + batchId + " ]";
    }
    
}
