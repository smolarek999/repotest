package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.TestExecution;
import edu.agh.repotest.dao.TestExecutionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testExecutionController")
@ViewScoped
public class TestExecutionController extends AbstractController<TestExecution> implements Serializable {

    @EJB
    private TestExecutionFacade ejbFacade;

    public TestExecutionController() {
        super(TestExecution.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
