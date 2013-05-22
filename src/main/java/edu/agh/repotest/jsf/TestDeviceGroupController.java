package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.Equipment;
import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.jsf.util.IdHelper;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.ProductStateFacade;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "testDeviceGroupController")
@ViewScoped
public class TestDeviceGroupController extends AbstractController<TestDeviceGroup> implements Serializable {

    Integer actualSelectedIndex;
    @EJB
    private TestDeviceGroupFacade tdService;
    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private TestDeviceGrouphasDeviceFacade grouphasDeviceFacade;
    @EJB
    private ProductStateFacade productStateFacade;
    private String newDeviceGroupName;
    private List<TestDeviceGroup> devicesGroups;
    private List<Device> devices;
    
    private int changedProductStateIndex;
    private Map<Integer, Collection<Device>> conditionDevicesMapForStates;
    private List<ProductState> productStates;
    
    private int changedEquipmentIndex;
    private Map<Integer, Collection<Device>> conditionDevicesMapForEquip;
    private List<TesthasEquipment> equipments;

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

        dg.setTest(testController.getSelected());

        setSelected(dg);
        super.saveNew(null);
        dg = tdService.find(dg.getId());
        devicesGroups.add(dg);

    }

    public List<Device> getDevices() {
        return devices;
    }

    public void onDeviceDrop(DragDropEvent ddEvent) {
        String[] splitted = ddEvent.getDropId().split(":");
        Integer identifier = Integer.parseInt(splitted[splitted.length - 2]);

        Device device = ((Device) ddEvent.getData());

        TestDeviceGroup group = getDevicesGroups().get(identifier);
        TestDeviceGroupDevices groupDevice = new TestDeviceGroupDevices();
        groupDevice.setDeviceGroups(group);
        groupDevice.setDevice(device);
        device.getTestDeviceGrouphasDeviceCollection().add(groupDevice);
        group.getDevices().add(groupDevice);

        devices.remove(device);
    }

    @Override
    public TestDeviceGroup prepareCreate(ActionEvent event) {
        devicesGroups = new ArrayList<TestDeviceGroup>();
        productStates = new ArrayList<ProductState>();
        equipments = new ArrayList<TesthasEquipment>();
        addNewProductState();
        conditionDevicesMapForStates = new HashMap<Integer, Collection<Device>>();
        conditionDevicesMapForEquip = new HashMap<Integer, Collection<Device>>();

        devices = getDeviceFacade().findAll();
        return super.prepareCreate(event);
    }

    @Override
    public void saveNew(ActionEvent event) {
        for (TestDeviceGroup testDeviceGroup : devicesGroups) {
            for (TestDeviceGroupDevices td : testDeviceGroup.getDevices()) {
                grouphasDeviceFacade.create(td);
            }
        }
    }

    public void saveProductStates(ActionEvent event) {
        for (ProductState state : productStates) {
            productStateFacade.create(state);
        }

    }

    public void refreshDeviceForState(AjaxBehaviorEvent e) {
        SelectOneMenu menu = (SelectOneMenu)e.getComponent();
        TestDeviceGroup deviceGroup = (TestDeviceGroup)menu.getValue();
        //ProductState productState = productStates.get(changedProductStateIndex);

        conditionDevicesMapForStates.put(changedProductStateIndex, deviceGroup.getRawDevices());
        //printMap(conditionDevicesMapForStates);

    }

    public void refreshDeviceForEquipment(AjaxBehaviorEvent e) {

        SelectOneMenu menu = (SelectOneMenu)e.getComponent();
        TestDeviceGroup deviceGroup = (TestDeviceGroup)menu.getValue();
        //ProductState productState = productStates.get(changedProductStateIndex);

        conditionDevicesMapForEquip.put(changedEquipmentIndex, deviceGroup.getRawDevices());
        //printMap(conditionDevicesMapForStates);

    }


    public void addNewProductState() {
        ProductState productState = new ProductState();
        productState.setTestidTest(getTest());
        productState.getRawCondition().add(new Condition());
        productStates.add(productState);
    }
    
     public void addNewEquipment() {
        
        TesthasEquipment testhasEquipment = new TesthasEquipment();
        testhasEquipment.setTest(getTest());
        testhasEquipment.getRawCondition().add(new Condition());
        equipments.add(testhasEquipment);
    }

        
    private Test getTest(){
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        TestController testController = (TestController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "testController");
        return testController.getSelected();
    }
    
    
        private void printMap(Map<Integer, Collection<Device>> map) {
        System.err.println("Begin" + map.size());
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Integer, Collection<Device>> entry : map.entrySet()) {
            builder.append(entry.getKey());
            builder.append("\n");
            for (Device device : entry.getValue()) {
                builder.append("\t").append(device.getIdDevice()).append("\n");
            }
        }
        System.err.println(builder);


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

    public TestDeviceGroupFacade getTdService() {
        return tdService;
    }

    public void setTdService(TestDeviceGroupFacade tdService) {
        this.tdService = tdService;
    }

    public TestDeviceGrouphasDeviceFacade getGrouphasDeviceFacade() {
        return grouphasDeviceFacade;
    }

    public void setGrouphasDeviceFacade(TestDeviceGrouphasDeviceFacade grouphasDeviceFacade) {
        this.grouphasDeviceFacade = grouphasDeviceFacade;
    }

    public List<ProductState> getProductStates() {
        return productStates;
    }

    public void setProductStates(List<ProductState> productStates) {
        this.productStates = productStates;
    }

    public Integer getActualSelectedIndex() {
        return actualSelectedIndex;
    }

    public void setActualSelectedIndex(Integer actualSelectedIndex) {
        this.actualSelectedIndex = actualSelectedIndex;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMap() {
        return conditionDevicesMapForStates;
    }

    public void setConditionDevicesMap(Map<Integer, Collection<Device>> conditionDevicesMap) {
        this.conditionDevicesMapForStates = conditionDevicesMap;
    }

    public int getChangedProductStateIndex() {
        return changedProductStateIndex;
    }

    public void setChangedProductStateIndex(int changedProductStateIndex) {
        this.changedProductStateIndex = changedProductStateIndex;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMapForStates() {
        return conditionDevicesMapForStates;
    }

    public void setConditionDevicesMapForStates(Map<Integer, Collection<Device>> conditionDevicesMapForStates) {
        this.conditionDevicesMapForStates = conditionDevicesMapForStates;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMapForEquip() {
        return conditionDevicesMapForEquip;
    }

    public void setConditionDevicesMapForEquip(Map<Integer, Collection<Device>> conditionDevicesMapForEquip) {
        this.conditionDevicesMapForEquip = conditionDevicesMapForEquip;
    }

    public List<TesthasEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<TesthasEquipment> equipments) {
        this.equipments = equipments;
    }

    public int getChangedEquipmentIndex() {
        return changedEquipmentIndex;
    }

    public void setChangedEquipmentIndex(int changedEquipmentIndex) {
        this.changedEquipmentIndex = changedEquipmentIndex;
    }
    
    
    


}
