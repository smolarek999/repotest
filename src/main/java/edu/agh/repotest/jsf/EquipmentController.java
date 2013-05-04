package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Equipment;
import edu.agh.repotest.session.EquipmentFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "equipmentController")
@ViewScoped
public class EquipmentController extends AbstractController<Equipment> implements Serializable {

    @EJB
    private EquipmentFacade ejbFacade;

    public EquipmentController() {
        super(Equipment.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
