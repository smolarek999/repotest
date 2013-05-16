/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pawel
 */
public class Condition implements Serializable {

    private static final String CONDITION_SEPARATOR = "|";
    private static final String HEAD_SEPARATOR = ":";
    private static final String ITEM_SEPARATOR = ".";
    private static final String CONDITION_SEPARATOR_PATTERN = "\\" + CONDITION_SEPARATOR;
    private static final String HEAD_SEPARATOR_PATTERN = HEAD_SEPARATOR;
    private static final String ITEM_SEPARATOR_PATTERN = "\\" + ITEM_SEPARATOR;

    public static List<Condition> createFromString(String rawCondtionions) {

        List<Condition> conditions = new LinkedList<Condition>();
        if (rawCondtionions == null || rawCondtionions.isEmpty()) {
            return conditions;
        }


        for (String rawCondtionin : rawCondtionions.split(CONDITION_SEPARATOR_PATTERN)) {
            String[] headAndTail = rawCondtionin.split(HEAD_SEPARATOR_PATTERN);
            if (headAndTail.length != 2) {
                throw new IllegalArgumentException("wrong pattern " + rawCondtionin);
            }
            Integer deviceGroupId = Integer.parseInt(headAndTail[0]);
            TestDeviceGroup deviceGroup = new TestDeviceGroup(deviceGroupId);
            List<Device> devices = new LinkedList<Device>();
            for (String rawDeviceId : headAndTail[1].split(ITEM_SEPARATOR_PATTERN)) {
                Integer deviceId = Integer.valueOf(rawDeviceId);

                devices.add(new Device(deviceId));
            }
            conditions.add(new Condition(deviceGroup, devices));

        }
        return conditions;
    }

    public static String createToString(Collection<Condition> condtionions) {

        StringBuilder builder = new StringBuilder();
        for (Condition cond : condtionions) {
            builder.append(cond.getDeviceGroup().getId());
            builder.append(HEAD_SEPARATOR);
            for (Device device : cond.getDevices()) {
                builder.append(device.getIdDevice());
                builder.append(ITEM_SEPARATOR);
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append(CONDITION_SEPARATOR);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
    private TestDeviceGroup deviceGroup;
    private List<Device> devices = new LinkedList<Device>();

    public Condition() {
    }

    public Condition(TestDeviceGroup deviceGroup, List<Device> devices) {
        this.deviceGroup = deviceGroup;
        this.devices = new LinkedList<Device>(devices);
    }

    public TestDeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(TestDeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
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
        return createToString(Arrays.asList(new Condition[]{this}));
    }
}
