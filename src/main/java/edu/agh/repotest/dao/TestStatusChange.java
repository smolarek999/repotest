/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestStatusChanges")
@NamedQueries({@NamedQuery(name = "TestStatusChange.getOrederedByDate", query = "Select t FROM TestStatusChange t ORDER BY t.dateTime DESC "),
@NamedQuery(name = "TestStatusChange.getByTestExecution", query = "Select t FROM TestStatusChange t WHERE t.testExecution = :testExecution ORDER BY t.dateTime DESC ")})
public class TestStatusChange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "previousStatus")
    @Enumerated(EnumType.STRING)
    private TestStatus previousStatus;
    @Column(name = "currentStatus")
    @Enumerated(EnumType.STRING)
    private TestStatus currentStatus;
    @Column(name = "dateTime")
    private java.sql.Timestamp dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testExecutionId", referencedColumnName = "idTestExecution")
    private TestExecution testExecution;
    
    @Column(name = "changeFactor")
    private int changeFactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(TestStatus previousStatus) {
        this.previousStatus = previousStatus;
    }

    public TestStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(TestStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public TestExecution getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(TestExecution testExecution) {
        this.testExecution = testExecution;
    }

    public int getChangeFactor() {
        return changeFactor;
    }

    public void setChangeFactor(int changeFactor) {
        this.changeFactor = changeFactor;
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
        if (!(object instanceof TestStatusChange)) {
            return false;
        }
        TestStatusChange other = (TestStatusChange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestStatusChange[ id=" + id + " ]";
    }
}
