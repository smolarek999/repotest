/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

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
    @NamedQuery(name = "LoadTable.findAll", query = "SELECT l FROM LoadTable l")})
public class LoadTable implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loadTableReleaseidRelease")
    private Collection<TestPlanExecution> testPlanExecutionCollection;
    @JoinColumn(name = "Release_idRelease", referencedColumnName = "idRelease", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ReleaseTable releaseTable;

    public LoadTable() {
    }

    public LoadTable(Integer releaseidRelease) {
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

    @XmlTransient
    public Collection<TestPlanExecution> getTestPlanExecutionCollection() {
        return testPlanExecutionCollection;
    }

    public void setTestPlanExecutionCollection(Collection<TestPlanExecution> testPlanExecutionCollection) {
        this.testPlanExecutionCollection = testPlanExecutionCollection;
    }

    public ReleaseTable getReleaseTable() {
        return releaseTable;
    }

    public void setReleaseTable(ReleaseTable releaseTable) {
        this.releaseTable = releaseTable;
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
        if (!(object instanceof LoadTable)) {
            return false;
        }
        LoadTable other = (LoadTable) object;
        if ((this.releaseidRelease == null && other.releaseidRelease != null) || (this.releaseidRelease != null && !this.releaseidRelease.equals(other.releaseidRelease))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.LoadTable[ releaseidRelease=" + releaseidRelease + " ]";
    }
    
}
