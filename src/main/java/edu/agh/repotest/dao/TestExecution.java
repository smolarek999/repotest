/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestExecution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestExecution.findAll", query = "SELECT t FROM TestExecution t"),
    @NamedQuery(name = "TestExecution.findByTestPlanUserAndStatus", query = "SELECT t FROM TestExecution t JOIN t.testPlan.team as user WHERE t.status = :status AND user = :user"),
    @NamedQuery(name = "TestExecution.findByUserAndStatus", query = "SELECT t FROM TestExecution t  WHERE t.status = :status AND t.tester = :user")})
public class TestExecution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestExecution")
    private Integer idTestExecution;
    @Size(max = 45)
    @Column(name = "comments")
    private String comments;
    @Column(name = "lastModificationDate")
    @Temporal(TemporalType.DATE)
    private Date lastModificationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "testStatus")
    private TestStatus status;
    @JoinTable(name = "TestExecution_has_ProductState", joinColumns = {
        @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution")}, inverseJoinColumns = {
        @JoinColumn(name = "ProductState_Product_idProduct", referencedColumnName = "Product_idProduct")})
    @ManyToMany
    private Collection<ProductState> productStates;
    @JoinTable(name = "TestExecution_has_Equipment", joinColumns = {
        @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution")}, inverseJoinColumns = {
        @JoinColumn(name = "Equipment_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<TesthasEquipment> equipments;
    @JoinColumn(name = "Users_id", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users tester;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testDefinition;
    @ManyToMany
    @JoinTable(name = "TestExecution_has_Device", joinColumns =
            @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution"), inverseJoinColumns =
            @JoinColumn(name = "Device_idDevice", referencedColumnName = "idDevice"))
    private Collection<Device> devices;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testExecution")
    private Collection<TestExecutionStep> steps;
    
    @ManyToOne
    @JoinColumn(name = "TestPlan_idTestPlan", referencedColumnName = "idTestPlan")
    private TestPlan testPlan;

    public TestExecution() {
    }

    public TestExecution(Integer idTestExecution) {
        this.idTestExecution = idTestExecution;
    }

    public Integer getIdTestExecution() {
        return idTestExecution;
    }

    public Collection<Device> getDevices() {
        return devices;
    }

    public void setDevices(Collection<Device> devices) {
        this.devices = devices;
    }

    public void setIdTestExecution(Integer idTestExecution) {
        this.idTestExecution = idTestExecution;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    @XmlTransient
    public Collection<ProductState> getProductStates() {
        return productStates;
    }

    public void setProductStates(Collection<ProductState> productStateCollection) {
        this.productStates = productStateCollection;
    }

    @XmlTransient
    public Collection<TesthasEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Collection<TesthasEquipment> equipmentCollection) {
        this.equipments = equipmentCollection;
    }

    public Users getTester() {
        return tester;
    }

    public void setTester(Users usersid) {
        this.tester = usersid;
    }

    public Test getTestDefinition() {
        return testDefinition;
    }

    public void setTestDefinition(Test testidTest) {
        this.testDefinition = testidTest;
    }

    @XmlTransient
    public Collection<TestExecutionStep> getSteps() {
        return steps;
    }

    public void setSteps(Collection<TestExecutionStep> testExecutionhasTestStepCollection) {
        this.steps = testExecutionhasTestStepCollection;
    }

    @Transient
    public String getName() {
        return testDefinition.getName();
    }

    @Transient
    public Feature getFeatureidFeature() {
        return testDefinition.getFeatureidFeature();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestExecution != null ? idTestExecution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestExecution)) {
            return false;
        }
        TestExecution other = (TestExecution) object;
        if ((this.idTestExecution == null && other.idTestExecution != null) || (this.idTestExecution != null && !this.idTestExecution.equals(other.idTestExecution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecution[ idTestExecution=" + idTestExecution + " ]";
    }
}
