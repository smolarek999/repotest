/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pawel
 */
@Embeddable
public class TestDeviceGroupPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTestDeviceGroup")
    private int idTestDeviceGroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Test_idTest")
    private int testidTest;

    public TestDeviceGroupPK() {
    }

    public TestDeviceGroupPK(int idTestDeviceGroup, int testidTest) {
        this.idTestDeviceGroup = idTestDeviceGroup;
        this.testidTest = testidTest;
    }

    public int getIdTestDeviceGroup() {
        return idTestDeviceGroup;
    }

    public void setIdTestDeviceGroup(int idTestDeviceGroup) {
        this.idTestDeviceGroup = idTestDeviceGroup;
    }

    public int getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(int testidTest) {
        this.testidTest = testidTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTestDeviceGroup;
        hash += (int) testidTest;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestDeviceGroupPK)) {
            return false;
        }
        TestDeviceGroupPK other = (TestDeviceGroupPK) object;
        if (this.idTestDeviceGroup != other.idTestDeviceGroup) {
            return false;
        }
        if (this.testidTest != other.testidTest) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.agh.repotest.dao.TestDeviceGroupPK[ idTestDeviceGroup=" + idTestDeviceGroup + ", testidTest=" + testidTest + " ]";
    }
    
}
