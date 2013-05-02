package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Strategy;
import edu.agh.repotest.dao.StrategyFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "strategyController")
@ViewScoped
public class StrategyController extends AbstractController<Strategy> implements Serializable {

    @EJB
    private StrategyFacade ejbFacade;

    public StrategyController() {
        super(Strategy.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
