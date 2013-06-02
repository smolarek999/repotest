/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.util.ConditionHelper;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;

/**
 *
 * @author JWR734
 */
public abstract class EnityWithCondition implements Serializable{
    @Transient
    Condition rawCondition;
    
    public void refreshCondition(){
         setCondition(ConditionHelper.createToString(rawCondition)) ;
    }
    public Condition getRawCondition() {
        System.out.println("getRawCondition");
        
        if( rawCondition == null ){
            rawCondition = ConditionHelper.createFromString(getConditionInner());
        }
        return rawCondition;
    }

    public void setRawCondition(Condition condition) {
        System.out.println("setRawCondition");
        setCondition(ConditionHelper.createToString(condition)) ;
        this.rawCondition = condition;
        
    }
    
        public abstract String getCondition() ;
        
        public abstract String getConditionInner() ;

    public abstract void setCondition(String condition);
}
