/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;

/**
 *
 * @author JWR734
 */
public abstract class EnityWithCondition implements Serializable{
    @Transient
    List<Condition> rawCondition;
    
    
    public List<Condition> getRawCondition() {
        if( rawCondition == null ){
            rawCondition = Condition.createFromString(getCondition());
        }
        return rawCondition;
    }

    public void setRawCondition(List<Condition> condition) {
        setCondition(Condition.createToString(condition)) ;
        this.rawCondition = condition;
    }
    
        public abstract String getCondition() ;

    public abstract void setCondition(String condition);
}
