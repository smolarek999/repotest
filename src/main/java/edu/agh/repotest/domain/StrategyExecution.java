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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "StrategyExecution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StrategyExecution.findAll", query = "SELECT s FROM StrategyExecution s")})
public class StrategyExecution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStrategyExecution")
    private Integer idStrategyExecution;
    @Size(max = 45)
    @Column(name = "StrategyExecutioncol")
    private String strategyExecutioncol;
    @ManyToMany(mappedBy = "strategyExecutionCollection")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strategyExecutionidStrategyExecution")
    private Collection<TestExecution> testExecutionCollection;
    @JoinColumn(name = "Strategy_idStrategy", referencedColumnName = "idStrategy")
    @ManyToOne(optional = false)
    private Strategy strategyidStrategy;

    public StrategyExecution() {
    }

    public StrategyExecution(Integer idStrategyExecution) {
        this.idStrategyExecution = idStrategyExecution;
    }

    public Integer getIdStrategyExecution() {
        return idStrategyExecution;
    }

    public void setIdStrategyExecution(Integer idStrategyExecution) {
        this.idStrategyExecution = idStrategyExecution;
    }

    public String getStrategyExecutioncol() {
        return strategyExecutioncol;
    }

    public void setStrategyExecutioncol(String strategyExecutioncol) {
        this.strategyExecutioncol = strategyExecutioncol;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<TestExecution> getTestExecutionCollection() {
        return testExecutionCollection;
    }

    public void setTestExecutionCollection(Collection<TestExecution> testExecutionCollection) {
        this.testExecutionCollection = testExecutionCollection;
    }

    public Strategy getStrategyidStrategy() {
        return strategyidStrategy;
    }

    public void setStrategyidStrategy(Strategy strategyidStrategy) {
        this.strategyidStrategy = strategyidStrategy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStrategyExecution != null ? idStrategyExecution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StrategyExecution)) {
            return false;
        }
        StrategyExecution other = (StrategyExecution) object;
        if ((this.idStrategyExecution == null && other.idStrategyExecution != null) || (this.idStrategyExecution != null && !this.idStrategyExecution.equals(other.idStrategyExecution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.StrategyExecution[ idStrategyExecution=" + idStrategyExecution + " ]";
    }
    
}
