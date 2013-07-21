/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pawel
 */
public class DummyTestPermutationTest {

    public DummyTestPermutationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of combine method, of class DummyTestPermutation.
     */
    @Test
    public void testCombine() {
        System.out.println("combine");

        Device d1 = new Device(1);
        DeviceInGroupOfDevices dgd1 = new DeviceInGroupOfDevices();
        dgd1.setDevice(d1);
        
        
        Device d2 = new Device(2);
        DeviceInGroupOfDevices dgd2 = new DeviceInGroupOfDevices();
        dgd2.setDevice(d2);
        
        Device d3 = new Device(3);
        DeviceInGroupOfDevices dgd3 = new DeviceInGroupOfDevices();
        dgd3.setDevice(d3);
        
        Device d4 = new Device(4);
        DeviceInGroupOfDevices dgd4 = new DeviceInGroupOfDevices();
        dgd4.setDevice(d4);
        
        Device d5 = new Device(5);
        DeviceInGroupOfDevices dgd5 = new DeviceInGroupOfDevices();
        dgd5.setDevice(d5);
        
        Device d6 = new Device(6);
        DeviceInGroupOfDevices dgd6 = new DeviceInGroupOfDevices();
        dgd6.setDevice(d6);

        List<GroupOfDevices> groupOfDevices = new ArrayList<GroupOfDevices>();
        GroupOfDevices g1 = new GroupOfDevices();
        g1.getDevices().add(dgd1);
        g1.getDevices().add(dgd2);
        
        
        GroupOfDevices g2 = new GroupOfDevices();
        g2.getDevices().add(dgd3);
        g2.getDevices().add(dgd4);
        
        GroupOfDevices g3 = new GroupOfDevices();
        g3.getDevices().add(dgd5);
        g3.getDevices().add(dgd6);


        groupOfDevices.add(g1);
        groupOfDevices.add(g2);
        groupOfDevices.add(g3);
        
        DummyTestPermutation instance = new DummyTestPermutation();
        List expResult = null;
        List result = instance.combine(groupOfDevices);
        
    }
}