/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest.model;

import java.util.HashMap;
import java.util.Map;
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
public class ResponseTest {
    
    public ResponseTest() {
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
     * Test of cookUrl method, of class Response.
     */
    @Test
    public void testCookUrl() {
        System.out.println("cookUrl");
        String rawUrl = "this is ${arg1} and ${arg2}/ tralala";
        Map<String, String> ingredients = new HashMap<String, String>();
        ingredients.put("arg1", "pierwszy");
        ingredients.put("arg2", "drugi");
        String expResult = "this is pierwszy and drugi/ tralala";
        String result = Response.cookUrl(rawUrl, ingredients);
        assertEquals(expResult, result);
    }
}