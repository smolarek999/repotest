/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "ProductState")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductState.findAll", query = "SELECT p FROM ProductState p")})
public class ProductState implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Product_idProduct")
    private Integer productidProduct;
    @Size(max = 45)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "ProductStatecol")
    private String condition;
    @ManyToMany(mappedBy = "productStateCollection")
    private Collection<TestExecution> testExecutionCollection;
    @JoinColumn(name = "Product_idProduct", referencedColumnName = "idProduct", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testidTest;

    public ProductState() {
    }

    public ProductState(Integer productidProduct) {
        this.productidProduct = productidProduct;
    }

    public Integer getProductidProduct() {
        return productidProduct;
    }

    public void setProductidProduct(Integer productidProduct) {
        this.productidProduct = productidProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Condition>  getRawCondition() {
        return Condition.createFromString(condition);
    }

    public void setRawCondition(List<Condition> condition) {
        this.condition = Condition.createToString(condition);
    }
    
    
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Test getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(Test testidTest) {
        this.testidTest = testidTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productidProduct != null ? productidProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductState)) {
            return false;
        }
        ProductState other = (ProductState) object;
        if ((this.productidProduct == null && other.productidProduct != null) || (this.productidProduct != null && !this.productidProduct.equals(other.productidProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.ProductState[ productidProduct=" + productidProduct + " ]";
    }
    
}
