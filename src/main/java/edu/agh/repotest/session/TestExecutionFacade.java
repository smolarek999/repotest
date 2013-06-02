/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.dao.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pawel
 */
@Stateless
public class TestExecutionFacade extends AbstractFacade<TestExecution> {

    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestExecutionFacade() {
        super(TestExecution.class);
    }
    public List<TestExecution> getAvailableForUser(Users user) {
        TypedQuery query = em.createNamedQuery("TestExecution.findByTestPlanUserAndStatus", TestExecution.class);
        query.setParameter("status", TestStatus.WAITING);
        query.setParameter("user", user);
        return query.getResultList();
    }
    public List<TestExecution> getTakenByUser(Users user) {
        TypedQuery query = em.createNamedQuery("TestExecution.findByUserAndStatus", TestExecution.class);
        query.setParameter("status", TestStatus.IN_PROGRESS);
        query.setParameter("user", user);
        return query.getResultList();
    }
}
