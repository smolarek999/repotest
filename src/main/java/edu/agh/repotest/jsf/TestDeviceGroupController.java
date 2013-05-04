package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testDeviceGroupController")
@ViewScoped
public class TestDeviceGroupController extends AbstractController<TestDeviceGroup> implements Serializable {

    @EJB
    private TestDeviceGroupFacade ejbFacade;

    public TestDeviceGroupController() {
        super(TestDeviceGroup.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    
}
