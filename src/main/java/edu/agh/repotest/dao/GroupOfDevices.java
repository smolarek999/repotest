/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestDeviceGroup")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "GroupOfDevices.findAll", query = "SELECT t FROM GroupOfDevices t")})
public class GroupOfDevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupOfDevices")
    private Collection<DeviceInGroupOfDevices> devices;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest", insertable = true, updatable = true)
    @ManyToOne(optional = false)
    @XmlTransient
    private Test test;
    @JoinTable(name = "TestDeviceGroup_has_Device", joinColumns = {
        @JoinColumn(name = "TestDeviceGroup_idTestDeviceGroup", referencedColumnName = "idTestDeviceGroup")}, inverseJoinColumns = {
        @JoinColumn(name = "Device_idDevice", referencedColumnName = "idDevice")})
    @ManyToMany
    @XmlTransient
    private Collection<Device> rawDevices;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idTestDeviceGroup;

    public GroupOfDevices() {
        devices = new ArrayList<DeviceInGroupOfDevices>();
    }

    public GroupOfDevices(Integer id) {
        this();
        this.idTestDeviceGroup = id;
    }

    public Integer getId() {
        return idTestDeviceGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String testDeviceGroupcol) {
        this.name = testDeviceGroupcol;
    }

    @XmlTransient
    public Collection<DeviceInGroupOfDevices> getDevices() {
        return devices;
    }

    public void setDevices(Collection<DeviceInGroupOfDevices> testDeviceGrouphasDeviceCollection) {
        this.devices = testDeviceGrouphasDeviceCollection;
        updateRawDevices();
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setRawDevices(Collection<Device> rawDevices) {
        this.rawDevices = rawDevices;
    }

    private void updateRawDevices() {
        List<Device> rawDevices = new LinkedList<Device>();
        for (DeviceInGroupOfDevices testDeviceGroupDevices : devices) {
            rawDevices.add(testDeviceGroupDevices.getDevice());

        }
        setRawDevices(rawDevices);
    }

    public Collection<Device> getRawDevices() {
        if (rawDevices.size() != devices.size()) {
            updateRawDevices();
        }
        return rawDevices;

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestDeviceGroup != null ? idTestDeviceGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupOfDevices)) {
            return false;
        }
        GroupOfDevices other = (GroupOfDevices) object;
        if ((this.idTestDeviceGroup == null && other.idTestDeviceGroup != null) || (this.idTestDeviceGroup != null && !this.idTestDeviceGroup.equals(other.idTestDeviceGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGroup[ testDeviceGroupPK=" + idTestDeviceGroup + " ]";
    }
}
