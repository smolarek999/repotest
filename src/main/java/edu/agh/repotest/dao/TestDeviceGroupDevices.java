/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
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
@Table(name = "TestDeviceGroup_has_Device")
@XmlRootElement
@IdClass(TestDeviceGroupId.class)
@NamedQueries({
    @NamedQuery(name = "TestDeviceGroupDevices.findAll", query = "SELECT t FROM TestDeviceGroupDevices t")})
@AssociationOverrides({
    @AssociationOverride(name = "pk.deviceGroup",
            joinColumns =
            @JoinColumn(name = "Device_idDevice")),
    @AssociationOverride(name = "pk.device",
            joinColumns =
            @JoinColumn(name = "TestDeviceGroup_idTestDeviceGroup"))})
public class TestDeviceGroupDevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "ExecutionCount")
    private String executionCount;
    @EmbeddedId
    private TestDeviceGroupDevicesPK pk = new TestDeviceGroupDevicesPK();

    public TestDeviceGroupDevices() {
    }

    public TestDeviceGroupDevicesPK getPk() {
        return pk;
    }

    public void setPk(TestDeviceGroupDevicesPK pk) {
        this.pk = pk;
    }

    public String getName() {
        return pk.getDeviceGroup().getName();
    }

    public Device getDevice() {
        return pk.getDevice();
    }

    public void setDevice(Device device) {
        pk.setDevice(device);
    }

    public TestDeviceGroup getDeviceGroup() {
        return pk.getDeviceGroup();
    }

    public void setDeviceGroup(TestDeviceGroup deviceGroup) {
        pk.setDeviceGroup(deviceGroup);
    }

    public String getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(String executionCount) {
        this.executionCount = executionCount;
    }

    @Override
    public int hashCode() {
        return pk.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupDevices)) {
            return false;
        }
        TestDeviceGroupDevices other = (TestDeviceGroupDevices) object;
        return pk.equals(other.getPk());
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevice[ testDeviceGrouphasDevicePK=" + getDevice() + " ]";
    }
}
