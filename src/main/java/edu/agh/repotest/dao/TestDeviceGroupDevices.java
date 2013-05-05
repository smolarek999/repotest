/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(TestDeviceGroupId.class)
@NamedQueries({
    @NamedQuery(name = "TestDeviceGroupDevices.findAll", query = "SELECT t FROM TestDeviceGroupDevices t")})

public class TestDeviceGroupDevices implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "ExecutionCount")
    private String executionCount;
    
    
  @Id
  private long Device_idDevice;
  @Id
  private long TestDeviceGroup_idTestDeviceGroup;

  @PrimaryKeyJoinColumn(name="Device_idDevice", referencedColumnName = "idDevice")
  /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
  *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
  * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
  */
  private Device device;
  @ManyToOne
  @PrimaryKeyJoinColumn(name="TestDeviceGroup_idTestDeviceGroup", referencedColumnName = "idTestDeviceGroup")
  /* the same goes here:
  *  if this JPA model doesn't create a table for the "PROJ_EMP" entity,
  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
  *  @JoinColumn(name = "projectId", updatable = false, insertable = false)
  * or @JoinColumn(name = "projectId", updatable = false, insertable = false, referencedColumnName = "id")
  */
  private TestDeviceGroup deviceGroups;
    
  
  public String getName(){
      return device.getName();
  }
   
    public TestDeviceGroupDevices() {
    }





    public String getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(String executionCount) {
        this.executionCount = executionCount;
    }

    public Device getDevice() {
        return device;
    }
    


    public TestDeviceGroup getTestDeviceGroup() {
        return deviceGroups;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getDevice() != null ? getDevice().hashCode() : 0);
        hash += (getTestDeviceGroup() != null ? getTestDeviceGroup().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupDevices)) {
            return false;
        }
        TestDeviceGroupDevices other = (TestDeviceGroupDevices) object;
        if ((this.getDevice() == null && other.getDevice() != null) || (this.getDevice() != null && !this.getDevice().equals(other.getDevice()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGrouphasDevice[ testDeviceGrouphasDevicePK=" + getDevice() + " ]";
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
        Device_idDevice = device.getIdDevice();
    }
    public void setTestDeviceGroup(TestDeviceGroup deviceGroup){
        this.deviceGroups = deviceGroup;
        TestDeviceGroup_idTestDeviceGroup = deviceGroup.getId();
        
    }
    
}
