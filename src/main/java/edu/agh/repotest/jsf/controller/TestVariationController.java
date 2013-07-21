/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.DevicePermutation;
import edu.agh.repotest.dao.EnityWithCondition;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecutionVariation;
import edu.agh.repotest.dao.TestExecutionVariationMap;
import edu.agh.repotest.jsf.util.JsfUtil;
import edu.agh.repotest.session.TestFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.BeforeCompletion;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author pawel
 */
@ManagedBean
@ViewScoped
public class TestVariationController {

    private MindmapNode root;
    private MindmapNode selectedNode;
    @EJB
    private TestFacade testFacade;
    private int idTest;
    private Test test;
    TestExecutionVariationMap map;
    TestExecutionVariation currentVariation;

    public void loadFromGetParameters() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            test = testFacade.find(idTest);
        }
    }

    private void computeVariations() {

        map = new TestExecutionVariationMap();
        final List<DevicePermutation> permutations = DevicePermutation.getPermutationForTest(test);
        List<EnityWithCondition> conditionableEntities = new ArrayList<EnityWithCondition>();
        conditionableEntities.addAll(test.getProductStateCollection());
        conditionableEntities.addAll(test.getTestStepCollection());
        conditionableEntities.addAll(test.getTesthasEquipmentCollection());
        for (DevicePermutation permutation : permutations) {
            final TestExecutionVariation variation = TestExecutionVariation.createForDevicePermutation(permutation, conditionableEntities);
            map.put(variation, permutation);
        }


        root = new DefaultMindmapNode(test.getName(), test, "FFCC00", false);
        for (TestExecutionVariation variation : map.getVariations()) {
            MindmapNode varNode = new DefaultMindmapNode(variation.getHash(), variation, "6e9ebf", true);
            root.addNode(varNode);
            for (DevicePermutation permutation : map.getDevicesPermuation(variation)) {
                MindmapNode permNode = new DefaultMindmapNode(permutation.toString(), permutation, "66FF66", false);
                varNode.addNode(permNode);
            }
        }



    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public TestExecutionVariationMap getMap() {
        if (map == null) {
            computeVariations();
        }
        return map;
    }

    public void setMap(TestExecutionVariationMap map) {
        this.map = map;
    }

    public MindmapNode getRoot() {
        getMap();
        return root;
    }

    public List<String> getVariationNames() {
        return getMap().getVariationHashes();
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();



    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
        if (selectedNode.getData() instanceof TestExecutionVariation) {
            currentVariation = (TestExecutionVariation) selectedNode.getData();
        }
    }

    public TestExecutionVariation getCurrentVariation() {
        return currentVariation;
    }
    
}
