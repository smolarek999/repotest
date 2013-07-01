/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Authorities;
import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Device;
import edu.agh.repotest.dao.Equipment;
import edu.agh.repotest.dao.Feature;
import edu.agh.repotest.dao.Load;
import edu.agh.repotest.dao.Product;
import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.dao.Release;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.GroupOfDevices;
import edu.agh.repotest.dao.DeviceInGroupOfDevices;
import edu.agh.repotest.dao.TestGroup;
import edu.agh.repotest.dao.TestStep;
import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.dao.UserRole;
import edu.agh.repotest.dao.Users;
import edu.agh.repotest.session.AuthoritiesFacade;
import edu.agh.repotest.session.DeviceFacade;
import edu.agh.repotest.session.EquipmentFacade;
import edu.agh.repotest.session.FeatureFacade;
import edu.agh.repotest.session.ProductFacade;
import edu.agh.repotest.session.ProductStateFacade;
import edu.agh.repotest.session.ReleaseTableFacade;
import edu.agh.repotest.session.TestDeviceGroupFacade;
import edu.agh.repotest.session.TestDeviceGrouphasDeviceFacade;
import edu.agh.repotest.session.TestFacade;
import edu.agh.repotest.session.TestGroupFacade;
import edu.agh.repotest.session.TestStepFacade;
import edu.agh.repotest.session.TesthasEquipmentFacade;
import edu.agh.repotest.session.UsersFacade;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(eager = true)
@ApplicationScoped
public class PreloadController {

    @EJB
    DeviceFacade deviceFacade;
    @EJB
    UsersFacade usersFacade;
    @EJB
    AuthoritiesFacade authoritiesFacade;
    @EJB
    EquipmentFacade equipmentFacade;
    @EJB
    FeatureFacade featureFacade;
    @EJB
    ProductFacade productFacade;
    @EJB
    ReleaseTableFacade releaseFacade;
    @EJB
    TestGroupFacade testGroupFacade;
    @EJB
    TestFacade testFacade;
    @EJB
    TestDeviceGroupFacade testDeviceGroupFacade;
    @EJB
    TestDeviceGrouphasDeviceFacade deviceGrouphasDeviceFacade;
    @EJB
    ProductStateFacade productStateFacade;
    @EJB
    TesthasEquipmentFacade testhasEquipmentFacade;
    @EJB
    TestStepFacade testStepFacade;

    @PostConstruct
    public void doPreload() {
        boolean shouldBeFilled = false;
        Map map = testDeviceGroupFacade.getEmProperties();
        if (map.containsKey("eclipselink.ddl-generation")) {
            if ("drop-and-create-tables".equalsIgnoreCase(map.get("eclipselink.ddl-generation").toString())) {
                shouldBeFilled = true;
                System.out.println("doPreload:exacly this value" + map.get("eclipselink.ddl-generation"));
            }else{
                System.out.println("doPreload:different value" + map.get("eclipselink.ddl-generation"));
            }
        } else {
            System.out.println("doPreload:no such propoerty");
        }

        if (shouldBeFilled) {
            doPreloadInternal();
        } else {
            System.out.println("Preload skipped");
        }
    }

    private void doPreloadInternal() {

        Authorities adminRole = new Authorities();
        adminRole.setRole(UserRole.ADMIN);
        Authorities userRole = new Authorities();
        userRole.setRole(UserRole.TESTER);
        authoritiesFacade.create(userRole);
        authoritiesFacade.create(adminRole);
        Authorities[] adminAuthorities = {adminRole};
        Authorities[] userAuthorities = {userRole};

        Users admin = new Users();
        admin.setPassword("admin");
        admin.setUsername("admin");
        admin.setAuthoritiesCollection(Arrays.asList(adminAuthorities));
        usersFacade.create(admin);
        for (int i = 0; i < 10; ++i) {
            Users user = new Users();
            user.setPassword("user");
            user.setUsername("user" + i);
            user.setAuthoritiesCollection(Arrays.asList(userAuthorities));
            usersFacade.create(user);
        }
        List<Device> routersDevices = new ArrayList<Device>();
        List<Device> switchesDevices = new ArrayList<Device>();
        String[] routersNames = {"skaner1", "skaner2", "skaner3"};
        //String[] switchesNames = {"switch 1", "switch 2"};
        List<List<?>> devicesAndNames = new ArrayList<List<?>>();
        devicesAndNames.add(Arrays.asList(new Object[]{routersDevices, routersNames}));
        //devicesAndNames.add(Arrays.asList(new Object[]{switchesDevices, switchesNames}));
        for (List<?> deviceAndName : devicesAndNames) {
            for (String deviceName : (String[]) deviceAndName.get(1)) {
                Device d = new Device();
                d.setName(deviceName);
                deviceFacade.create(d);
                ((List<Device>) deviceAndName.get(0)).add(d);
            }
        }


        String[] equipmentNames = {"kabel dla producenta A", "kabel dla producenta B"};
        Equipment e = null;
        for (String equipmentName : equipmentNames) {
            e = new Equipment();
            e.setName(equipmentName);
            equipmentFacade.create(e);
        }

        Product kodex = new Product();
        kodex.setName("kodex");

        Product backadm = new Product();
        backadm.setName("backadm");

        Product statos = new Product();
        statos.setName("statos");

        productFacade.create(kodex);
        productFacade.create(backadm);
        productFacade.create(statos);


        String[] webTierFeatures = {"Skanowanie produktu", "Dodawanie produktu", "Statystyki dla produktu"};
        Feature f = null;
        for (String featureName : webTierFeatures) {
            f = new Feature();
            f.setName(featureName);
            f.setProduct(kodex);
            featureFacade.create(f);
        }

        Release release = new Release();
        release.setName("R1.0.0");
        release.setLoads(new LinkedList<Load>());

        String[] loadsNames = {"1.0", "1.1", "1.2", "2.0", "2.1", "2.2", "3.0", "3.1", "3.2"};
        for (String loadName : loadsNames) {
            Load load = new Load();
            load.setName(loadName);
            load.setRelease(release);
            release.getLoads().add(load);
        }
        releaseFacade.create(release);

        TestGroup funcGroup = new TestGroup();
        funcGroup.setName("Testy funkcjonalne");
        TestGroup.recalculatePath(funcGroup);
        testGroupFacade.create(funcGroup);
        
        TestGroup admGroup = new TestGroup();
        admGroup.setName("Moduł administracji");
        admGroup.setParentId(funcGroup);
        TestGroup.recalculatePath(admGroup);
        testGroupFacade.create(admGroup);
        
        TestGroup statGroup = new TestGroup();
        statGroup.setName("Moduł statystyczny");
        statGroup.setParentId(funcGroup);
        TestGroup.recalculatePath(statGroup);
        testGroupFacade.create(statGroup);
        
        
        TestGroup nonFuncGroup = new TestGroup();
        nonFuncGroup.setName("Testy niefunkcjonalne");
        TestGroup.recalculatePath(nonFuncGroup);
        testGroupFacade.create(nonFuncGroup);

        Test test = new Test();
        test.setDescription("some test");
        test.setName("some test");
        test.setTestGroupidTestGroup(funcGroup);
        test.setFeatureidFeature(f);
        test.setProducts(new LinkedList<Product>());
        test.getProducts().add(kodex);
        test.getProducts().add(backadm);
        testFacade.create(test);

        GroupOfDevices routers = new GroupOfDevices();
        routers.setName("Routers");
        routers.setTest(test);
        GroupOfDevices switches = new GroupOfDevices();
        switches.setName("Switches");
        switches.setTest(test);

        testDeviceGroupFacade.create(routers);
        testDeviceGroupFacade.create(switches);

        List<List<Object>> groupsAndDevices = new ArrayList<List<Object>>();
        groupsAndDevices.add(Arrays.asList(new Object[]{routers, routersDevices}));
        groupsAndDevices.add(Arrays.asList(new Object[]{switches, switchesDevices}));
        for (List<Object> groupAndDevices : groupsAndDevices) {
            for (Device device : (List<Device>) groupAndDevices.get(1)) {
                DeviceInGroupOfDevices tgDevice = new DeviceInGroupOfDevices();
                tgDevice.setDevice(device);
                tgDevice.setGroupOfDevices((GroupOfDevices) groupAndDevices.get(0));
                deviceGrouphasDeviceFacade.create(tgDevice);
            }
        }

        Condition onlyTwoFirstRouters = new Condition();
        onlyTwoFirstRouters.setDeviceGroup(routers);
        onlyTwoFirstRouters.setDevices(routersDevices.subList(0, 2));

        Condition thirdRouter = new Condition();
        thirdRouter.setDeviceGroup(routers);
        thirdRouter.setDevices(routersDevices.subList(2, 3));


        TesthasEquipment equipment = new TesthasEquipment();
        equipment.setEquipment(e);
        equipment.setRawCondition(onlyTwoFirstRouters);
        equipment.setQuantity(2);
        equipment.setTest(test);
        test.getTesthasEquipmentCollection();
        testhasEquipmentFacade.create(equipment);

        ProductState productState = new ProductState();
        productState.setProduct(kodex);
        productState.setDescription("description");
        productState.setTestidTest(test);
        productStateFacade.create(productState);


        TestStep testStepOneTwoRouter = new TestStep();
        testStepOneTwoRouter.setRawCondition(onlyTwoFirstRouters);
        testStepOneTwoRouter.setDescription("for router 1 and 2");
        testStepOneTwoRouter.setExpectedResult("should be ok");
        testStepOneTwoRouter.setTestidTest(test);

        TestStep testStepThirdRouter = new TestStep();
        testStepThirdRouter.setRawCondition(thirdRouter);
        testStepThirdRouter.setDescription("for router 3");
        testStepThirdRouter.setExpectedResult("should be ok");
        testStepThirdRouter.setTestidTest(test);

        TestStep testStepForAll = new TestStep();
        testStepForAll.setDescription("for ALL");
        testStepForAll.setExpectedResult("should be ok");
        testStepForAll.setTestidTest(test);

        testStepFacade.create(testStepOneTwoRouter);
        testStepFacade.create(testStepThirdRouter);
        testStepFacade.create(testStepForAll);
        


    }
}
