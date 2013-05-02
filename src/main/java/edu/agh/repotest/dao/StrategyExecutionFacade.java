/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.domain.StrategyExecution;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pawel
 */
@Stateless
public class StrategyExecutionFacade extends AbstractFacade<StrategyExecution> {
    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StrategyExecutionFacade() {
        super(StrategyExecution.class);
    }
    
}
