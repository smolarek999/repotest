/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.domain.Device;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pawel
 */
@Stateless
public class DeviceFacade extends AbstractFacade<Device> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceFacade() {
        super(Device.class);
    }
    
}
