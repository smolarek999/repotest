/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.dao.TestStatusChange;
import edu.agh.repotest.dao.TestStatusChangeSum;
import edu.agh.repotest.dao.TestStatusHelper;
import edu.agh.repotest.dao.Users;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.joda.time.DateMidnight;

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

    public void edit(TestExecution testExecution, TestStatus previousState) {

        super.edit(testExecution);
        TestStatus currentStatus = testExecution.getStatus();
        TestStatusChange change = new TestStatusChange();
        change.setPreviousStatus(previousState);
        change.setCurrentStatus(currentStatus);
        change.setChangeFactor(TestStatusHelper.getChangeFactorForTransition(previousState, currentStatus));
        change.setTestExecution(testExecution);
        change.setDateTime(new Timestamp(System.currentTimeMillis()));
        getEntityManager().persist(change);

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

    public List<TestStatusChange> findNLastStatusChanges(int count) {
        TypedQuery<TestStatusChange> query = em.createNamedQuery("TestStatusChange.getOrederedByDate", TestStatusChange.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public List<TestStatusChange> findByTestExecution(TestExecution testExecution) {
        TypedQuery<TestStatusChange> query = em.createNamedQuery("TestStatusChange.getByTestExecution", TestStatusChange.class);
        query.setParameter("testExecution", testExecution);
        return query.getResultList();
    }

    public List<TestStatusChangeSum> getTestStatusChangeSumForTimePeriod(DateMidnight from, DateMidnight to) {
        List<TestStatusChangeSum> result = new ArrayList<TestStatusChangeSum>();
        final Query query = getEntityManager().createQuery("SELECT SUM(tsc.changeFactor), tsc.dateTime FROM TestStatusChange tsc WHERE tsc.dateTime >= :fromDate and tsc.dateTime < :toDate   GROUP BY FUNC('DATE',tsc.dateTime) ORDER BY tsc.dateTime");
        query.setParameter("fromDate", new Timestamp(from.getMillis()));
        query.setParameter("toDate", new Timestamp(to.getMillis()));
        for (Object rawObject : query.getResultList()) {
            if (rawObject instanceof Object[]) {
                Object[] objects = (Object[]) rawObject;
                TestStatusChangeSum sum = new TestStatusChangeSum(new DateMidnight((Timestamp) objects[1]), (Long) objects[0]);
                result.add(sum);
            }
        }
        return result;
    }

    public Long getChangeFactorBeforeDate(DateMidnight date) {
        final Query query = getEntityManager().createQuery("SELECT SUM(tsc.changeFactor) FROM TestStatusChange tsc WHERE tsc.dateTime < :dateTime ");
        query.setParameter("dateTime", new Timestamp(date.getMillis()));
        Object obj = query.getSingleResult();
        if (obj == null) {
            return 0L;
        } else {
            return (Long) obj;
        }

    }

    public Long getTestExecutionCount() {
        final Query query = getEntityManager().createQuery("SELECT COUNT(te.idTestExecution) FROM TestExecution te ");
        return (Long) query.getSingleResult();
    }

    public List<TestStatusCount> getTestStatusCount() {
        List<TestStatusCount> statusCounts = new ArrayList<TestStatusCount>();
        Query query = em.createQuery("SELECT te.status, COUNT(te.idTestExecution) FROM TestExecution te WHERE te.testPlan.planState = :state GROUP BY te.status ");
        query.setParameter("state", TestPlan.State.Open);
        for (Object rawObject : query.getResultList()) {
            Object[] obj = (Object[]) rawObject;
            statusCounts.add(new TestStatusCount((TestStatus) obj[0], (Long) obj[1]));
        }
        return statusCounts;
    }

    public static class TestStatusCount {

        TestStatus status;
        Long count;

        public TestStatusCount(TestStatus status, Long count) {
            this.status = status;
            this.count = count;
        }

        public Long getCount() {
            return count;
        }

        public TestStatus getStatus() {
            return status;
        }
    }
}
