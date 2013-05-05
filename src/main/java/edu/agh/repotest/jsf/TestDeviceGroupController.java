package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "testDeviceGroupController")
@ViewScoped
public class TestDeviceGroupController extends AbstractController<TestDeviceGroup> implements Serializable {

    @EJB
    private TestDeviceGroupFacade tdService;
    @EJB
    private DeviceFacade deviceFacade;
    
    @EJB
    private TestDeviceGrouphasDeviceFacade grouphasDeviceFacade;
    private String newDeviceGroupName;
    private List<TestDeviceGroup> devicesGroups;
    private List<Device> devices;

    public TestDeviceGroupController() {
        super(TestDeviceGroup.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(tdService);
    }

    public void addNew() {

        TestDeviceGroup dg = new TestDeviceGroup();

        dg.setName(newDeviceGroupName);
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        TestController testController = (TestController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "testController");
        System.out.println(testController.getSelected().getIdTest());
        dg.setTest(testController.getSelected());
        devicesGroups.add(dg);
        setSelected(dg);            
        super.saveNew(new ActionEvent(new UIComponentBase() {

            @Override
            public String getFamily() {
                return "";
            }
        })); 
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void onDeviceDrop(DragDropEvent ddEvent) {
        String[] splitted = ddEvent.getDropId().split(":");
        Integer identifier = Integer.parseInt(splitted[splitted.length - 2]);


        Device device = ((Device) ddEvent.getData());
        TestDeviceGroupDevices groupDevice = new TestDeviceGroupDevices();
        groupDevice.setDevice(device);
        
        TestDeviceGroup group = getDevicesGroups().get(identifier);
        groupDevice.setTestDeviceGroup(group);
        group.getDevices().add(groupDevice);
        devices.remove(device);
    }

    @Override
    public TestDeviceGroup prepareCreate(ActionEvent event) {
        devicesGroups = new ArrayList<TestDeviceGroup>();

        devices = getDeviceFacade().findAll();
        return super.prepareCreate(event);
    }

    @Override
    public void saveNew(ActionEvent event) {
        for (TestDeviceGroup testDeviceGroup : devicesGroups) {
            
            for( TestDeviceGroupDevices td : testDeviceGroup.getDevices() ){
                td.setTestDeviceGroup(testDeviceGroup);
                System.err.println(td.getTestDeviceGroup().getId());
                grouphasDeviceFacade.create(td);
            }
            
            
        }
        
    }
    
    

    /**
     * @return the devicesGroups
     */
    public List<TestDeviceGroup> getDevicesGroups() {
        return devicesGroups;
    }

    /**
     * @param devicesGroups the devicesGroups to set
     */
    public void setDevicesGroups(List<TestDeviceGroup> devicesGroups) {
        this.devicesGroups = devicesGroups;
    }

    /**
     * @return the newDeviceGroupName
     */
    public String getNewDeviceGroupName() {
        return newDeviceGroupName;
    }

    /**
     * @param newDeviceGroupName the newDeviceGroupName to set
     */
    public void setNewDeviceGroupName(String newDeviceGroupName) {
        this.newDeviceGroupName = newDeviceGroupName;
    }

    /**
     * @return the deviceFacade
     */
    public DeviceFacade getDeviceFacade() {
        return deviceFacade;
    }

    /**
     * @param deviceFacade the deviceFacade to set
     */
    public void setDeviceFacade(DeviceFacade deviceFacade) {
        this.deviceFacade = deviceFacade;
    }
}
