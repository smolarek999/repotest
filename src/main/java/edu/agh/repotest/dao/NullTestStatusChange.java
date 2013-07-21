/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.sql.Timestamp;

/**
 *
 * @author pawel
 */
public class NullTestStatusChange extends TestStatusChange {

    public final static TestStatusChange INSTANCE = new NullTestStatusChange();
    public final static Timestamp NULL_TIMESTAMP = new Timestamp(0);

    @Override
    public Timestamp getDateTime() {
        return NULL_TIMESTAMP;
    }
}
