package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.DeviceInGroupOfDevices;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testDeviceGrouphasDeviceController")
@ViewScoped
public class TestDeviceGrouphasDeviceController extends AbstractController<DeviceInGroupOfDevices> implements Serializable {

    @EJB
    private TestDeviceGrouphasDeviceFacade ejbFacade;

    public TestDeviceGrouphasDeviceController() {
        super(DeviceInGroupOfDevices.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

   

 
}
