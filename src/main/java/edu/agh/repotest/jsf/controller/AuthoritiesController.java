package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Authorities;
import edu.agh.repotest.session.AuthoritiesFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "authoritiesController")
@ViewScoped
public class AuthoritiesController extends AbstractController<Authorities> implements Serializable {

    @EJB
    private AuthoritiesFacade ejbFacade;

    public AuthoritiesController() {
        super(Authorities.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
