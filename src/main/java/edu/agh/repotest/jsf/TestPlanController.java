package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.session.TestPlanFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testPlanController")
@ViewScoped
public class TestPlanController extends AbstractController<TestPlan> implements Serializable {

    @EJB
    private TestPlanFacade ejbFacade;

    public TestPlanController() {
        super(TestPlan.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
