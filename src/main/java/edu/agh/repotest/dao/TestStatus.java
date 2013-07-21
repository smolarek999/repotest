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
    


    WAITING(1, "","",1),
    IN_PROGRESS(2, "","",1),
    BLOCKED(3, "label-inverse","btn-inverse",1) {
        @Override
        public boolean isValidTestExecution(TestExecution testExec) {
            return super.isValidTestExecution(testExec);
        }
    },
    PASSED(4, "label-success", "btn-success",-1),
    FAILED(5, "label-important", "btn-danger",-1),
    WAIVED(6, "label-inverse", "btn-inverse",-1),
    NEED_HELP(7, "label-info","btn-info",1);
    private int rawValue;
    private String cssClassLabel;
    private String cssClassButton;
    private int changeFactor;

    TestStatus(int rawValue, String cssClassLabel, String cssClassButton, int changeFactor) {
        this.rawValue = rawValue;
        this.cssClassLabel = cssClassLabel;
        this.cssClassButton = cssClassButton;
        this.changeFactor = changeFactor;
    }
    public int getRawValue() {
        return rawValue;
    }

    public String getCssClassLabel() {
        return cssClassLabel;
    }
     public String getCssClassButton() {
        return cssClassButton;
    }

    public boolean isValidTestExecution(TestExecution testExec) {
        return true;
    } 

    public int getChangeFactor() {
        return changeFactor;
    }
    
    
}
