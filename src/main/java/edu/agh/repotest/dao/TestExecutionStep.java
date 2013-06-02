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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "TestExecution_has_TestStep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestExecutionStep.findAll", query = "SELECT t FROM TestExecutionStep t")})
public class TestExecutionStep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 45)
    @Column(name = "Comment")
    private String comment;
    @JoinColumn(name = "TestStep_idTestStep", referencedColumnName = "idTestStep")
    @ManyToOne(optional = false)
    private TestStep testStep;
    @JoinColumn(name = "TestExecution_idTestExecution", referencedColumnName = "idTestExecution")
    @ManyToOne(optional = false)
    private TestExecution testExecution;

    public TestExecutionStep() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public TestStep getTestStep() {
        return testStep;
    }

    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    public TestExecution getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(TestExecution testExecution) {
        this.testExecution = testExecution;
    }

    public String getDescription() {
        return testStep.getDescription();
    }

    public String getExpectedResult() {
        return testStep.getExpectedResult();
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
        if (!(object instanceof TestExecutionStep)) {
            return false;
        }
        TestExecutionStep other = (TestExecutionStep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestExecutionhasTestStep[ testExecutionhasTestStepPK=" + id + " ]";
    }
}
