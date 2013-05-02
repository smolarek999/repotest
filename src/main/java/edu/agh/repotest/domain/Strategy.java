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
@Table(name = "Strategy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Strategy.findAll", query = "SELECT s FROM Strategy s")})
public class Strategy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStrategy")
    private Integer idStrategy;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Size(max = 45)
    @Column(name = "creationDate")
    private String creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strategyidStrategy")
    private Collection<StrategyExecution> strategyExecutionCollection;

    public Strategy() {
    }

    public Strategy(Integer idStrategy) {
        this.idStrategy = idStrategy;
    }

    public Integer getIdStrategy() {
        return idStrategy;
    }

    public void setIdStrategy(Integer idStrategy) {
        this.idStrategy = idStrategy;
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
    public Collection<StrategyExecution> getStrategyExecutionCollection() {
        return strategyExecutionCollection;
    }

    public void setStrategyExecutionCollection(Collection<StrategyExecution> strategyExecutionCollection) {
        this.strategyExecutionCollection = strategyExecutionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStrategy != null ? idStrategy.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Strategy)) {
            return false;
        }
        Strategy other = (Strategy) object;
        if ((this.idStrategy == null && other.idStrategy != null) || (this.idStrategy != null && !this.idStrategy.equals(other.idStrategy))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Strategy[ idStrategy=" + idStrategy + " ]";
    }
    
}
