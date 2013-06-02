/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.util.ConditionHelper;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pawel
 */
public class Condition implements Serializable {

    private GroupOfDevices deviceGroup;
    private List<Device> devices = new LinkedList<Device>();

    public Condition() {
        this(new GroupOfDevices(), new LinkedList<Device>());
    }

    public Condition(GroupOfDevices deviceGroup, List<Device> devices) {
        this.deviceGroup = deviceGroup;
        this.devices = new LinkedList<Device>(devices);
    }

    public GroupOfDevices getDeviceGroup() {
        System.out.println("Condition::" + "getDeviceGroup");
        return deviceGroup;
    }

    public void setDeviceGroup(GroupOfDevices deviceGroup) {
        System.out.println("Condition::" + "getDeviceGroup");
        this.deviceGroup = deviceGroup;
    }

    public List<Device> getDevices() {
        System.out.println("Condition::" + "getDevices" + devices.size());
        for (Device device : devices) {
            System.out.println("\t" + device.getName() + device.getIdDevice());
        }
        return devices;
    }

    public void setDevices(List<Device> devices) {
        System.out.println("Condition::" + "setDevices");
        this.devices = devices;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Condition)) {
            return false;
        }
        Condition other = (Condition) obj;

        return deviceGroup.equals(other.deviceGroup) && devices.equals(other.devices);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.deviceGroup != null ? this.deviceGroup.hashCode() : 0);
        hash = 83 * hash + (this.devices != null ? this.devices.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ConditionHelper.createToString(this);
    }
}
