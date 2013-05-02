package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.TestGroup;
import edu.agh.repotest.dao.TestGroupFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testGroupController")
@ViewScoped
public class TestGroupController extends AbstractController<TestGroup> implements Serializable {

    @EJB
    private TestGroupFacade ejbFacade;

    public TestGroupController() {
        super(TestGroup.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
