/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.session;

import edu.agh.repotest.dao.Feature;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pawel
 */
@Stateless
public class FeatureFacade extends AbstractFacade<Feature> {

    @PersistenceContext(unitName = "edu.agh.repotest_repoTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeatureFacade() {
        super(Feature.class);
    }
    public List<FeatureCount> getTestCountByFeature() {
        List<FeatureCount> featuesCount = new ArrayList<FeatureCount>();
        final Query getCountQuery = em.createQuery("SELECT count(t.idTest), t.featureidFeature.idFeature FROM Test AS t GROUP BY t.featureidFeature");
        for( Object rawObj : getCountQuery.getResultList()){
            Object[] obj = (Object[])rawObj;
           featuesCount.add(new FeatureCount( (Long) obj[0],  (Integer) obj[1],""));
        }
        return featuesCount;
    }
    public static class FeatureCount {
        private Long count;
        private Integer idFeature;
        private String name;

        public FeatureCount(Long count, Integer idFeature, String name) {
            this.count = count;
            this.idFeature = idFeature;
            this.name = name;
        }

        public Integer getIdFeature() {
            return idFeature;
        }

        public Long getCount() {
            return count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public void setIdFeature(Integer idFeature) {
            this.idFeature = idFeature;
        }
        
    }
}
