/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

import edu.agh.repotest.util.ConditionHelper;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlAccessMethods;

/**
 *
 * @author JWR734
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EnityWithCondition implements Serializable {

    @Transient
    Condition rawCondition;

    public void refreshCondition() {
        setCondition(ConditionHelper.createToString(rawCondition));
    }
    public Condition getRawCondition() {
        System.out.println("getRawCondition");

        if (rawCondition == null) {
            rawCondition = ConditionHelper.createFromString(getConditionInner());
        }
        return rawCondition;
    }

    public void setRawCondition(Condition condition) {
        System.out.println("setRawCondition");
        setCondition(ConditionHelper.createToString(condition));
        this.rawCondition = condition;

    }

    public abstract String getCondition();

    public abstract String getConditionInner();

    public abstract void setCondition(String condition);
    
    public abstract String getConditionalId();
    public abstract String getConditionalDesc();
}
