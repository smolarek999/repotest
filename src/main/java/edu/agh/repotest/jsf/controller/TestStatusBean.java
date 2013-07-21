/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.TestStatus;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * This bean is used for using TestStatus information in a Facelets code
 */
@ApplicationScoped
@ManagedBean(name = "testStatus")
public class TestStatusBean {

    public TestStatus getPassed() {
        return TestStatus.PASSED;
    }

    public TestStatus getFailed() {
        return TestStatus.FAILED;
    }

    public TestStatus getWaived() {
        return TestStatus.WAIVED;
    }

    public TestStatus getWaiting() {
        return TestStatus.WAITING;
    }

    public TestStatus getBlocked() {
        return TestStatus.BLOCKED;
    }

    public TestStatus getNeedHelp() {
        return TestStatus.NEED_HELP;
    }

    public TestStatus getInProgress() {
        return TestStatus.IN_PROGRESS;
    }
}
