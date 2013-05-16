/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.util;

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
public class IdHelperTest {
    
    public IdHelperTest() {
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
     * Test of getIdPart method, of class IdHelper.
     */
    @Test
    public void testGetIdPart() {
        System.out.println("getIdPart");
        String id = "raz:dwa:trzy";
        int part = -1;
        String expResult = "trzy";
        String result = IdHelper.getIdPart(id, part);
        assertEquals(expResult, result);
        part = -2;
        result = IdHelper.getIdPart(id, part);
        assertEquals("dwa", result);
        
        part = 1;
        result = IdHelper.getIdPart(id, part);
        assertEquals("raz", result);
        
        part = 2;
        result = IdHelper.getIdPart(id, part);
        assertEquals("dwa", result);
        
        part = -3;
        result = IdHelper.getIdPart(id, part);
        assertEquals("raz", result);
        
        
        
        // TODO review the generated test code and remove the default call to fail.
    }
}