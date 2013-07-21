/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Authorities;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.dao.UserRole;
import edu.agh.repotest.dao.Users;
import edu.agh.repotest.session.TestExecutionFacade;
import edu.agh.repotest.session.TestPlanFacade;
import edu.agh.repotest.session.UsersFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class LoggedUserController implements Serializable{
    private Users loggedUser;
    @EJB
    UsersFacade usersFacade;
    
    @EJB
    TestPlanFacade testPlanFacade;
    

    public Users getLoggedUser() {
        System.err.println("SET USER start");
        if (loggedUser == null) {
            final Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                System.err.println("SET USER");
                loggedUser = usersFacade.getByName(principal.getName());
            }
        }
        return loggedUser;
    }

    public String logout() {
        loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
        return "/login.xhtml?faces-redirect=true";
    }
    
    public List<TestPlan> getTestPlans(){
        return testPlanFacade.getByUser(loggedUser);
    }
    
     
    
  
    
   

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    public boolean isNonLoggedUser(){
        return loggedUser == null;
    }
    
     public boolean isAdmin(){
         
        return !isNonLoggedUser() && loggedUser.hasRole(UserRole.ADMIN);
    }
     
      public boolean isUser(){
        return  !isNonLoggedUser() && loggedUser.hasRole(UserRole.TESTER);
    }
 
}
