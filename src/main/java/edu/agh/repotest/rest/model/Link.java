/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@XmlRootElement(name = "link",  namespace = Response.NAMESPACE)
public class Link {
    @XmlAttribute(name = "rel",namespace = Response.NAMESPACE)
    Relation relation;
    @XmlAttribute(namespace = Response.NAMESPACE)
    String href;

    public Link(){
        
    }
    
    public Link(Relation relation, String url) {
        this.relation = relation;
        this.href = url;
    }

    public Relation getRelation() {
        return relation;
    }

    public String getUrl() {
        return href;
    }
    
    
    
}
