package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Device;
import edu.agh.repotest.session.DeviceFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "deviceController")
@ViewScoped
public class DeviceController extends AbstractController<Device> implements Serializable {

    @EJB
    private DeviceFacade ejbFacade;

    public DeviceController() {
        super(Device.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
