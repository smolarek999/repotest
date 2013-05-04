/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

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
import javax.persistence.ManyToOne;
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
@Table(name = "TestGroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestGroup.findAll", query = "SELECT t FROM TestGroup t"),
    @NamedQuery(name = "TestGroup.findFirstLevel", query = "SELECT t FROM TestGroup t WHERE t.parentId IS NULL"),
    @NamedQuery(name = "TestGroup.findByParent", query = "SELECT t FROM TestGroup t WHERE t.parentId.idTestGroup = :parentId")})
public class TestGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTestGroup")
    private Integer idTestGroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "path")
    private String path;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "parentId")
    private Collection<TestGroup> testGroupCollection;
    @JoinColumn(name = "parentId", referencedColumnName = "idTestGroup")
    @ManyToOne
    private TestGroup parentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testGroupidTestGroup")
    private Collection<Test> testCollection;

    public TestGroup() {
    }

    public TestGroup(Integer idTestGroup) {
        this.idTestGroup = idTestGroup;
    }

    public TestGroup(Integer idTestGroup, String path) {
        this.idTestGroup = idTestGroup;
        this.path = path;
    }

    public Integer getIdTestGroup() {
        return idTestGroup;
    }

    public void setIdTestGroup(Integer idTestGroup) {
        this.idTestGroup = idTestGroup;
    }

    public String getPath() {
        return path;
    }

    public String getPathForChild() {
        if (!path.endsWith("/")) {
            return path + "/" + idTestGroup;
        } else {
            return path + +idTestGroup;
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<TestGroup> getTestGroupCollection() {
        return testGroupCollection;
    }

    public void setTestGroupCollection(Collection<TestGroup> testGroupCollection) {
        this.testGroupCollection = testGroupCollection;
    }

    public TestGroup getParentId() {
        return parentId;
    }

    public void setParentId(TestGroup parentId) {
        this.parentId = parentId;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTestGroup != null ? idTestGroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestGroup)) {
            return false;
        }
        TestGroup other = (TestGroup) object;
        if ((this.idTestGroup == null && other.idTestGroup != null) || (this.idTestGroup != null && !this.idTestGroup.equals(other.idTestGroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestGroup[ idTestGroup=" + idTestGroup + " ]";
    }
}
