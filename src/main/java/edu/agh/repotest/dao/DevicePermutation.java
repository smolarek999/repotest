/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author pawel
 */
public class DevicePermutation {

    private static final TestPermutationStrategy PERMUTATION_STRATEGY = new DummyTestPermutation();

    public static List<DevicePermutation> getPermutationForTest(Test test) {
        return PERMUTATION_STRATEGY.combine(test.getDevicesGroups());
    }
    private List<Device> devices;

    public DevicePermutation(List<Device> devices) {
        this.devices = new ArrayList<Device>(devices);
    }

    public void appendDevice(Device device) {
        devices.add(device);
    }

    
    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Device dev : devices) {
            sb.append(dev.getName());
            sb.append(",");
        }
        return sb.toString();
    }

    boolean has(Device device) {
        return devices.contains(device);
    }
}
