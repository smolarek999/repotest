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
@Table(name = "Mode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mode.findAll", query = "SELECT m FROM Mode m")})
public class Mode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMode")
    private Integer idMode;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "Test_has_Mode", joinColumns = {
        @JoinColumn(name = "Mode_idMode", referencedColumnName = "idMode")}, inverseJoinColumns = {
        @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")})
    @ManyToMany
    private Collection<Test> testCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeidMode")
    private Collection<TestExecution> testExecutionCollection;

    public Mode() {
    }

    public Mode(Integer idMode) {
        this.idMode = idMode;
    }

    public Integer getIdMode() {
        return idMode;
    }

    public void setIdMode(Integer idMode) {
        this.idMode = idMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idMode != null ? idMode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mode)) {
            return false;
        }
        Mode other = (Mode) object;
        if ((this.idMode == null && other.idMode != null) || (this.idMode != null && !this.idMode.equals(other.idMode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Mode[ idMode=" + idMode + " ]";
    }
    
}
