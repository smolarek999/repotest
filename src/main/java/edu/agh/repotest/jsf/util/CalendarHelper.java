/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.util;

import edu.agh.repotest.dao.NullTestStatusChange;
import edu.agh.repotest.dao.TestStatusChange;
import edu.agh.repotest.dao.TestStatusChangeSum;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 *
 * @author pawel
 */
public class CalendarHelper {

    public static List<DateMidnight> getDaysBetween(LocalDate start, LocalDate end) {

       
        return getDaysBetween(start.toDateMidnight(),end.toDateMidnight());
    }
    public static List<DateMidnight> getDaysBetween(DateMidnight start, DateMidnight end) {

        List<DateMidnight> daysBeetwenPeriod = new ArrayList<DateMidnight>();
        DateMidnight currentDay = start;
        daysBeetwenPeriod.add(currentDay);
        Days between = Days.daysBetween(start, end);
        for (int i = 0; i < between.getDays(); ++i) {
            currentDay = currentDay.plusDays(1);
            daysBeetwenPeriod.add(currentDay);
        }
        return daysBeetwenPeriod;
    }

    public static List<TestChangeModel> getTestBurn(List<DateMidnight> days, List<TestStatusChangeSum> changeTicks, Long startCount) {


        final Iterator<TestStatusChangeSum> iterator = changeTicks.iterator();

        TestStatusChangeSum currentTestStatus = null;
        if (iterator.hasNext()) {
            currentTestStatus = iterator.next();
        } else {
            currentTestStatus = TestStatusChangeSum.NULL;
        }





        List<TestChangeModel> testCount = new ArrayList<TestChangeModel>();

        for (DateMidnight day : days) {
            if (day.isEqual(currentTestStatus.getDate())) {
                startCount += currentTestStatus.getFactor();
                if (iterator.hasNext()) {
                    currentTestStatus = iterator.next();
                }

            }
            testCount.add(new TestChangeModel(day.getMillis(), startCount));

        }
        return testCount;
    }

    public static class TestChangeModel {

        Long timestamp;
        Long testCount;

        public TestChangeModel(Long timestamp, Long testCount) {
            this.timestamp = timestamp;
            this.testCount = testCount;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public Long getTestCount() {
            return testCount;
        }
    }
}
