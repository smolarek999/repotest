/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.util.ConditionHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pawel
 */
public class TestExecutionVariation {

    public static TestExecutionVariation createForDevicePermutation(DevicePermutation devices, List<EnityWithCondition> enityWithConditions) {
        List<EnityWithCondition> passedEntities = new ArrayList<EnityWithCondition>();
        for (EnityWithCondition enity : enityWithConditions) {
            if (ConditionHelper.hasBeenPassed(enity, devices)) {
                passedEntities.add(enity);
            }
        }
        return new TestExecutionVariation(passedEntities);
    }
    private List<EnityWithCondition> entities;

    private TestExecutionVariation(List<EnityWithCondition> entities) {
        this.entities = entities;
    }

    public String getHash() {
        StringBuilder sb = new StringBuilder();
        for (EnityWithCondition entity : entities) {
            sb.append(entity.getConditionalId());
        }
        return sb.toString();

    }

    public List<String> getDescriptions(){
        List<String> descriptions = new ArrayList<String>();
        for(EnityWithCondition entity : entities){
            descriptions.add(entity.getConditionalDesc());
        }
        return descriptions;
    }
    @Override
    public int hashCode() {
        return getHash().hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TestExecutionVariation) {
            TestExecutionVariation casted = (TestExecutionVariation) obj;
            return getHash().equals(casted.getHash());
        }
        return false;
    }
}
