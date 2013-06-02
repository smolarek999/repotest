/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.dao.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pawel
 */
@Stateless
public class TestPlanFacade extends AbstractFacade<TestPlan> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<TestPlan> getByUser( Users user) {
        final TypedQuery<TestPlan> query = em.createNamedQuery("TestPlan.findByUser", TestPlan.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    public TestPlanFacade() {
        super(TestPlan.class);
    }
    
}
