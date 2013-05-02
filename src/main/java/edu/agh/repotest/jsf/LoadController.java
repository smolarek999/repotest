package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Load;
import edu.agh.repotest.dao.LoadFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "loadController")
@ViewScoped
public class LoadController extends AbstractController<Load> implements Serializable {

    @EJB
    private LoadFacade ejbFacade;

    public LoadController() {
        super(Load.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
