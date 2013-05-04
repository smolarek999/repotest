/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestExecution_has_TestStep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestExecutionhasTestStep.findAll", query = "SELECT t FROM TestExecutionhasTestStep t")})
public class TestExecutionhasTestStep implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TestExecutionhasTestStepPK testExecutionhasTestStepPK;
    @Size(max = 45)
    @Column(name = "Comment")
    private String comment;
    @JoinColumn(name = "TestStep_idTestStep", referencedColumnName = "idTestStep", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TestStep testStep;
    @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TestExecution testExecution;

    public TestExecutionhasTestStep() {
    }

    public TestExecutionhasTestStep(TestExecutionhasTestStepPK testExecutionhasTestStepPK) {
        this.testExecutionhasTestStepPK = testExecutionhasTestStepPK;
    }

    public TestExecutionhasTestStep(int testExecutionidTestExecution, int testStepidTestStep) {
        this.testExecutionhasTestStepPK = new TestExecutionhasTestStepPK(testExecutionidTestExecution, testStepidTestStep);
    }

    public TestExecutionhasTestStepPK getTestExecutionhasTestStepPK() {
        return testExecutionhasTestStepPK;
    }

    public void setTestExecutionhasTestStepPK(TestExecutionhasTestStepPK testExecutionhasTestStepPK) {
        this.testExecutionhasTestStepPK = testExecutionhasTestStepPK;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TestStep getTestStep() {
        return testStep;
    }

    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    public TestExecution getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(TestExecution testExecution) {
        this.testExecution = testExecution;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testExecutionhasTestStepPK != null ? testExecutionhasTestStepPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestExecutionhasTestStep)) {
            return false;
        }
        TestExecutionhasTestStep other = (TestExecutionhasTestStep) object;
        if ((this.testExecutionhasTestStepPK == null && other.testExecutionhasTestStepPK != null) || (this.testExecutionhasTestStepPK != null && !this.testExecutionhasTestStepPK.equals(other.testExecutionhasTestStepPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecutionhasTestStep[ testExecutionhasTestStepPK=" + testExecutionhasTestStepPK + " ]";
    }
    
}
