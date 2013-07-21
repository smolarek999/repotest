/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.session.TestExecutionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TestSelectionController {
    
    List<TestExecution> availableTests;
    
    TestExecution selectedTest;
    
    @ManagedProperty("#{loggedUserController}")
    LoggedUserController userController;
    
    @EJB
    TestExecutionFacade testExecutionFacade;

    public LoggedUserController getUserController() {
        return userController;
    }

    public void setUserController(LoggedUserController userController) {
        this.userController = userController;
    }
    
    
    public List<TestExecution> getAvailableTests(){
        if(  availableTests == null ){
            availableTests = testExecutionFacade.getAvailableForUser(userController.getLoggedUser());
        }
        return availableTests;
    }
    
    public void signMeUpForTest(){
        availableTests.remove(selectedTest);
        selectedTest.setStatus(TestStatus.IN_PROGRESS);
        selectedTest.setTester(userController.getLoggedUser());
        testExecutionFacade.edit(selectedTest,TestStatus.WAITING);
    }
    
    public void setAvailableTests( List<TestExecution> availableTests ){
        this.availableTests = availableTests;
    }

    public TestExecution getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(TestExecution selectedTest) {
        this.selectedTest = selectedTest;
    }
    
    
}
