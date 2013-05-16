/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author pawel
 */
public class ConditionTest {

    public ConditionTest() {
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
     * Test of createFromString method, of class Condition.
     */
    @org.junit.Test
    public void testCreateFromString() {

        System.out.println("createFromString");
        String rawCondtionions = "1:2.4.6|10:1.2.4|4:5";
        List<Device> ids1 = Arrays.asList(new Device[]{new Device(2), new Device(4), new Device(6)});
        List<Device> ids2 = Arrays.asList(new Device[]{new Device(1), new Device(2), new Device(4)});
        List<Device> ids3 = Arrays.asList(new Device[]{new Device(5)});

        List<Condition> expResult = Arrays.asList(new Condition[]{
            new Condition(new TestDeviceGroup(1), ids1), new Condition(new TestDeviceGroup(10), ids2), new Condition(new TestDeviceGroup(4), ids3)
        });
        List result = Condition.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }
    
    
    @org.junit.Test
    public void testCreateFromEmptyString() {

        System.out.println("createFromString");
        String rawCondtionions = "";
        List<Condition> expResult = new LinkedList<Condition>();
        List result = Condition.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }
        
        
    @org.junit.Test
    public void testCreateFromNull() {

        System.out.println("createFromString");
        String rawCondtionions = null;
          List<Condition> expResult = new LinkedList<Condition>();
        List result = Condition.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }

    /**
     * Test of createToString method, of class Condition.
     */
    @org.junit.Test
    public void testCreateToString() {
        System.out.println("createToString");
        String rawCondtionions = "1:2.4.6|10:1.2.4|4:5";
        List<Device> ids1 = Arrays.asList(new Device[]{new Device(2), new Device(4), new Device(6)});
        List<Device> ids2 = Arrays.asList(new Device[]{new Device(1), new Device(2), new Device(4)});
        List<Device> ids3 = Arrays.asList(new Device[]{new Device(5)});
        List<Condition> condtionions = Arrays.asList(new Condition[]{
            new Condition(new TestDeviceGroup(1), ids1), new Condition(new TestDeviceGroup(10), ids2), new Condition(new TestDeviceGroup(4), ids3)
        });
        String expResult = "1:2.4.6|10:1.2.4|4:5";
        String result = Condition.createToString(condtionions);
        assertEquals(expResult, result);
    }

    
}