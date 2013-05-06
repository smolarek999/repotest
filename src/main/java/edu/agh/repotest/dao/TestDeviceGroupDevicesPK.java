/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author pawel
 */
@Embeddable
public class TestDeviceGroupDevicesPK implements Serializable {

    @ManyToOne
    private Device device;
    @ManyToOne
    private TestDeviceGroup deviceGroup;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public TestDeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(TestDeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getDevice().hashCode();
        hash += (int) getDeviceGroup().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupDevicesPK)) {
            return false;
        }
        TestDeviceGroupDevicesPK other = (TestDeviceGroupDevicesPK) object;
        if (this.getDevice() != other.getDevice()) {
            return false;
        }
        if (this.getDeviceGroup() != other.getDeviceGroup()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevicePK[ testDeviceGroupidTestDeviceGroup=" + getDeviceGroup() + ", testDeviceGroupTestidTest=" + getDevice() + " ]";
    }
}
