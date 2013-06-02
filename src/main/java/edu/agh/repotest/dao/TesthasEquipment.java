/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    
    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "ConditionCol")
    private String condition;
    @Column(name = "Quantity")
    private int quantity;
    @JoinColumn(name = "Equipment_idEquipment", referencedColumnName = "idEquipment")
    @OneToOne(optional = false)
    private Equipment equipment;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test test;
    

    public TesthasEquipment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String getConditionInner(){
        return condition;
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
        System.out.println("getEquipment"+equipment);
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
         System.out.println("setEquipment");
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesthasEquipment)) {
            return false;
        }
        TesthasEquipment other = (TesthasEquipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TesthasEquipment[ testhasEquipmentPK=" + id + " ]";
    }
}
