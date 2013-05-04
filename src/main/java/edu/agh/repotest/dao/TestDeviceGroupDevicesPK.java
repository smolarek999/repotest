/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pawel
 */
@Embeddable
public class TestDeviceGroupDevicesPK implements Serializable {
    
   
    @Column(name = "Device_idDevice")
    private int deviceId;
    
    @Column(name = "TestDeviceGroup_idTestDeviceGroup")
    private int deviceGroupsId;
    
    


    /**
     * @return the device
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * @param device the device to set
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the deviceGroups
     */
    public int getDeviceGroupsId() {
        return deviceGroupsId;
    }

    /**
     * @param deviceGroups the deviceGroups to set
     */
    public void setDeviceGroupsId(int deviceGroupsId) {
        this.deviceGroupsId = deviceGroupsId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getDeviceId();
        hash += (int) getDeviceGroupsId();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupDevicesPK)) {
            return false;
        }
        TestDeviceGroupDevicesPK other = (TestDeviceGroupDevicesPK) object;
        if (this.getDeviceGroupsId() != other.getDeviceGroupsId()) {
            return false;
        }
        if (this.deviceId != other.deviceId) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevicePK[ testDeviceGroupidTestDeviceGroup=" + deviceId + ", testDeviceGroupTestidTest=" + deviceGroupsId + " ]";
    }

    
}
