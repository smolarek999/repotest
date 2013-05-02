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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id")
    private String id;
    @Column(name = "enabled")
    private Boolean enabled;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @JoinTable(name = "Users_Authorities", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "authorities_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Authorities> authoritiesCollection;
    @JoinTable(name = "Users_has_StrategyExecution", joinColumns = {
        @JoinColumn(name = "Users_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "StrategyExecution_idStrategyExecution", referencedColumnName = "idStrategyExecution")})
    @ManyToMany
    private Collection<StrategyExecution> strategyExecutionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersid")
    private Collection<TestExecution> testExecutionCollection;

    public Users() {
    }

    public Users(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public Collection<Authorities> getAuthoritiesCollection() {
        return authoritiesCollection;
    }

    public void setAuthoritiesCollection(Collection<Authorities> authoritiesCollection) {
        this.authoritiesCollection = authoritiesCollection;
    }

    @XmlTransient
    public Collection<StrategyExecution> getStrategyExecutionCollection() {
        return strategyExecutionCollection;
    }

    public void setStrategyExecutionCollection(Collection<StrategyExecution> strategyExecutionCollection) {
        this.strategyExecutionCollection = strategyExecutionCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Users[ id=" + id + " ]";
    }
    
}
