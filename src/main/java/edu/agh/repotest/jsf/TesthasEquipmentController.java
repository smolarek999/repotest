package edu.agh.repotest.jsf;

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
        this.getSelected().getTesthasEquipmentPK().setTestidTest(this.getSelected().getTest().getIdTest());
        this.getSelected().getTesthasEquipmentPK().setEquipmentidEquipment(this.getSelected().getEquipment().getIdEquipment());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setTesthasEquipmentPK(new edu.agh.repotest.dao.TesthasEquipmentPK());
    }
}
