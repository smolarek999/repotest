package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.ProductState;
import edu.agh.repotest.session.ProductStateFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "productStateController")
@ViewScoped
public class ProductStateController extends AbstractController<ProductState> implements Serializable {

    @EJB
    private ProductStateFacade ejbFacade;

    public ProductStateController() {
        super(ProductState.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
