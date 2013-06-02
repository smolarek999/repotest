/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

/**
 *
 * @author pawel
 */
public enum TestStatus {

    WAITING(1,""),
    IN_PROGRESS(2,""),
    BLOCKED(3,"label-inverse"),
    PASSED(4,"label-success"),
    FAILED(5,"label-important"),
    WAIVED(6,"label-inverse"),
    NEED_HELP(7,"label-info");
    private int rawValue;
    private String cssClass;

    TestStatus(int rawValue,String cssClass) {
        this.rawValue = rawValue;
        this.cssClass = cssClass;
    }

    public int getRawValue() {
        return rawValue;
    }

    public String getCssClass() {
        return cssClass;
    }
    
}
