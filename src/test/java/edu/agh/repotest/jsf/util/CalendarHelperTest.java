/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.util;

import edu.agh.repotest.dao.NullTestStatusChange;
import edu.agh.repotest.dao.TestStatusChange;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateMidnight;
import org.joda.time.LocalDate;
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
public class CalendarHelperTest {

    public CalendarHelperTest() {
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



    private boolean areSecondDateBefore(DateMidnight date1, Timestamp date2) {
        if (date2.equals(NullTestStatusChange.NULL_TIMESTAMP)) {
            return false;
        }
        return date1.isAfter(new DateMidnight(date2));
    }

    private boolean areDatesEquals(DateMidnight date1, Timestamp date2) {
        return date1.isEqual(new DateMidnight(date2));

    }
}