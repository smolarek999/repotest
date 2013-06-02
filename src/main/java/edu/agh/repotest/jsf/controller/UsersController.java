package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Users;
import edu.agh.repotest.session.UsersFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usersController")
@ViewScoped
public class UsersController extends AbstractController<Users> implements Serializable {

    @EJB
    private UsersFacade ejbFacade;
    public UsersController() {
        super(Users.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}