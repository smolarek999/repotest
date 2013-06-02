/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.dao.TestStatus;

/**
 *
 * @author pawel
 */
public class TestAction {

    TestStatus status;
    String testName;
    String userName;

    public TestAction(TestStatus status, String testName, String userName) {
        this.status = status;
        this.testName = testName;
        this.userName = userName;
    }

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
