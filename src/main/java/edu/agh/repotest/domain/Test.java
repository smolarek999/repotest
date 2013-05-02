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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "Test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")})
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTest")
    private Integer idTest;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "Group_path")
    private String grouppath;
    @ManyToMany(mappedBy = "testCollection")
    private Collection<Mode> modeCollection;
    @ManyToMany(mappedBy = "testCollection")
    private Collection<Device> deviceCollection;
    @ManyToMany(mappedBy = "testCollection")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testidTest")
    private Collection<TestExecution> testExecutionCollection;
    @JoinColumn(name = "TestGroup_idTestGroup", referencedColumnName = "idTestGroup")
    @ManyToOne(optional = false)
    private TestGroup testGroupidTestGroup;
    @JoinColumn(name = "Feature_idFeature", referencedColumnName = "idFeature")
    @ManyToOne(optional = false)
    private Feature featureidFeature;

    public Test() {
    }

    public Test(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
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

    public String getGrouppath() {
        return grouppath;
    }

    public void setGrouppath(String grouppath) {
        this.grouppath = grouppath;
    }

    @XmlTransient
    public Collection<Mode> getModeCollection() {
        return modeCollection;
    }

    public void setModeCollection(Collection<Mode> modeCollection) {
        this.modeCollection = modeCollection;
    }

    @XmlTransient
    public Collection<Device> getDeviceCollection() {
        return deviceCollection;
    }

    public void setDeviceCollection(Collection<Device> deviceCollection) {
        this.deviceCollection = deviceCollection;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    public TestGroup getTestGroupidTestGroup() {
        return testGroupidTestGroup;
    }

    public void setTestGroupidTestGroup(TestGroup testGroupidTestGroup) {
        this.testGroupidTestGroup = testGroupidTestGroup;
    }

    public Feature getFeatureidFeature() {
        return featureidFeature;
    }

    public void setFeatureidFeature(Feature featureidFeature) {
        this.featureidFeature = featureidFeature;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Test[ idTest=" + idTest + " ]";
    }
    
}
