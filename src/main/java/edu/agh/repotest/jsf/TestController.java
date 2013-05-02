package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Test;
import edu.agh.repotest.dao.TestFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testController")
@ViewScoped
public class TestController extends AbstractController<Test> implements Serializable {

    @EJB
    private TestFacade ejbFacade;

    public TestController() {
        super(Test.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
