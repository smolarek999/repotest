package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Authorities;
import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.EnityWithCondition;
import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.GroupOfDevices;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestExecutionStep;
import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.dao.TestStep;
import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.dao.UserRole;
import edu.agh.repotest.dao.Users;
import edu.agh.repotest.jsf.util.JsfUtil;
import edu.agh.repotest.session.TestFacade;
import edu.agh.repotest.session.TestPlanFacade;
import edu.agh.repotest.session.UsersFacade;
import edu.agh.repotest.util.ConditionHelper;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "testPlanController")
@ViewScoped
public class TestPlanController extends AbstractController<TestPlan> implements Serializable {

    @EJB
    private TestPlanFacade ejbFacade;
    @EJB
    private TestFacade testFacade;
    @EJB
    private UsersFacade usersFacade;
    TestExecution[] testsToRemove;
    Test[] testsToAdd;
    List<Test> testsAvailableToAdd;
    List<Test> filteredTestsAvailableToAdd;
    List<TestExecution> addedTests;
    Test selectedTest;
    Map<Integer, Device> devicesGroupSelectedDevice = new HashMap<Integer, Device>();
    List<Users> availableTeamMembers;
    
    private int id;

    public TestPlanController() {
        super(TestPlan.class);

    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    public void loadFromGetParameters() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            setSelected(ejbFacade.find(id));
        }
    }

    @Override
    public TestPlan prepareCreate(ActionEvent event) {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            super.prepareCreate(event);
            testsAvailableToAdd = testFacade.findAll();
            addedTests = new ArrayList<TestExecution>();
            getSelected().setTests(new LinkedList<TestExecution>());
            availableTeamMembers = usersFacade.getByRole(UserRole.TESTER);

        }

        return getSelected();

    }

    public void removeTest() {
        final Iterator<TestExecution> iterator = getSelected().getTests().iterator();
        for (GroupOfDevices group : selectedTest.getDevicesGroups()) {
            devicesGroupSelectedDevice.remove(group.getId());
        }


        while (iterator.hasNext()) {
            TestExecution tex = iterator.next();
            if (tex.getTestDefinition().equals(selectedTest)) {
                iterator.remove();
                testsAvailableToAdd.add(tex.getTestDefinition());

            }
        }
    }

    public void removeMultipleTests() {
        if (testsToRemove != null) {
            for (TestExecution testExecution : testsToRemove) {
                setSelectedTest(testExecution.getTestDefinition());
                removeTest();
            }
        }
    }

    public void addSelectedTest() {
        addSelectedTest(true);
    }

    public void addSelectedTest(boolean remove) {
        TestExecution tex = new TestExecution();
        tex.setTestDefinition(selectedTest);
        tex.setStatus(TestStatus.WAITING);
        tex.setTestPlan(getSelected());
        getAddedTests().add(tex);
        System.out.println("adding test " + selectedTest.getName());
        System.out.println("count of device groups: " + selectedTest.getDevicesGroups().size());
        System.out.println("count of test steps: " + selectedTest.getTestStepCollection().size());
        for (GroupOfDevices group : selectedTest.getDevicesGroups()) {
            System.err.print("iterate over" + group.getName());
            devicesGroupSelectedDevice.put(group.getId(), null);
        }
        if (remove) {
            testsAvailableToAdd.remove(selectedTest);
        }


    }

    public void addMultipleTests() {
        for (Test test : testsToAdd) {
            setSelectedTest(test);
            addSelectedTest();
        }
    }

    @Override
    public void saveNew(ActionEvent event) {


        for (TestExecution testExecution : getSelected().getTests()) {
            cookTestExecution(testExecution);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        getSelected().setCreationDate(timestamp);
        super.saveNew(event);
        // add devices
        // add test steps 1 
        // add equipment
        // add product states 1
        //To change body of generated methods, choose Tools | Templates.
    }

    private void cookTestExecution(TestExecution testExecution) {
        final Test testDefinition = testExecution.getTestDefinition();
        List<Device> selectedDevices = getDevicesForTest(testDefinition);
        testExecution.setDevices(selectedDevices);
        testExecution.setSteps(new ArrayList<TestExecutionStep>());
        testExecution.setProductStates(new ArrayList<ProductState>());
        testExecution.setEquipments(new ArrayList<TesthasEquipment>());
        for (TestStep testStep : testDefinition.getTestStepCollection()) {
            if (ConditionHelper.hasBeenPassed(testStep, selectedDevices)) {
                TestExecutionStep executionStep = new TestExecutionStep();
                executionStep.setTestExecution(testExecution);
                executionStep.setTestStep(testStep);
                testExecution.getSteps().add(executionStep);
            }
        }

        for (ProductState productState : testDefinition.getProductStateCollection()) {
            if (ConditionHelper.hasBeenPassed(productState, selectedDevices)) {
                testExecution.getProductStates().add(productState);
            }
        }

        for (TesthasEquipment equipment : testDefinition.getTesthasEquipmentCollection()) {
            if (ConditionHelper.hasBeenPassed(equipment, selectedDevices)) {
                testExecution.getEquipments().add(equipment);
            }
        }

    }

    private List<Device> getDevicesForTest(Test testDefinition) {
        List<Device> selectedDevices = new ArrayList<Device>();
        for (GroupOfDevices deviceGroup : testDefinition.getDevicesGroups()) {
            selectedDevices.add(devicesGroupSelectedDevice.get(deviceGroup.getId()));
        }
        return selectedDevices;
    }

    public TestExecution[] getTestsToRemove() {
        return testsToRemove;
    }

    public void setTestsToRemove(TestExecution[] testsToRemove) {
        this.testsToRemove = testsToRemove;
    }

    public Test[] getTestsToAdd() {
        return testsToAdd;
    }

    public void setTestsToAdd(Test[] testsToAdd) {
        this.testsToAdd = testsToAdd;
    }

    public Test getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(Test selectedTest) {
        this.selectedTest = selectedTest;
    }

    public Collection<Test> getTestsAvailableToAdd() {
        return testsAvailableToAdd;
    }

    public void setTestsAvailableToAdd(List<Test> testsAvailableToAdd) {
        this.testsAvailableToAdd = testsAvailableToAdd;
    }

    public Collection<TestExecution> getAddedTests() {
        return getSelected().getTests();

    }

    public List<Test> getFilteredTestsAvailableToAdd() {
        return filteredTestsAvailableToAdd;
    }

    public void setFilteredTestsAvailableToAdd(List<Test> filteredTestsAvailableToAdd) {
        this.filteredTestsAvailableToAdd = filteredTestsAvailableToAdd;
    }

    public Map<Integer, Device> getDevicesGroupSelectedDevice() {
        return devicesGroupSelectedDevice;

    }

    public void setDevicesGroupSelectedDevice(Map<Integer, Device> devicesGroupSelectedDevice) {
        this.devicesGroupSelectedDevice = devicesGroupSelectedDevice;
    }

    public List<Users> getAvailableTeamMembers() {
        return availableTeamMembers;
    }

    public void setAvailableTeamMembers(List<Users> availableTeamMembers) {
        this.availableTeamMembers = availableTeamMembers;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
