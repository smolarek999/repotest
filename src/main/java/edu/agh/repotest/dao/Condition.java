/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pawel
 */
public class Condition {
    private static final String CONDITION_SEPARATOR = "|";
    private static final String HEAD_SEPARATOR = ":";
    private static final String ITEM_SEPARATOR = ".";
    
    private static final String CONDITION_SEPARATOR_PATTERN = "\\" + CONDITION_SEPARATOR;
    private static final String HEAD_SEPARATOR_PATTERN = HEAD_SEPARATOR;
    private static final String ITEM_SEPARATOR_PATTERN = "\\" + ITEM_SEPARATOR;
    public static List<Condition> createFromString( String rawCondtionions ){
        
        List<Condition> conditions = new LinkedList<Condition>();
        for( String rawCondtionin : rawCondtionions.split(CONDITION_SEPARATOR_PATTERN) ){
            String [] headAndTail = rawCondtionin.split(HEAD_SEPARATOR_PATTERN);
            Integer deviceGroupId = Integer.parseInt(headAndTail[0]);
            List<Integer> devicesIds = new LinkedList<Integer>();
            for( String deviceId : headAndTail[1].split(ITEM_SEPARATOR_PATTERN)){
                devicesIds.add( Integer.valueOf(deviceId));
            }
            conditions.add(new Condition(deviceGroupId, devicesIds));
            
        }
        return conditions;
    }
    public static String createToString( List<Condition> condtionions ){
        
        StringBuilder builder = new StringBuilder();
        for( Condition cond : condtionions){
            builder.append(cond.getDeviceGroupId());
            builder.append(HEAD_SEPARATOR);
            for( Integer id : cond.getDevices()){
                builder.append(id);
                builder.append(ITEM_SEPARATOR);
            }
            builder.deleteCharAt(builder.length() -1);
            builder.append(CONDITION_SEPARATOR);
        }
         builder.deleteCharAt(builder.length() -1);
         return builder.toString();
    }
    private Integer deviceGroupId;
    private List<Integer> devices = new LinkedList<Integer>();
    
    public Condition(){
        
    }
    
    public Condition( Integer deviceGroupId, List<Integer> devices ){
        this.deviceGroupId = deviceGroupId;
        this.devices = new LinkedList<Integer>(devices);
    }

    /**
     * @return the deviceGroupId
     */
    public Integer getDeviceGroupId() {
        return deviceGroupId;
    }

    /**
     * @param deviceGroupId the deviceGroupId to set
     */
    public void setDeviceGroupId(Integer deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    /**
     * @return the allowedDevices
     */
    public List<Integer> getDevices() {
        return devices;
    }

    /**
     * @param allowedDevices the allowedDevices to set
     */
    public void setDevices(List<Integer> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Condition)) {
            return false;
        }
        Condition other = (Condition) obj;
        
        return deviceGroupId.equals(other.getDeviceGroupId()) && devices.equals(other.getDevices());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.deviceGroupId != null ? this.deviceGroupId.hashCode() : 0);
        hash = 83 * hash + (this.devices != null ? this.devices.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return createToString( Arrays.asList(new Condition[]{this}));
    }
    
    
    
    
    
    
   
}
