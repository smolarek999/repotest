/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest.model;

import edu.agh.repotest.dao.Users;
import static edu.agh.repotest.rest.model.Response.BASE_URI;
import static edu.agh.repotest.rest.model.Response.cookUrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pawel
 */
@XmlRootElement(name = "user", namespace = Response.NAMESPACE)
public class SimpleUser extends Response{

    static final String DETAIL_URL_FORMAT = BASE_URI + "users/${username}";
    static final String RELATED_TESTS_FORMAT = BASE_URI + "users/${username}/testsExecutions";
   public static List<SimpleUser> fromUsers( List<Users> users ){
       List<SimpleUser> result = new LinkedList<SimpleUser>();
       for( Users user : users){
           result.add(new SimpleUser(user));
       }
       return result;
   }
    
    Users user;
    
    SimpleUser(){
        
    }

    public SimpleUser(Users user) {
        this.user = user;
        links = new ArrayList<Link>();
        Map<String,String> variables = new HashMap<String,String>();
        variables.put("username", user.getUsername());
        links.add(new Link(Relation.about, cookUrl(DETAIL_URL_FORMAT, variables)));
        links.add(new Link(Relation.related, cookUrl(RELATED_TESTS_FORMAT, variables)));
       
    }
    @XmlElement(namespace = Response.NAMESPACE)
    public Integer getId() {
        return user.getId();
    }
    @XmlElement(namespace = Response.NAMESPACE)
    public String getUsername() {
        return user.getUsername();
    }
    
    
}
