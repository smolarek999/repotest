/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestDeviceGroup_has_Device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeviceInGroupOfDevices.findAll", query = "SELECT t FROM DeviceInGroupOfDevices t")})
public class DeviceInGroupOfDevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    Integer id;
    
    
    @Column(name = "ExecutionCount")
    private Integer executionCount;
    
    
    @Id
    @JoinColumn(name = "Device_idDevice")
    /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
     *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
     *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
     * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
     */
    private Device device;
    @Id
    @ManyToOne
    @JoinColumn(name = "TestDeviceGroup_idTestDeviceGroup")
    /* the same goes here:
     *  if this JPA model doesn't create a table for the "PROJ_EMP" entity,
     *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
     *  @JoinColumn(name = "projectId", updatable = false, insertable = false)
     * or @JoinColumn(name = "projectId", updatable = false, insertable = false, referencedColumnName = "id")
     */
    private GroupOfDevices groupOfDevices;

    public String getName() {
        return device.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeviceInGroupOfDevices() {
    }

    public GroupOfDevices getGroupOfDevices() {
        return groupOfDevices;
    }

    public void setGroupOfDevices(GroupOfDevices groupOfDevices) {
        this.groupOfDevices = groupOfDevices;
    }

    public Integer getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(Integer executionCount) {
        this.executionCount = executionCount;
    }

    public Device getDevice() {
        return device;
    }

    public GroupOfDevices getTestDeviceGroup() {
        return groupOfDevices;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceInGroupOfDevices)) {
            return false;
        }
        DeviceInGroupOfDevices other = (DeviceInGroupOfDevices) object;
        if (this.getId() == null && other.getId() != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevice[ testDeviceGrouphasDevicePK=" + getDevice() + " ]" + "deviceGroup=" + getGroupOfDevices();
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    public void setTestDeviceGroup(GroupOfDevices deviceGroup) {
        this.groupOfDevices = deviceGroup;

    }
}
