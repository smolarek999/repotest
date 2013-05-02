package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Mode;
import edu.agh.repotest.dao.ModeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "modeController")
@ViewScoped
public class ModeController extends AbstractController<Mode> implements Serializable {

    @EJB
    private ModeFacade ejbFacade;

    public ModeController() {
        super(Mode.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
