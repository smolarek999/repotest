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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Equipment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e")})
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEquipment")
    private Integer idEquipment;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "equipmentCollection")
    private Collection<TestExecution> testExecutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment")
    private Collection<TesthasEquipment> testhasEquipmentCollection;

    public Equipment() {
    }

    public Equipment(Integer idEquipment) {
        this.idEquipment = idEquipment;
    }

    public Integer getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(Integer idEquipment) {
        this.idEquipment = idEquipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    @XmlTransient
    public Collection<TesthasEquipment> getTesthasEquipmentCollection() {
        return testhasEquipmentCollection;
    }

    public void setTesthasEquipmentCollection(Collection<TesthasEquipment> testhasEquipmentCollection) {
        this.testhasEquipmentCollection = testhasEquipmentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipment != null ? idEquipment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.idEquipment == null && other.idEquipment != null) || (this.idEquipment != null && !this.idEquipment.equals(other.idEquipment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.Equipment[ idEquipment=" + idEquipment + " ]";
    }
    
}
