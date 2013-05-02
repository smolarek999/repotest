/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Feature")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feature.findAll", query = "SELECT f FROM Feature f")})
public class Feature implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFeature")
    private Integer idFeature;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "releaseSince")
    private String releaseSince;
    @Size(max = 45)
    @Column(name = "productId")
    private String productId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "featureidFeature")
    private Collection<Test> testCollection;

    public Feature() {
    }

    public Feature(Integer idFeature) {
        this.idFeature = idFeature;
    }

    public Integer getIdFeature() {
        return idFeature;
    }

    public void setIdFeature(Integer idFeature) {
        this.idFeature = idFeature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseSince() {
        return releaseSince;
    }

    public void setReleaseSince(String releaseSince) {
        this.releaseSince = releaseSince;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeature != null ? idFeature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feature)) {
            return false;
        }
        Feature other = (Feature) object;
        if ((this.idFeature == null && other.idFeature != null) || (this.idFeature != null && !this.idFeature.equals(other.idFeature))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Feature[ idFeature=" + idFeature + " ]";
    }
    
}
