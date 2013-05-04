/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pawel
 */
@Embeddable
public class TesthasEquipmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Test_idTest")
    private int testidTest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Equipment_idEquipment")
    private int equipmentidEquipment;

    public TesthasEquipmentPK() {
    }

    public TesthasEquipmentPK(int testidTest, int equipmentidEquipment) {
        this.testidTest = testidTest;
        this.equipmentidEquipment = equipmentidEquipment;
    }

    public int getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(int testidTest) {
        this.testidTest = testidTest;
    }

    public int getEquipmentidEquipment() {
        return equipmentidEquipment;
    }

    public void setEquipmentidEquipment(int equipmentidEquipment) {
        this.equipmentidEquipment = equipmentidEquipment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) testidTest;
        hash += (int) equipmentidEquipment;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesthasEquipmentPK)) {
            return false;
        }
        TesthasEquipmentPK other = (TesthasEquipmentPK) object;
        if (this.testidTest != other.testidTest) {
            return false;
        }
        if (this.equipmentidEquipment != other.equipmentidEquipment) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TesthasEquipmentPK[ testidTest=" + testidTest + ", equipmentidEquipment=" + equipmentidEquipment + " ]";
    }
    
}
