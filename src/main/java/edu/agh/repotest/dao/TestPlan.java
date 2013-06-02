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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "TestPlan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestPlan.findAll", query = "SELECT t FROM TestPlan t"),
    @NamedQuery(name = "TestPlan.findByUser", query = "SELECT t FROM TestPlan t JOIN t.team as user WHERE user = :user ")})
public class TestPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestPlan")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "creationDate")
    private String creationDate;
    @JoinTable(name = "TestPlan_has_Feature", joinColumns = {
        @JoinColumn(name = "TestPlan_idTestPlan", referencedColumnName = "idTestPlan")}, inverseJoinColumns = {
        @JoinColumn(name = "Feature_idFeature", referencedColumnName = "idFeature")})
    @ManyToMany
    private Collection<Feature> features;
    @ManyToMany
    @JoinTable(name = "Users_has_TestPlan", joinColumns =
            @JoinColumn(name = "TestPlan_idTestPlan", referencedColumnName = "idTestPlan"), inverseJoinColumns =
            @JoinColumn(name = "Users_id", referencedColumnName = "userId"))
    private Collection<Users> team;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testPlan")
    private Collection<TestExecution> tests;
    @Enumerated(EnumType.STRING)
    State planState;

    public TestPlan() {
    }

    public TestPlan(Integer idTestPlan) {
        this.id = idTestPlan;

    }

    public Integer getIdTestPlan() {
        return id;
    }

    public void setIdTestPlan(Integer idTestPlan) {
        this.id = idTestPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Collection<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Collection<Feature> featureCollection) {
        this.features = featureCollection;
    }

    public Collection<Users> getTeam() {
        return team;
    }

    public void setTeam(Collection<Users> team) {
        this.team = team;
    }

    public Collection<TestExecution> getTests() {
        return tests;
    }

    public void setTests(Collection<TestExecution> tests) {
        this.tests = tests;
    }

    public State getPlanState() {
        return planState;
    }

    public void setPlanState(State planState) {
        this.planState = planState;
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
        if (!(object instanceof TestPlan)) {
            return false;
        }
        TestPlan other = (TestPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public enum State {

        Open,
        Closed
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestPlan[ idTestPlan=" + id + " ]";
    }
}
