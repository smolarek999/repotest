/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pawel
 */
public class TestExecutionVariationMap {

    private Map<TestExecutionVariation, List<DevicePermutation>> backingMap = new HashMap<TestExecutionVariation, List<DevicePermutation>>();

    public void put(TestExecutionVariation executionVariation, DevicePermutation deviceSelection) {

        if (!backingMap.containsKey(executionVariation)) {
            backingMap.put(executionVariation, new ArrayList<DevicePermutation>());
        }
        backingMap.get(executionVariation).add(deviceSelection);
    }

    public boolean containsKey(Object o) {
        return backingMap.containsKey(o);
    }
    public Set<TestExecutionVariation> getVariations(){
        return backingMap.keySet();
    }
    
    public List<String> getVariationHashes(){
        List<String> keyNames = new  ArrayList<String>();
        for( TestExecutionVariation variation : backingMap.keySet()){
            keyNames.add(variation.getHash());
        }
        return keyNames;
    }
    
    public List<DevicePermutation> getDevicesPermuation(TestExecutionVariation var) {
        
        return backingMap.get(var);
    }

    public List<TestExecutionVariation> getVariationForDevice(Device device) {
        List<TestExecutionVariation> variations = new ArrayList<TestExecutionVariation>();
        for (TestExecutionVariation variation : backingMap.keySet()) {
            if (hasVariationDevice(variation, device)) {
                variations.add(variation);
            }
        }
        return variations;
    }

    private boolean hasVariationDevice(TestExecutionVariation executionVariation, Device d) {
        for (DevicePermutation deviceSelection : backingMap.get(executionVariation)) {
            if (deviceSelection.equals(d)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return backingMap.size();
    }

    public boolean isEmpty() {
        return backingMap.isEmpty();
    }
}
