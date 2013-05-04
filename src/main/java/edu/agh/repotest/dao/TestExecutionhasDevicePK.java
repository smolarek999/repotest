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
public class TestExecutionhasDevicePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "TestExecution_idTestExecution")
    private int testExecutionidTestExecution;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Device_idDevice")
    private int deviceidDevice;

    public TestExecutionhasDevicePK() {
    }

    public TestExecutionhasDevicePK(int testExecutionidTestExecution, int deviceidDevice) {
        this.testExecutionidTestExecution = testExecutionidTestExecution;
        this.deviceidDevice = deviceidDevice;
    }

    public int getTestExecutionidTestExecution() {
        return testExecutionidTestExecution;
    }

    public void setTestExecutionidTestExecution(int testExecutionidTestExecution) {
        this.testExecutionidTestExecution = testExecutionidTestExecution;
    }

    public int getDeviceidDevice() {
        return deviceidDevice;
    }

    public void setDeviceidDevice(int deviceidDevice) {
        this.deviceidDevice = deviceidDevice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) testExecutionidTestExecution;
        hash += (int) deviceidDevice;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestExecutionhasDevicePK)) {
            return false;
        }
        TestExecutionhasDevicePK other = (TestExecutionhasDevicePK) object;
        if (this.testExecutionidTestExecution != other.testExecutionidTestExecution) {
            return false;
        }
        if (this.deviceidDevice != other.deviceidDevice) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecutionhasDevicePK[ testExecutionidTestExecution=" + testExecutionidTestExecution + ", deviceidDevice=" + deviceidDevice + " ]";
    }
    
}
