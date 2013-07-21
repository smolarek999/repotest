/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author pawel
 */
public class DummyTestPermutation implements TestPermutationStrategy {

    @Override
    public List<DevicePermutation> combine(Collection<GroupOfDevices> groupOfDevices) {
        List<DevicePermutation> permuttedList = new ArrayList<DevicePermutation>();
        for (GroupOfDevices groupOfDevice : groupOfDevices) {
            List<DevicePermutation> newPermuttedList = new ArrayList<DevicePermutation>();
            newPermuttedList.addAll(combineOneGroupWithRest(groupOfDevice, permuttedList));
            permuttedList = newPermuttedList;
        }
        return permuttedList;
    }

    public List<DevicePermutation> combineOneGroupWithRest(GroupOfDevices groupOfDevices, List<DevicePermutation> deviceSelections) {
        List<DevicePermutation> permuttedList = new ArrayList<DevicePermutation>();
        for (DeviceInGroupOfDevices deviceInGroupOfDevices : groupOfDevices.getDevices()) {
            permuttedList.addAll(appendDeviceForAllSelection(deviceInGroupOfDevices, deviceSelections));
        }
        return permuttedList;
    }

    public List<DevicePermutation> appendDeviceForAllSelection(DeviceInGroupOfDevices deviceInGroupOfDevices, List<DevicePermutation> deviceSelections) {

        List<DevicePermutation> permutedSelections = new ArrayList<DevicePermutation>();

        for (DevicePermutation deviceSelection : deviceSelections) {
            DevicePermutation permutedSelection = new DevicePermutation(deviceSelection.getDevices());
            permutedSelection.appendDevice(deviceInGroupOfDevices.getDevice());
            permutedSelections.add(permutedSelection);
        }
        if (permutedSelections.isEmpty()) {

            DevicePermutation permutedSelection = new DevicePermutation(new ArrayList<Device>());
            permutedSelection.appendDevice(deviceInGroupOfDevices.getDevice());
            permutedSelections.add(permutedSelection);
        }
        return permutedSelections;

    }
}
