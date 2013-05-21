package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.dao.TestDeviceGroup;
import edu.agh.repotest.dao.TestDeviceGroupDevices;
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
    private Map<Integer, Collection<Device>> conditionDevicesMap;
    private List<ProductState> productStates;

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
        super.saveNew(new ActionEvent(new UIComponentBase() {
            @Override
            public String getFamily() {
                return "";
            }
        }));
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
        addNewProductState();
        conditionDevicesMap = new HashMap<Integer, Collection<Device>>();
        conditionDevicesMap.put(0, new ArrayList<Device>());
        conditionDevicesMap.put(1, new ArrayList<Device>());
        conditionDevicesMap.put(2, new ArrayList<Device>());
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
    

    public void actionListener(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Made it!");
    }

    public void morePressed(AjaxBehaviorEvent e) {
        
        String psId = IdHelper.getIdPart(e.getComponent().getClientId(), -2);
        ProductState productState = productStates.get(Integer.parseInt(psId));
        int indexOfPressed = devicesGroups.indexOf(productState.getRawCondition().get(0).getDeviceGroup());
        
        conditionDevicesMap.put(Integer.parseInt(psId), devicesGroups.get(indexOfPressed).getRawDevices());
        productState.getRawCondition();
        printMap(conditionDevicesMap);
        
        
        

    }

    public void handleDeviceChange() {
        System.out.println("handleDeviceChange");
    }
    
      public void addNewProductState() {
          ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        TestController testController = (TestController) FacesContext.getCurrentInstance().getApplication()
                .getELResolver().getValue(elContext, null, "testController");
          System.out.println("addNewProductState");
        ProductState productState = new ProductState();
        productState.setTestidTest(testController.getSelected());
        productState.getRawCondition().add(new Condition());
        productStates.add(productState);
         System.out.println("END addNewProductState");
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
        return conditionDevicesMap;
    }

    public void setConditionDevicesMap(Map<Integer, Collection<Device>> conditionDevicesMap) {
        this.conditionDevicesMap = conditionDevicesMap;
    }
    
    private void printMap( Map<Integer, Collection<Device>> map ){
         System.err.println("Begin" + map.size());
        StringBuilder builder = new StringBuilder();
        
        for( Map.Entry<Integer, Collection<Device>> entry: map.entrySet()){
            builder.append(entry.getKey());
            builder.append("\n");
            for( Device device : entry.getValue()){
                builder.append("\t").append(device.getIdDevice()).append("\n");
            }
        }
        System.err.println(builder);
        
        
    }
}
