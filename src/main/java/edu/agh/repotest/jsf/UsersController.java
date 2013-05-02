package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Users;
import edu.agh.repotest.dao.UsersFacade;
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
