package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestGroup;
import edu.agh.repotest.session.TestFacade;
import edu.agh.repotest.session.TestGroupFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "testController")
@ViewScoped
public class TestController extends AbstractController<Test> implements Serializable {

    @EJB
    private TestFacade ejbFacade;
    @EJB
    private TestGroupFacade ejbGroupFacade;
    @ManagedProperty("#{testDeviceGroupController}")
    private TestDeviceGroupController deviceGroupController;
    private Integer parentId;

    public TestController() {
        super(Test.class);
    }

 

    public void prepare() {
        if( getSelected() == null || getSelected().getTestGroupidTestGroup()  == null ){
            System.err.println("Prepare");
        prepareCreate(null);
        if (parentId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        TestGroup tg = ejbGroupFacade.find(parentId);

        if (tg == null) {
            String message = "Bad request. Unknown user.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        } else {
            getSelected().setTestGroupidTestGroup(tg);
            getDeviceGroupController().prepareCreate(null);
        }

        }else{
            System.err.println("Existed before");
        }
        
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);

    }

    /**
     * @return the deviceGroupController
     */
    public TestDeviceGroupController getDeviceGroupController() {
        return deviceGroupController;
    }

    /**
     * @param deviceGroupController the deviceGroupController to set
     */
    public void setDeviceGroupController(TestDeviceGroupController deviceGroupController) {
        this.deviceGroupController = deviceGroupController;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
}
