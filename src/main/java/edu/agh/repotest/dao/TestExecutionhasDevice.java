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
@Table(name = "TestExecution_has_Device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestExecutionhasDevice.findAll", query = "SELECT t FROM TestExecutionhasDevice t")})
public class TestExecutionhasDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TestExecutionhasDevicePK testExecutionhasDevicePK;
    @Size(max = 45)
    @Column(name = "GroupName")
    private String groupName;
    @JoinColumn(name = "Device_idDevice", referencedColumnName = "idDevice", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Device device;
    @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TestExecution testExecution;

    public TestExecutionhasDevice() {
    }

    public TestExecutionhasDevice(TestExecutionhasDevicePK testExecutionhasDevicePK) {
        this.testExecutionhasDevicePK = testExecutionhasDevicePK;
    }

    public TestExecutionhasDevice(int testExecutionidTestExecution, int deviceidDevice) {
        this.testExecutionhasDevicePK = new TestExecutionhasDevicePK(testExecutionidTestExecution, deviceidDevice);
    }

    public TestExecutionhasDevicePK getTestExecutionhasDevicePK() {
        return testExecutionhasDevicePK;
    }

    public void setTestExecutionhasDevicePK(TestExecutionhasDevicePK testExecutionhasDevicePK) {
        this.testExecutionhasDevicePK = testExecutionhasDevicePK;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
        hash += (testExecutionhasDevicePK != null ? testExecutionhasDevicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestExecutionhasDevice)) {
            return false;
        }
        TestExecutionhasDevice other = (TestExecutionhasDevice) object;
        if ((this.testExecutionhasDevicePK == null && other.testExecutionhasDevicePK != null) || (this.testExecutionhasDevicePK != null && !this.testExecutionhasDevicePK.equals(other.testExecutionhasDevicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecutionhasDevice[ testExecutionhasDevicePK=" + testExecutionhasDevicePK + " ]";
    }
    
}
