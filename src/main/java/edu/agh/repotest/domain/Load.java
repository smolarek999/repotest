/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Load_Table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Load.findAll", query = "SELECT l FROM Load l")})
public class Load implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Release_idRelease")
    private Integer releaseidRelease;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Size(max = 45)
    @Column(name = "endDate")
    private String endDate;
    @JoinColumn(name = "Release_idRelease", referencedColumnName = "idRelease", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Release release;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loadReleaseidRelease")
    private Collection<TestExecution> testExecutionCollection;

    public Load() {
    }

    public Load(Integer releaseidRelease) {
        this.releaseidRelease = releaseidRelease;
    }

    public Integer getReleaseidRelease() {
        return releaseidRelease;
    }

    public void setReleaseidRelease(Integer releaseidRelease) {
        this.releaseidRelease = releaseidRelease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (releaseidRelease != null ? releaseidRelease.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Load)) {
            return false;
        }
        Load other = (Load) object;
        if ((this.releaseidRelease == null && other.releaseidRelease != null) || (this.releaseidRelease != null && !this.releaseidRelease.equals(other.releaseidRelease))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Load[ releaseidRelease=" + releaseidRelease + " ]";
    }
    
}
