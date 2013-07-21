/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import org.joda.time.DateMidnight;

/**
 *
 * @author pawel
 */
public class TestStatusChangeSum {
    public static TestStatusChangeSum NULL = new TestStatusChangeSum(new DateMidnight(1970,12,1), 0L);
    
    private DateMidnight date;
    private Long factor;

    public TestStatusChangeSum(DateMidnight date, Long factor) {
        this.date = date;
        this.factor = factor;
    }

    public DateMidnight getDate() {
        return date;
    }

    public void setDate(DateMidnight date) {
        this.date = date;
    }

    public Long getFactor() {
        return factor;
    }

    public void setFactor(Long factor) {
        this.factor = factor;
    }
    
    
    
    
}
