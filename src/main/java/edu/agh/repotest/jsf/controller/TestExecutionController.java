/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.dao.TestStatusChange;
import edu.agh.repotest.dao.UserRole;
import edu.agh.repotest.dao.Users;
import edu.agh.repotest.jsf.util.JsfUtil;
import edu.agh.repotest.session.TestExecutionFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pawel
 */
@ManagedBean
@ViewScoped
public class TestExecutionController extends AbstractController<TestExecution> implements Serializable {

    @ManagedProperty("#{loggedUserController}")
    LoggedUserController userController;
    @EJB
    TestExecutionFacade testExecutionFacade;
    private int idTestExecution;
    private TestStatus previousStatus;
    
    private List<TestStatusChange> changesOnTest;
    /**
     * Lazy loaded
     */
    private List<TestExecution> takenTests;
    
    @PostConstruct
    public void init() {
        super.setFacade(testExecutionFacade);
        System.out.println("TestExecutionController.init");

    }
    
    public void update( TestStatus status ){
        System.out.println("TestExecutionController update");
        if( status.isValidTestExecution(getSelected()) ){
            getSelected().setStatus(status);
            testExecutionFacade.edit(getSelected(), previousStatus);
        }else{
            
        }
        
    }

    
    public void loadFromGetParameters() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            setSelected(testExecutionFacade.find(idTestExecution));
            previousStatus = getSelected().getStatus();
            if (!isUserAllowed()) {
                JsfUtil.addErrorMessage("You are not owner of this tests, readonly");
            }
        }
    }
    
    // Getters and setters ----------------------------------------------------

    public boolean isUserAllowed() {
        Users user = userController.getLoggedUser();
        if (user.hasStrictRole(UserRole.TESTER)) {
            return getSelected().getTester().equals(user);
        }
        return true;
    }

    public Test getTestDefinition() {
        return getSelected().getTestDefinition();
    }

    public int getIdTestExecution() {
        return idTestExecution;
    }

    public void setIdTestExecution(int idTestExecution) {
        this.idTestExecution = idTestExecution;
    }

    public List<TestExecution> getTakenTests() {
        if (takenTests == null) {
            takenTests = testExecutionFacade.getTakenByUser(userController.getLoggedUser());
        }
        return takenTests;
    }

    public LoggedUserController getUserController() {
        return userController;
    }

    public void setUserController(LoggedUserController userController) {
        this.userController = userController;
    }

    public List<TestStatusChange> getChangesOnTest() {
        if( changesOnTest == null ){
            changesOnTest = testExecutionFacade.findByTestExecution(getSelected());
        }
        return changesOnTest;
    }
    
}
