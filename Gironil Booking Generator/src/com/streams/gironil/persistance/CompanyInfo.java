/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streams.gironil.persistance;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shehata.Ibrahim
 */
@Entity
@Table(name = "COMPANY_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyInfo.findAll", query = "SELECT c FROM CompanyInfo c"),
    @NamedQuery(name = "CompanyInfo.findByCode", query = "SELECT c FROM CompanyInfo c WHERE c.code = :code"),
    @NamedQuery(name = "CompanyInfo.findByName", query = "SELECT c FROM CompanyInfo c WHERE c.name = :name"),
    @NamedQuery(name = "CompanyInfo.findByTempColumn", query = "SELECT c FROM CompanyInfo c WHERE c.tempColumn = :tempColumn")})
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Column(name = "TEMP_COLUMN")
    private String tempColumn;

    public CompanyInfo() {
    }

    public CompanyInfo(String code) {
        this.code = code;
    }

    public CompanyInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempColumn() {
        return tempColumn;
    }

    public void setTempColumn(String tempColumn) {
        this.tempColumn = tempColumn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyInfo)) {
            return false;
        }
        CompanyInfo other = (CompanyInfo) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.streams.gironil.persistance.CompanyInfo[ code=" + code + " ]";
    }
    
}
