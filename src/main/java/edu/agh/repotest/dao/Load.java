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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pawel
 */
@Entity
@Table(name = "Load_Table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Load.findAll", query = "SELECT l FROM Load l")})
public class Load implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoad")
    private Integer idLoad;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "releaseId", referencedColumnName = "idRelease")
    private Release release;

    public Load() {
    }

    public Load(Integer releaseidRelease) {
        this.idLoad = releaseidRelease;
    }

    public Integer getIdLoad() {
        return idLoad;
    }

    public void setIdLoad(Integer idLoad) {
        this.idLoad = idLoad;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Release getRelease() {
        return release;
    }

    public void setRelease(Release releaseTable) {
        this.release = releaseTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoad != null ? idLoad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Load)) {
            return false;
        }
        Load other = (Load) object;
        if ((this.idLoad == null && other.idLoad != null) || (this.idLoad != null && !this.idLoad.equals(other.idLoad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.LoadTable[ releaseidRelease=" + idLoad + " ]";
    }
    
}
