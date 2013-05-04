package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.TestFacade;
import edu.agh.repotest.session.TestGroupFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "testController")
@ViewScoped
public class TestController extends AbstractController<Test> implements Serializable {

    private List<TestDeviceGroup> devicesGroups;
    private List<Device> devices;
    private String newDeviceGroupName;
    private TestDeviceGroup deviceGroupForCondition;
    private List<Device> devicesForCondition;
    @EJB
    private TestFacade ejbFacade;
    @EJB
    private TestGroupFacade ejbGroupFacade;
    @EJB
    private DeviceFacade deviceFacade;

    public TestController() {
        super(Test.class);
    }
/*
 *         <h:panelGrid columns="2" cellpadding="5">  
                            <p:selectOneMenu id="city" value="#{testController.deviceGroupForCondition}">  
                                <f:selectItem itemLabel="Select City" itemValue="" />  
                                <f:selectItems value="#{testController.deviceGroups}" />  
                                <p:ajax update="suburbs"  
                                        listener="#{testController.handleConditionDeviceGroupChange}" />  
                            </p:selectOneMenu>  

                            <p:selectManyButton  id="devices" value="#{testController.devicesForCondition}">  
                                <f:selectItems value="#{testController.deviceGroupForCondition.devices}" />  
                            </p:selectManyButton> 
                        </h:panelGrid>  
 * 
 */
      public void handleCityChange() {  
        System.err.println(deviceGroupForCondition.getName());  
    }  

    public void addNewGroupName(){
        
        TestDeviceGroup dg = new TestDeviceGroup();
        
        dg.setName(newDeviceGroupName);
        devicesGroups.add(dg);
    }
    public void nodeListener(ActionEvent event) {
        prepareCreate(event);
        Integer rawId = (Integer) event.getComponent().getAttributes().get("rawParentId");
        getSelected().setTestGroupidTestGroup(ejbGroupFacade.find(rawId));
    }

    public List<Device> getDevices() {
        return devices;
    }

    @Override
    public Test prepareCreate(ActionEvent event) {
        devicesGroups = new ArrayList<TestDeviceGroup>();
        
        devices = deviceFacade.findAll();
        return super.prepareCreate(event); //To change body of generated methods, choose Tools | Templates.
    }

    public void onDeviceDrop(DragDropEvent ddEvent) {
        String [] splitted = ddEvent.getDropId().split(":");
        Integer identifier =  Integer.parseInt(splitted[splitted.length - 2]);
        
        
        Device device = ((Device) ddEvent.getData());
        TestDeviceGroupDevices groupDevice = new TestDeviceGroupDevices();
        groupDevice.setDevice(device);
        TestDeviceGroup group = getDevicesGroups().get(identifier);
        group.getDevices().add(groupDevice);
        devices.remove(device);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

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
     * @return the deviceGroupForCondition
     */
    public TestDeviceGroup getDeviceGroupForCondition() {
        return deviceGroupForCondition;
    }

    /**
     * @param deviceGroupForCondition the deviceGroupForCondition to set
     */
    public void setDeviceGroupForCondition(TestDeviceGroup deviceGroupForCondition) {
        this.deviceGroupForCondition = deviceGroupForCondition;
    }

    /**
     * @return the devicesForCondition
     */
    public List<Device> getDevicesForCondition() {
        return devicesForCondition;
    }

    /**
     * @param devicesForCondition the devicesForCondition to set
     */
    public void setDevicesForCondition(List<Device> devicesForCondition) {
        this.devicesForCondition = devicesForCondition;
    }
    
    
}
