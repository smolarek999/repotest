/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

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
public class TestStatusHelperTest {
    
   
    @Test
    public void testGetChangeFactorForTransitionNoChange() {
        System.out.println("getChangeFactorForTransition");
        TestStatus from = TestStatus.WAITING;
        TestStatus to = TestStatus.IN_PROGRESS;
        int expResult = 0;
        int result = TestStatusHelper.getChangeFactorForTransition(from, to);
        assertEquals(expResult, result);
        
        from = TestStatus.FAILED;
        to = TestStatus.PASSED;
        result = TestStatusHelper.getChangeFactorForTransition(from, to);
        assertEquals(expResult, result);
    }
     @Test
    public void testGetChangeFactorForTransitionGain() {
        System.out.println("testGetChangeFactorForTransitionGain");
        TestStatus from = TestStatus.PASSED;
        TestStatus to = TestStatus.IN_PROGRESS;
        int expResult = 1;
        int result = TestStatusHelper.getChangeFactorForTransition(from, to);
        assertEquals(expResult, result);
    }
        @Test
    public void testGetChangeFactorForTransitionLost() {
        System.out.println("testGetChangeFactorForTransitionLost");
        TestStatus from = TestStatus.IN_PROGRESS;
        TestStatus to = TestStatus.PASSED;
        int expResult = -1;
        int result = TestStatusHelper.getChangeFactorForTransition(from, to);
        assertEquals(expResult, result);
    }
}