/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.TestGroup;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pawel
 */
@Stateless
public class TestGroupFacade extends AbstractFacade<TestGroup> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestGroupFacade() {
        super(TestGroup.class);
    }
    public Collection<TestGroup> getAllFirstLevel(){
        return getEntityManager().createNamedQuery("TestGroup.findFirstLevel", TestGroup.class).getResultList();
    }
    public Collection<TestGroup> getByParent( int parentId ){
        return getEntityManager().createNamedQuery("TestGroup.findByParent", TestGroup.class).setParameter("parentId", parentId).getResultList();
    }
    
}
