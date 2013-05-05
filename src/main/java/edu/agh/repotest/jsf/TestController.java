package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.ProductStepAndCondition;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.TestFacade;
import edu.agh.repotest.session.TestGroupFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@ManagedBean(name = "testController")
@ViewScoped
public class TestController extends AbstractController<Test> implements Serializable {

    @EJB
    private TestFacade ejbFacade;
    @EJB
    private TestGroupFacade ejbGroupFacade;
    @ManagedProperty("#{testDeviceGroupController}")
    private TestDeviceGroupController deviceGroupController;

    public TestController() {
        super(Test.class);
    }

    public void nodeListener(ActionEvent event) {
        prepareCreate(event);
        Integer rawId = (Integer) event.getComponent().getAttributes().get("rawParentId");
        getSelected().setTestGroupidTestGroup(ejbGroupFacade.find(rawId));
        getDeviceGroupController().prepareCreate(event);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

    }

    /**
     * @return the deviceGroupController
     */
    public TestDeviceGroupController getDeviceGroupController() {
        return deviceGroupController;
    }

    /**
     * @param deviceGroupController the deviceGroupController to set
     */
    public void setDeviceGroupController(TestDeviceGroupController deviceGroupController) {
        this.deviceGroupController = deviceGroupController;
    }
}
