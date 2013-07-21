package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Authorities;
import edu.agh.repotest.dao.Users;
import edu.agh.repotest.session.AuthoritiesFacade;
import edu.agh.repotest.session.UsersFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usersController")
@ViewScoped
public class UsersController extends AbstractController<Users> implements Serializable {

    List<Authorities> authorities;
    @EJB
    private UsersFacade ejbFacade;
    
    @EJB
    private AuthoritiesFacade authoritiesFacade;
    public UsersController() {
        super(Users.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        authorities = authoritiesFacade.findAll();
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }
    
    
}
