/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pawel
 */
@Embeddable
public class TestDeviceGroupDevicesPK  {

    Integer deviceId;
    Integer deviceGroupId;

    public TestDeviceGroupDevicesPK() {
    }
    
    
    public TestDeviceGroupDevicesPK(Integer i1, Integer i2) {
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceGroupId) {
        this.deviceId = deviceGroupId;
    }

    public Integer getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(Integer deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getDeviceId().hashCode();
        hash += (int) getDeviceGroupId().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupDevicesPK)) {
            return false;
        }
        TestDeviceGroupDevicesPK other = (TestDeviceGroupDevicesPK) object;
        if (this.getDeviceId() != other.getDeviceId()) {
            return false;
        }
        if (this.getDeviceGroupId() != other.getDeviceGroupId()) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevicePK[ testDeviceGroupidTestDeviceGroup=" + getDeviceGroupId() + ", testDeviceGroupTestidTest=" + getDeviceId() + " ]";
    }
}
