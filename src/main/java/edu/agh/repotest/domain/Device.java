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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "Device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")})
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDevice")
    private Integer idDevice;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "manualUrl")
    private String manualUrl;
    @JoinTable(name = "Test_has_Device", joinColumns = {
        @JoinColumn(name = "Device_idDevice", referencedColumnName = "idDevice")}, inverseJoinColumns = {
        @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")})
    @ManyToMany
    private Collection<Test> testCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceidDevice")
    private Collection<TestExecution> testExecutionCollection;

    public Device() {
    }

    public Device(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManualUrl() {
        return manualUrl;
    }

    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevice != null ? idDevice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.idDevice == null && other.idDevice != null) || (this.idDevice != null && !this.idDevice.equals(other.idDevice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Device[ idDevice=" + idDevice + " ]";
    }
    
}
