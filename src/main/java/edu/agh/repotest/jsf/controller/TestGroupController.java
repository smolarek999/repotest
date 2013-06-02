package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.TestGroup;
import edu.agh.repotest.session.TestGroupFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "testGroupController")
@ViewScoped
public class TestGroupController extends AbstractController<TestGroup> implements Serializable {

    @EJB
    private TestGroupFacade ejbFacade;

    public TestGroupController() {
        super(TestGroup.class);
    }

    
    public void nodeListener(ActionEvent event){
        prepareCreate(event);
        Integer rawId =  Integer.valueOf( event.getComponent().getAttributes().get("rawParentId").toString());
        getSelected().setParentId( ejbFacade.find(rawId));
    }
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }


    
    @Override
    public void saveNew(ActionEvent event) {
        TestGroup selected = getSelected();
        TestGroup.recalculatePath(selected);
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }
    
}
