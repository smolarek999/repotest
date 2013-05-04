/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pawel
 */
@Embeddable
public class TestExecutionhasTestStepPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "TestExecution_idTestExecution")
    private int testExecutionidTestExecution;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TestStep_idTestStep")
    private int testStepidTestStep;

    public TestExecutionhasTestStepPK() {
    }

    public TestExecutionhasTestStepPK(int testExecutionidTestExecution, int testStepidTestStep) {
        this.testExecutionidTestExecution = testExecutionidTestExecution;
        this.testStepidTestStep = testStepidTestStep;
    }

    public int getTestExecutionidTestExecution() {
        return testExecutionidTestExecution;
    }

    public void setTestExecutionidTestExecution(int testExecutionidTestExecution) {
        this.testExecutionidTestExecution = testExecutionidTestExecution;
    }

    public int getTestStepidTestStep() {
        return testStepidTestStep;
    }

    public void setTestStepidTestStep(int testStepidTestStep) {
        this.testStepidTestStep = testStepidTestStep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) testExecutionidTestExecution;
        hash += (int) testStepidTestStep;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestExecutionhasTestStepPK)) {
            return false;
        }
        TestExecutionhasTestStepPK other = (TestExecutionhasTestStepPK) object;
        if (this.testExecutionidTestExecution != other.testExecutionidTestExecution) {
            return false;
        }
        if (this.testStepidTestStep != other.testStepidTestStep) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecutionhasTestStepPK[ testExecutionidTestExecution=" + testExecutionidTestExecution + ", testStepidTestStep=" + testStepidTestStep + " ]";
    }
    
}
