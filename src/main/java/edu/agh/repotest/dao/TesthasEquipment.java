/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Test_has_Equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TesthasEquipment.findAll", query = "SELECT t FROM TesthasEquipment t")})
public class TesthasEquipment extends EnityWithCondition {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TesthasEquipmentPK testhasEquipmentPK;
    @Size(max = 45)
    @Column(name = "Condition")
    private String condition;
    @Column(name = "Quantity")
    private int quantity;
    @JoinColumn(name = "Equipment_idEquipment", referencedColumnName = "idEquipment", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Equipment equipment;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Test test;

    public TesthasEquipment() {
    }

    public TesthasEquipment(TesthasEquipmentPK testhasEquipmentPK) {
        this.testhasEquipmentPK = testhasEquipmentPK;
    }

    public TesthasEquipment(int testidTest, int equipmentidEquipment) {
        this.testhasEquipmentPK = new TesthasEquipmentPK(testidTest, equipmentidEquipment);
    }

    public TesthasEquipmentPK getTesthasEquipmentPK() {
        return testhasEquipmentPK;
    }

    public void setTesthasEquipmentPK(TesthasEquipmentPK testhasEquipmentPK) {
        this.testhasEquipmentPK = testhasEquipmentPK;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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
        hash += (testhasEquipmentPK != null ? testhasEquipmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesthasEquipment)) {
            return false;
        }
        TesthasEquipment other = (TesthasEquipment) object;
        if ((this.testhasEquipmentPK == null && other.testhasEquipmentPK != null) || (this.testhasEquipmentPK != null && !this.testhasEquipmentPK.equals(other.testhasEquipmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TesthasEquipment[ testhasEquipmentPK=" + testhasEquipmentPK + " ]";
    }
}
