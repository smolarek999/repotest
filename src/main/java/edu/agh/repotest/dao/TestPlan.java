/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "TestPlan")
@NamedQueries({
    @NamedQuery(name = "TestPlan.findAll", query = "SELECT t FROM TestPlan t"),
    @NamedQuery(name = "TestPlan.findByState", query = "SELECT t FROM TestPlan t WHERE t.planState = :state"),
    @NamedQuery(name = "TestPlan.findByUser", query = "SELECT t FROM TestPlan t JOIN t.team as user WHERE user = :user ")})
public class TestPlan implements Serializable {

    public enum State {

        Open,
        Closed
    }
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
    @Column(name = "creationDate")
    private java.sql.Timestamp creationDate;
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
   
    @ManyToOne
    @JoinColumn(name = "Load_Table_idLoad", referencedColumnName = "idLoad")
    private Load load;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testPlan")
    private Collection<TestExecution> tests;
    @Column(name="currentState")
    @Enumerated(EnumType.STRING)
    State planState = State.Open;

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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

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

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestPlan[ idTestPlan=" + id + " ]";
    }
}
