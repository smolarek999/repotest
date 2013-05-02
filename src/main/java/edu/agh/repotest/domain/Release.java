/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Release_Table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Release.findAll", query = "SELECT r FROM Release r")})
public class Release implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRelease")
    private Integer idRelease;
    @Size(max = 45)
    @Column(name = "Releasecol")
    private String releasecol;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "release")
    private Load load;

    public Release() {
    }

    public Release(Integer idRelease) {
        this.idRelease = idRelease;
    }

    public Integer getIdRelease() {
        return idRelease;
    }

    public void setIdRelease(Integer idRelease) {
        this.idRelease = idRelease;
    }

    public String getReleasecol() {
        return releasecol;
    }

    public void setReleasecol(String releasecol) {
        this.releasecol = releasecol;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelease != null ? idRelease.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Release)) {
            return false;
        }
        Release other = (Release) object;
        if ((this.idRelease == null && other.idRelease != null) || (this.idRelease != null && !this.idRelease.equals(other.idRelease))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.domain.Release[ idRelease=" + idRelease + " ]";
    }
    
}
