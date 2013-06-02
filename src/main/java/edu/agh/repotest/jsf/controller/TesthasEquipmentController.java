package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.TesthasEquipment;
import edu.agh.repotest.session.TesthasEquipmentFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testhasEquipmentController")
@ViewScoped
public class TesthasEquipmentController extends AbstractController<TesthasEquipment> implements Serializable {

    @EJB
    private TesthasEquipmentFacade ejbFacade;

    public TesthasEquipmentController() {
        super(TesthasEquipment.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
    }

    @Override
    protected void initializeEmbeddableKey() {
    }
}
