package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Feature;
import edu.agh.repotest.session.FeatureFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "featureController")
@ViewScoped
public class FeatureController extends AbstractController<Feature> implements Serializable {

    @EJB
    private FeatureFacade ejbFacade;

    public FeatureController() {
        super(Feature.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
