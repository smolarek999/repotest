/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestExecution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestExecution.findAll", query = "SELECT t FROM TestExecution t")})
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
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @JoinColumn(name = "Users_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users usersid;
    @JoinColumn(name = "Load_Release_idRelease", referencedColumnName = "Release_idRelease")
    @ManyToOne(optional = false)
    private Load loadReleaseidRelease;
    @JoinColumn(name = "Test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testidTest;
    @JoinColumn(name = "StrategyExecution_idStrategyExecution", referencedColumnName = "idStrategyExecution")
    @ManyToOne(optional = false)
    private StrategyExecution strategyExecutionidStrategyExecution;
    @JoinColumn(name = "Mode_idMode", referencedColumnName = "idMode")
    @ManyToOne(optional = false)
    private Mode modeidMode;
    @JoinColumn(name = "Device_idDevice", referencedColumnName = "idDevice")
    @ManyToOne(optional = false)
    private Device deviceidDevice;

    public TestExecution() {
    }

    public TestExecution(Integer idTestExecution) {
        this.idTestExecution = idTestExecution;
    }

    public Integer getIdTestExecution() {
        return idTestExecution;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Users getUsersid() {
        return usersid;
    }

    public void setUsersid(Users usersid) {
        this.usersid = usersid;
    }

    public Load getLoadReleaseidRelease() {
        return loadReleaseidRelease;
    }

    public void setLoadReleaseidRelease(Load loadReleaseidRelease) {
        this.loadReleaseidRelease = loadReleaseidRelease;
    }

    public Test getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(Test testidTest) {
        this.testidTest = testidTest;
    }

    public StrategyExecution getStrategyExecutionidStrategyExecution() {
        return strategyExecutionidStrategyExecution;
    }

    public void setStrategyExecutionidStrategyExecution(StrategyExecution strategyExecutionidStrategyExecution) {
        this.strategyExecutionidStrategyExecution = strategyExecutionidStrategyExecution;
    }

    public Mode getModeidMode() {
        return modeidMode;
    }

    public void setModeidMode(Mode modeidMode) {
        this.modeidMode = modeidMode;
    }

    public Device getDeviceidDevice() {
        return deviceidDevice;
    }

    public void setDeviceidDevice(Device deviceidDevice) {
        this.deviceidDevice = deviceidDevice;
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
        return "edu.agh.repotest.domain.TestExecution[ idTestExecution=" + idTestExecution + " ]";
    }
    
}
