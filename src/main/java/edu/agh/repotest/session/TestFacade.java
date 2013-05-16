/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.Test;
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
public class TestFacade extends AbstractFacade<Test> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     
    public Collection<Test> getByGroup( TestGroup group) {
       return getEntityManager().createNamedQuery("Test.findByParent", Test.class).setParameter("parentId", group.getIdTestGroup()).getResultList();
  
    }

    public TestFacade() {
        super(Test.class);
    }
    
}
