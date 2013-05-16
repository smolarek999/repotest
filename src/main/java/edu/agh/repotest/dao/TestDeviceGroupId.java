/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;

/**
 *
 * @author pawel
 */
public class TestDeviceGroupId implements Serializable {
    private long Device_idDevice;
    private long TestDeviceGroup_idTestDeviceGroup;

    public long getDevice_idDevice() {
        return Device_idDevice;
    }

    public void setDevice_idDevice(long Device_idDevice) {
        this.Device_idDevice = Device_idDevice;
    }

    public long getTestDeviceGroup_idTestDeviceGroup() {
        return TestDeviceGroup_idTestDeviceGroup;
    }

    public void setTestDeviceGroup_idTestDeviceGroup(long TestDeviceGroup_idTestDeviceGroup) {
        this.TestDeviceGroup_idTestDeviceGroup = TestDeviceGroup_idTestDeviceGroup;
    }

    public int hashCode() {
        return (int) (Device_idDevice + TestDeviceGroup_idTestDeviceGroup);
    }

    public boolean equals(Object object) {
        if (object instanceof TestDeviceGroupId) {
            TestDeviceGroupId otherId = (TestDeviceGroupId) object;
            return (otherId.Device_idDevice == this.Device_idDevice) && (otherId.TestDeviceGroup_idTestDeviceGroup == this.TestDeviceGroup_idTestDeviceGroup);
        }
        return false;
    }
    
}
