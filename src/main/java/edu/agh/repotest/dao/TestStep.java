/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "TestStep")
@NamedQueries({
    @NamedQuery(name = "TestStep.findAll", query = "SELECT t FROM TestStep t")})
public class TestStep extends EnityWithCondition {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestStep")
    private Integer idTestStep;
    @Size(max = 511)
    @Column(name = "Description")
    private String description;
    @Size(max = 511)
    @Column(name = "ExpectedResult")
    private String expectedResult;
    @Size(max = 45)
    @Column(name = "ConditionCol")
    @XmlTransient
    private String condition;
    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testStep")
    private Collection<TestExecutionStep> testExecutionhasTestStepCollection;
    @XmlTransient
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testidTest;

    public TestStep() {
    }

    public TestStep(Integer idTestStep) {
        this.idTestStep = idTestStep;
    }

    @Override
    public String getConditionalId() {
        return "S" + idTestStep;
    }

    
    public String getDescription() {
        System.out.println("TESTSTEP" + "getDescription" + description);
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectedResult() {
        System.out.println("TESTSTEP" + "getExpectedResult" + expectedResult);
        return expectedResult;
    }

    @Override
    public String getConditionInner() {
        return condition;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public String getCondition() {
        System.out.println("TESTSTEP" + "getCondition");
        return condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getIdTestStep() {
        System.out.println("TESTSTEP" + "getIdTestStep");
        return idTestStep;
    }

    public void setIdTestStep(Integer idTestStep) {
        this.idTestStep = idTestStep;
    }

    @XmlTransient
    public Collection<TestExecutionStep> getTestExecutionhasTestStepCollection() {
        System.out.println("TESTSTEP" + "getTestExecutionhasTestStepCollection");
        return testExecutionhasTestStepCollection;
    }

    public void setTestExecutionhasTestStepCollection(Collection<TestExecutionStep> testExecutionhasTestStepCollection) {
        this.testExecutionhasTestStepCollection = testExecutionhasTestStepCollection;
    }

    public Test getTestidTest() {
        System.out.println("TESTSTEP" + "getTestidTest");

        return testidTest;
    }

    public void setTestidTest(Test testidTest) {
        this.testidTest = testidTest;
    }

    @Override
    public int hashCode() {
        System.out.println("TESTSTEP" + "hashCode");
        int hash = 0;
        hash += (idTestStep != null ? idTestStep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        System.out.println("TESTSTEP" + "equals");
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
    public String getConditionalDesc() {
        return "TestStep: " + getDescription();
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestStep[ idTestStep=" + idTestStep + " ]";
    }
}
