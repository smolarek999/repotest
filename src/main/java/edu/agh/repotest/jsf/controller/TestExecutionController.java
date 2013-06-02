/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecution;
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

    @EJB
    TestExecutionFacade testExecutionFacade;
    private int idTestExecution;
    private List<TestExecution> takenTests;
    @ManagedProperty("#{loggedUserController}")
    LoggedUserController userController;

    @PostConstruct
    public void init() {
        super.setFacade(testExecutionFacade);
        System.out.println("TestExecutionController.init");

    }

    public void loadFromGetParameters() {
        System.out.println("TestExecutionController.loadFromGetParameters");
        if (!FacesContext.getCurrentInstance().isPostback()) {
            setSelected(testExecutionFacade.find(idTestExecution));
        }
    }

    public Test getTestDefinition() {
        return getSelected().getTestDefinition();
    }

    public int getIdTestExecution() {
        return idTestExecution;
    }

    public void setIdTestExecution(int idTestExecution) {
        System.out.println("setIdTestExecution");
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
    
}
