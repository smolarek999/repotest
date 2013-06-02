/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.util;

import static edu.agh.repotest.util.AssertHelper.atLeastOneIsNull;
import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.EnityWithCondition;
import edu.agh.repotest.dao.GroupOfDevices;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pawel
 */
public class ConditionHelper {

    static final String ITEM_SEPARATOR = ".";
    static final String HEAD_SEPARATOR = ":";
    static final String HEAD_SEPARATOR_PATTERN = HEAD_SEPARATOR;
    static final String CONDITION_SEPARATOR = "|";
    static final String CONDITION_SEPARATOR_PATTERN = "\\" + CONDITION_SEPARATOR;
    static final String ITEM_SEPARATOR_PATTERN = "\\" + ITEM_SEPARATOR;

    public static boolean hasBeenPassed(EnityWithCondition entityWithCondition, List<Device> availableDevices) {
        boolean passed = true;
        Condition condition = entityWithCondition.getRawCondition();
        if (!isEmptyCondition(condition)) {
            passed = false;
            for (Device oneOfRequiredDevice : condition.getDevices()) {
                if (availableDevices.contains(oneOfRequiredDevice)) {
                    passed = true;
                    break;
                }
            }

        }
        return passed;
    }

    public static Condition createFromString(String rawCondition) {
        if (rawCondition == null || rawCondition.isEmpty()) {
            return new Condition();
        }
        String[] groupAndDevices = rawCondition.split(HEAD_SEPARATOR_PATTERN);
        if (groupAndDevices.length != 2) {
            throw new IllegalArgumentException("wrong pattern " + rawCondition);
        }
        Integer deviceGroupId = Integer.parseInt(groupAndDevices[0]);
        GroupOfDevices deviceGroup = new GroupOfDevices(deviceGroupId);
        List<Device> devices = new LinkedList<Device>();
        for (String rawDeviceId : groupAndDevices[1].split(ITEM_SEPARATOR_PATTERN)) {
            Integer deviceId = Integer.valueOf(rawDeviceId);
            devices.add(new Device(deviceId));
        }
        return new Condition(deviceGroup, devices);
    }

    public static String createToString(Condition condition) {
        if (isEmptyCondition(condition)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(condition.getDeviceGroup().getId());
        builder.append(HEAD_SEPARATOR);
        for (Device device : condition.getDevices()) {
            builder.append(device.getIdDevice());
            builder.append(ITEM_SEPARATOR);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static boolean isEmptyCondition(Condition condition) {
        return atLeastOneIsNull(condition.getDeviceGroup(), condition.getDevices()) || condition.getDevices().isEmpty();
    }
}
