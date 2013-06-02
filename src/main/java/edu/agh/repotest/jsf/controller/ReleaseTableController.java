package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Release;
import edu.agh.repotest.session.ReleaseTableFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "releaseTableController")
@ViewScoped
public class ReleaseTableController extends AbstractController<Release> implements Serializable {

    @EJB
    private ReleaseTableFacade ejbFacade;

    public ReleaseTableController() {
        super(Release.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
