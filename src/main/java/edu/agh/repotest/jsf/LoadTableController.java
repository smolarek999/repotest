package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.LoadTable;
import edu.agh.repotest.session.LoadTableFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "loadTableController")
@ViewScoped
public class LoadTableController extends AbstractController<LoadTable> implements Serializable {

    @EJB
    private LoadTableFacade ejbFacade;

    public LoadTableController() {
        super(LoadTable.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
