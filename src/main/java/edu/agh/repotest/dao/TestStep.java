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
import javax.persistence.ManyToOne;
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
@Table(name = "TestStep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestStep.findAll", query = "SELECT t FROM TestStep t")})
public class TestStep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "Condition")
    private String condition;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestStep")
    private Integer idTestStep;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testStep")
    private Collection<TestExecutionhasTestStep> testExecutionhasTestStepCollection;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testidTest;

    public TestStep() {
    }

    public TestStep(Integer idTestStep) {
        this.idTestStep = idTestStep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getIdTestStep() {
        return idTestStep;
    }

    public void setIdTestStep(Integer idTestStep) {
        this.idTestStep = idTestStep;
    }

    @XmlTransient
    public Collection<TestExecutionhasTestStep> getTestExecutionhasTestStepCollection() {
        return testExecutionhasTestStepCollection;
    }

    public void setTestExecutionhasTestStepCollection(Collection<TestExecutionhasTestStep> testExecutionhasTestStepCollection) {
        this.testExecutionhasTestStepCollection = testExecutionhasTestStepCollection;
    }

    public Test getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(Test testidTest) {
        this.testidTest = testidTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestStep != null ? idTestStep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestStep)) {
            return false;
        }
        TestStep other = (TestStep) object;
        if ((this.idTestStep == null && other.idTestStep != null) || (this.idTestStep != null && !this.idTestStep.equals(other.idTestStep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestStep[ idTestStep=" + idTestStep + " ]";
    }
    
}
