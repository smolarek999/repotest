/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestPlan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestPlan.findAll", query = "SELECT t FROM TestPlan t")})
public class TestPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestPlan")
    private Integer idTestPlan;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "creationDate")
    private String creationDate;
    @JoinTable(name = "TestPlan_has_Feature", joinColumns = {
        @JoinColumn(name = "TestPlan_idTestPlan", referencedColumnName = "idTestPlan")}, inverseJoinColumns = {
        @JoinColumn(name = "Feature_idFeature", referencedColumnName = "idFeature")})
    @ManyToMany
    private Collection<Feature> featureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testPlanidTestPlan")
    private Collection<TestPlanExecution> testPlanExecutionCollection;

    public TestPlan() {
    }

    public TestPlan(Integer idTestPlan) {
        this.idTestPlan = idTestPlan;
    }

    public Integer getIdTestPlan() {
        return idTestPlan;
    }

    public void setIdTestPlan(Integer idTestPlan) {
        this.idTestPlan = idTestPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Collection<Feature> getFeatureCollection() {
        return featureCollection;
    }

    public void setFeatureCollection(Collection<Feature> featureCollection) {
        this.featureCollection = featureCollection;
    }

    @XmlTransient
    public Collection<TestPlanExecution> getTestPlanExecutionCollection() {
        return testPlanExecutionCollection;
    }

    public void setTestPlanExecutionCollection(Collection<TestPlanExecution> testPlanExecutionCollection) {
        this.testPlanExecutionCollection = testPlanExecutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestPlan != null ? idTestPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestPlan)) {
            return false;
        }
        TestPlan other = (TestPlan) object;
        if ((this.idTestPlan == null && other.idTestPlan != null) || (this.idTestPlan != null && !this.idTestPlan.equals(other.idTestPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestPlan[ idTestPlan=" + idTestPlan + " ]";
    }
    
}
