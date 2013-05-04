package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.TestStep;
import edu.agh.repotest.session.TestStepFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testStepController")
@ViewScoped
public class TestStepController extends AbstractController<TestStep> implements Serializable {

    @EJB
    private TestStepFacade ejbFacade;

    public TestStepController() {
        super(TestStep.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
