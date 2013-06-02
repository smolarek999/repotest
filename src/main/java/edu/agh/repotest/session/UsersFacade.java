/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

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
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    public Users getByName(String username) {
        final TypedQuery<Users> query = em.createNamedQuery("Users.findByName", Users.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public List<Users> getByAuthorityname(String authorityname) {
        final TypedQuery<Users> query = em.createNamedQuery("Users.findByAuthorityname", Users.class);
        query.setParameter("authorityname", authorityname);
        return query.getResultList();
    }
}
