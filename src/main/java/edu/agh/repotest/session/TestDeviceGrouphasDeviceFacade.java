/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.DeviceInGroupOfDevices;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pawel
 */
@Stateless
public class TestDeviceGrouphasDeviceFacade extends AbstractFacade<DeviceInGroupOfDevices> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestDeviceGrouphasDeviceFacade() {
        super(DeviceInGroupOfDevices.class);
    }
    
}
