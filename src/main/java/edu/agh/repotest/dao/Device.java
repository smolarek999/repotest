/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    private Collection<TestExecutionhasDevice> testExecutionhasDeviceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceidDevice")
    private Collection<TestExecution> testExecutionCollection;
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "device")
    //private Collection<TestDeviceGroupDevices> testDeviceGroupDevices;

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
    public Collection<TestExecutionhasDevice> getTestExecutionhasDeviceCollection() {
        return testExecutionhasDeviceCollection;
    }

    public void setTestExecutionhasDeviceCollection(Collection<TestExecutionhasDevice> testExecutionhasDeviceCollection) {
        this.testExecutionhasDeviceCollection = testExecutionhasDeviceCollection;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }
/*
    @XmlTransient
    public Collection<TestDeviceGroupDevices> getTestDeviceGrouphasDeviceCollection() {
        return testDeviceGroupDevices;
    }

    public void setTestDeviceGrouphasDeviceCollection(Collection<TestDeviceGroupDevices> testDeviceGrouphasDeviceCollection) {
        this.testDeviceGroupDevices = testDeviceGrouphasDeviceCollection;
    }
*/
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
        return "edu.agh.repotest.dao.Device[ idDevice=" + idDevice + " ]";
    }
    
}
