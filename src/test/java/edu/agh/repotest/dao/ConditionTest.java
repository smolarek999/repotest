/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.util.ConditionHelper;
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
        String rawCondtionions = "1:2.4.6";
        List<Device> ids1 = Arrays.asList(new Device[]{new Device(2), new Device(4), new Device(6)});

        Condition expResult = new Condition(new GroupOfDevices(1), ids1);
        Condition result = ConditionHelper.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }

    @org.junit.Test
    public void testCreateFromEmptyString() {

        System.out.println("createFromString");
        String rawCondtionions = "";
        Condition expResult = new Condition();
        Condition result = ConditionHelper.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }

    @org.junit.Test
    public void testCreateFromNull() {

        System.out.println("createFromString");
        String rawCondtionions = null;
        Condition expResult = new Condition();
        Condition result = ConditionHelper.createFromString(rawCondtionions);
        assertEquals(expResult, result);

    }

    /**
     * Test of createToString method, of class Condition.
     */
    @org.junit.Test
    public void testCreateToString() {
        System.out.println("createToString");
        List<Device> ids1 = Arrays.asList(new Device[]{new Device(2), new Device(4), new Device(6)});

        Condition condition = new Condition(new GroupOfDevices(1), ids1);
        String expResult = "1:2.4.6";
        String result = ConditionHelper.createToString(condition);
        assertEquals(expResult, result);
    }
}