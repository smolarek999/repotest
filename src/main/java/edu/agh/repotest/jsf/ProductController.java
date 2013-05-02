package edu.agh.repotest.jsf;

import edu.agh.repotest.domain.Product;
import edu.agh.repotest.dao.ProductFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "productController")
@ViewScoped
public class ProductController extends AbstractController<Product> implements Serializable {

    @EJB
    private ProductFacade ejbFacade;

    public ProductController() {
        super(Product.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
}
