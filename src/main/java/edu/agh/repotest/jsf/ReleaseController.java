package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Release;
import edu.agh.repotest.dao.ReleaseFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "releaseController")
@ViewScoped
public class ReleaseController extends AbstractController<Release> implements Serializable {

    @EJB
    private ReleaseFacade ejbFacade;

    public ReleaseController() {
        super(Release.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
