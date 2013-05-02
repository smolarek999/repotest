package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.StrategyExecution;
import edu.agh.repotest.dao.StrategyExecutionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "strategyExecutionController")
@ViewScoped
public class StrategyExecutionController extends AbstractController<StrategyExecution> implements Serializable {

    @EJB
    private StrategyExecutionFacade ejbFacade;

    public StrategyExecutionController() {
        super(StrategyExecution.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
