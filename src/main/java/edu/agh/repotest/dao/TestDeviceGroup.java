/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "TestDeviceGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestDeviceGroup.findAll", query = "SELECT t FROM TestDeviceGroup t")})
public class TestDeviceGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceGroups")
    private Collection<TestDeviceGroupDevices> devices;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    private Test test;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTestDeviceGroup;

    public TestDeviceGroup() {
        devices = new ArrayList<TestDeviceGroupDevices>();
    }

    

    public Integer getId(){
        return idTestDeviceGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String testDeviceGroupcol) {
        this.name = testDeviceGroupcol;
    }

    @XmlTransient
    public Collection<TestDeviceGroupDevices> getDevices() {
        return devices;
    }

    public void setDevices(Collection<TestDeviceGroupDevices> testDeviceGrouphasDeviceCollection) {
        this.devices = testDeviceGrouphasDeviceCollection;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestDeviceGroup != null ? idTestDeviceGroup.hashCode() : 0);
        name += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroup)) {
            return false;
        }
        TestDeviceGroup other = (TestDeviceGroup) object;
        if ((this.idTestDeviceGroup == null && other.idTestDeviceGroup != null) || (this.idTestDeviceGroup != null && !this.idTestDeviceGroup.equals(other.idTestDeviceGroup))) {
            return false;
        }
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGroup[ testDeviceGroupPK=" + idTestDeviceGroup + " ]";
    }
    
}
