package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.EnityWithCondition;
import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.GroupOfDevices;
import edu.agh.repotest.dao.DeviceInGroupOfDevices;
import edu.agh.repotest.dao.TestStep;
import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.jsf.util.IdHelper;
import edu.agh.repotest.jsf.util.JsfUtil;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.ProductStateFacade;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import edu.agh.repotest.session.TestStepFacade;
import edu.agh.repotest.session.TesthasEquipmentFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.DragDropEvent;

@ManagedBean(name = "testDeviceGroupController")
@ViewScoped
public class TestDeviceGroupController extends AbstractController<GroupOfDevices> implements Serializable {

    Integer actualSelectedIndex;
    @EJB
    private TestDeviceGroupFacade tdService;
    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private TestDeviceGrouphasDeviceFacade grouphasDeviceFacade;
    @EJB
    private ProductStateFacade productStateFacade;
    
    @EJB
    private TesthasEquipmentFacade testhasEquipmentFacade;
    
    @EJB
    private TestStepFacade testStepFacade;
    /*
     * DEVICE GROUPS
     */
    private String newDeviceGroupName;
    private List<GroupOfDevices> devicesGroups;
    private List<Device> devices;
    /*
     * PRODUCT STATE
     */
    private int changedProductStateIndex;
    private Map<Integer, Collection<Device>> conditionDevicesMapForStates;
    private List<ProductState> productStates;
    /*
     * EQUIPMENT
     */
    private int changedEquipmentIndex;
    private Map<Integer, Collection<Device>> conditionDevicesMapForEquip;
    private List<TesthasEquipment> equipments;
    /*
     * SCRIPT STEP
     */
    private Map<Integer, Collection<Device>> conditionDevicesMapForScriptStep;
    private List<TestStep> scriptSteps;

    public TestDeviceGroupController() {
        super(GroupOfDevices.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(tdService);
    }

    public void addNew() {
        if( newDeviceGroupName == null || newDeviceGroupName.isEmpty()){
            
            JsfUtil.addErrorMessage("Name of group must be not empty");
            return;
        }
        
        GroupOfDevices dg = new GroupOfDevices();

        dg.setName(newDeviceGroupName);
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        TestController testController = (TestController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "testController");

        dg.setTest(testController.getSelected());

        setSelected(dg);
        super.saveNew(null);
        dg = tdService.find(dg.getId());
        devicesGroups.add(dg);
        JsfUtil.addSuccessMessage(newDeviceGroupName + " added");
        setNewDeviceGroupName(null);
        

    }

    public List<Device> getDevices() {
        return devices;
    }

    public void onDeviceDrop(DragDropEvent ddEvent) {
        String[] splitted = ddEvent.getDropId().split(":");
        Integer identifier = Integer.parseInt(splitted[splitted.length - 2]);

        Device device = ((Device) ddEvent.getData());

        GroupOfDevices group = getDevicesGroups().get(identifier);
        DeviceInGroupOfDevices groupDevice = new DeviceInGroupOfDevices();
        groupDevice.setGroupOfDevices(group);
        groupDevice.setDevice(device);
        device.getTestDeviceGrouphasDeviceCollection().add(groupDevice);
        group.getDevices().add(groupDevice);
        devices.remove(device);
    }

    @Override
    public GroupOfDevices prepareCreate(ActionEvent event) {
        conditionDevicesMapForStates = new HashMap<Integer, Collection<Device>>();
        conditionDevicesMapForEquip = new HashMap<Integer, Collection<Device>>();
        conditionDevicesMapForScriptStep = new HashMap<Integer, Collection<Device>>();
        
        devicesGroups = new ArrayList<GroupOfDevices>();
        productStates = new ArrayList<ProductState>();
        equipments = new ArrayList<TesthasEquipment>();
        scriptSteps = new ArrayList<TestStep>();

        devices = getDeviceFacade().findAll();
        return super.prepareCreate(event);
    }

    @Override
    public void saveNew(ActionEvent event) {
        for (GroupOfDevices testDeviceGroup : devicesGroups) {
            for (DeviceInGroupOfDevices td : testDeviceGroup.getDevices()) {
                grouphasDeviceFacade.create(td);

            }
        }
        JsfUtil.addSuccessMessage("All group of devices saved");
    }

    public void saveProductStates(ActionEvent event) {

        for (ProductState state : productStates) {
            state.refreshCondition();
            productStateFacade.create(state);
        }
        
        JsfUtil.addSuccessMessage("All product states saved");

    }
    
    public void saveEquipments(ActionEvent event) {

        for (TesthasEquipment equipment : equipments) {
            equipment.refreshCondition();
            testhasEquipmentFacade.create(equipment);
        }
        JsfUtil.addSuccessMessage("All equipment saved");

    }

    public void saveScriptSteps(ActionEvent event) {
        for( TestStep testStep: scriptSteps){
            testStep.refreshCondition();
            testStepFacade.create(testStep);
        }
        JsfUtil.addSuccessMessage("All script steps saved");
    }

    public void refreshDeviceForState(AjaxBehaviorEvent e) {
        System.out.println("refreshDeviceForState");
        refreshMap(e, conditionDevicesMapForStates, productStates);
    }

    public void refreshDeviceForEquipment(AjaxBehaviorEvent e) {
        System.out.println("refreshDeviceForEquipment");
        refreshMap(e, conditionDevicesMapForEquip, equipments);
    }

    public void refreshDeviceForScriptStep(AjaxBehaviorEvent e) {
        
        System.out.println("refreshDeviceForScriptStep");
        refreshMap(e, conditionDevicesMapForScriptStep, scriptSteps);
    }

    public void refreshMap(AjaxBehaviorEvent e, Map<Integer, Collection<Device>> mapDevices, List<? extends EnityWithCondition> enityWithConditions) {

        String rawIndexOfSelected = e.getComponent().getAttributes().get("index").toString();
        int indexOfSelected = Integer.parseInt(rawIndexOfSelected);
        final EnityWithCondition enityWithCondition = enityWithConditions.get(indexOfSelected);

        mapDevices.put(indexOfSelected, getDevicesForGroup(enityWithCondition));
        printMap(mapDevices);


    }

    private Collection<Device> getDevicesForGroup(EnityWithCondition enityWithCondition) {
        int idx = devicesGroups.indexOf(enityWithCondition.getRawCondition().getDeviceGroup());
        return devicesGroups.get(idx).getRawDevices();

    }

    public void addNewProductState() {
        ProductState productState = new ProductState();
        productState.setTestidTest(getTest());
        productState.setRawCondition(new Condition());
        productStates.add(productState);
        
        conditionDevicesMapForStates.put(productStates.size(), new ArrayList());
    }

    public void addNewScriptStep() {

        TestStep testStep = new TestStep();
        testStep.setTestidTest(getTest());
        testStep.setRawCondition(new Condition());
        scriptSteps.add(testStep);
        conditionDevicesMapForScriptStep.put(scriptSteps.size(), new ArrayList());
    }

    public void addNewEquipment() {


        TesthasEquipment testhasEquipment = new TesthasEquipment();
        testhasEquipment.setTest(getTest());
        testhasEquipment.setRawCondition(new Condition());
        equipments.add(testhasEquipment);
        conditionDevicesMapForEquip.put(equipments.size(), new ArrayList());
    }

    private Test getTest() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        TestController testController = (TestController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "testController");

        return testController.getSelected();
    }

    private void printMap(Map<Integer, Collection<Device>> map) {

        System.err.println("Begin" + map.size());
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Integer, Collection<Device>> entry : map.entrySet()) {
            builder.append("Map KEY::" + entry.getKey());
            builder.append("\n");
            for (Device device : entry.getValue()) {
                builder.append("\t").append("device id::" + device.getIdDevice()).append("\n");
            }
        }
        System.err.println(builder);


    }

    /**
     * @return the devicesGroups
     */
    public List<GroupOfDevices> getDevicesGroups() {
        System.out.println("getDevicesGroups");
        return devicesGroups;
    }

    /**
     * @param devicesGroups the devicesGroups to set
     */
    public void setDevicesGroups(List<GroupOfDevices> devicesGroups) {
        System.out.println("setDevicesGroups");
        this.devicesGroups = devicesGroups;
    }

    /**
     * @return the newDeviceGroupName
     */
    public String getNewDeviceGroupName() {
        System.out.println("getNewDeviceGroupName");
        return newDeviceGroupName;
    }

    /**
     * @param newDeviceGroupName the newDeviceGroupName to set
     */
    public void setNewDeviceGroupName(String newDeviceGroupName) {
        System.out.println("setNewDeviceGroupName");
        this.newDeviceGroupName = newDeviceGroupName;
    }

    /**
     * @return the deviceFacade
     */
    public DeviceFacade getDeviceFacade() {
        System.out.println("getDeviceFacade");
        return deviceFacade;
    }

    /**
     * @param deviceFacade the deviceFacade to set
     */
    public void setDeviceFacade(DeviceFacade deviceFacade) {
        System.out.println("setDeviceFacade");
        this.deviceFacade = deviceFacade;
    }

    public TestDeviceGroupFacade getTdService() {
        System.out.println("getTdService");
        return tdService;
    }

    public void setTdService(TestDeviceGroupFacade tdService) {
        System.out.println("setTdService");
        this.tdService = tdService;
    }

    public TestDeviceGrouphasDeviceFacade getGrouphasDeviceFacade() {
        System.out.println("getGrouphasDeviceFacade");
        return grouphasDeviceFacade;
    }

    public void setGrouphasDeviceFacade(TestDeviceGrouphasDeviceFacade grouphasDeviceFacade) {
        System.out.println("setGrouphasDeviceFacade");
        this.grouphasDeviceFacade = grouphasDeviceFacade;
    }

    public List<ProductState> getProductStates() {
        System.out.println("getProductStates");
        return productStates;
    }

    public void setProductStates(List<ProductState> productStates) {
        System.out.println("setProductStates");
        this.productStates = productStates;
    }

    public Integer getActualSelectedIndex() {
        System.out.println("getActualSelectedIndex");
        return actualSelectedIndex;
    }

    public void setActualSelectedIndex(Integer actualSelectedIndex) {
        System.out.println("setActualSelectedIndex");
        this.actualSelectedIndex = actualSelectedIndex;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMap() {
        System.out.println("getConditionDevicesMap");
        return conditionDevicesMapForStates;
    }

    public void setConditionDevicesMap(Map<Integer, Collection<Device>> conditionDevicesMap) {
        System.out.println("setConditionDevicesMap");
        this.conditionDevicesMapForStates = conditionDevicesMap;
    }

    public int getChangedProductStateIndex() {
        System.out.println("getChangedProductStateIndex");
        return changedProductStateIndex;
    }

    public void setChangedProductStateIndex(int changedProductStateIndex) {
        System.out.println("setChangedProductStateIndex");

        this.changedProductStateIndex = changedProductStateIndex;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMapForStates() {
        System.out.println("getConditionDevicesMapForStates");
        return conditionDevicesMapForStates;
    }

    public void setConditionDevicesMapForStates(Map<Integer, Collection<Device>> conditionDevicesMapForStates) {
        System.out.println("setConditionDevicesMapForStates");
        this.conditionDevicesMapForStates = conditionDevicesMapForStates;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMapForEquip() {
        System.out.println("getConditionDevicesMapForEquip");
        return conditionDevicesMapForEquip;
    }

    public void setConditionDevicesMapForEquip(Map<Integer, Collection<Device>> conditionDevicesMapForEquip) {
        System.out.println("setConditionDevicesMapForEquip");
        this.conditionDevicesMapForEquip = conditionDevicesMapForEquip;
    }

    public List<TesthasEquipment> getEquipments() {
        System.out.println("getEquipments");
        return equipments;
    }

    public void setEquipments(List<TesthasEquipment> equipments) {
        System.out.println("setEquipments");
        this.equipments = equipments;
    }

    public int getChangedEquipmentIndex() {
        System.out.println("getChangedEquipmentIndex");
        return changedEquipmentIndex;
    }

    public void setChangedEquipmentIndex(int changedEquipmentIndex) {
        System.out.println("setChangedEquipmentIndex");
        this.changedEquipmentIndex = changedEquipmentIndex;
    }

    public Map<Integer, Collection<Device>> getConditionDevicesMapForScriptStep() {
        System.out.println("getConditionDevicesMapForScriptStep");
        return conditionDevicesMapForScriptStep;
    }

    public void setConditionDevicesMapForScriptStep(Map<Integer, Collection<Device>> conditionDevicesMapForScriptStep) {
        System.out.println("setConditionDevicesMapForScriptStep");
        this.conditionDevicesMapForScriptStep = conditionDevicesMapForScriptStep;
    }

    public List<TestStep> getScriptSteps() {
        System.out.println("getScriptSteps" + scriptSteps + scriptSteps.size());
        return scriptSteps;
    }

    public ProductStateFacade getProductStateFacade() {
        return productStateFacade;
    }

    public void setProductStateFacade(ProductStateFacade productStateFacade) {
        this.productStateFacade = productStateFacade;
    }

    public TesthasEquipmentFacade getTesthasEquipmentFacade() {
        return testhasEquipmentFacade;
    }

    public void setTesthasEquipmentFacade(TesthasEquipmentFacade testhasEquipmentFacade) {
        this.testhasEquipmentFacade = testhasEquipmentFacade;
    }

    public TestStepFacade getTestStepFacade() {
        return testStepFacade;
    }

    public void setTestStepFacade(TestStepFacade testStepFacade) {
        this.testStepFacade = testStepFacade;
    }
    

    public void setScriptSteps(List<TestStep> scriptSteps) {
        System.out.println("setScriptSteps");
        this.scriptSteps = scriptSteps;
    }
}
