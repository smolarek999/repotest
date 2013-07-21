/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author pawel
 */
public interface TestPermutationStrategy {
    public List<DevicePermutation> combine(Collection<GroupOfDevices> groupOfDevices);
}
