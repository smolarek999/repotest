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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestPlanExecution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestPlanExecution.findAll", query = "SELECT t FROM TestPlanExecution t")})
public class TestPlanExecution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestPlanExecution")
    private Integer idTestPlanExecution;
    @ManyToMany(mappedBy = "testPlanExecutionCollection")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strategyExecutionidStrategyExecution")
    private Collection<TestExecution> testExecutionCollection;
    @JoinColumn(name = "Load_Table_Release_idRelease", referencedColumnName = "Release_idRelease")
    @ManyToOne(optional = false)
    private LoadTable loadTableReleaseidRelease;
    @JoinColumn(name = "TestPlan_idTestPlan", referencedColumnName = "idTestPlan")
    @ManyToOne(optional = false)
    private TestPlan testPlanidTestPlan;

    public TestPlanExecution() {
    }

    public TestPlanExecution(Integer idTestPlanExecution) {
        this.idTestPlanExecution = idTestPlanExecution;
    }

    public Integer getIdTestPlanExecution() {
        return idTestPlanExecution;
    }

    public void setIdTestPlanExecution(Integer idTestPlanExecution) {
        this.idTestPlanExecution = idTestPlanExecution;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    public LoadTable getLoadTableReleaseidRelease() {
        return loadTableReleaseidRelease;
    }

    public void setLoadTableReleaseidRelease(LoadTable loadTableReleaseidRelease) {
        this.loadTableReleaseidRelease = loadTableReleaseidRelease;
    }

    public TestPlan getTestPlanidTestPlan() {
        return testPlanidTestPlan;
    }

    public void setTestPlanidTestPlan(TestPlan testPlanidTestPlan) {
        this.testPlanidTestPlan = testPlanidTestPlan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestPlanExecution != null ? idTestPlanExecution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestPlanExecution)) {
            return false;
        }
        TestPlanExecution other = (TestPlanExecution) object;
        if ((this.idTestPlanExecution == null && other.idTestPlanExecution != null) || (this.idTestPlanExecution != null && !this.idTestPlanExecution.equals(other.idTestPlanExecution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestPlanExecution[ idTestPlanExecution=" + idTestPlanExecution + " ]";
    }
    
}
