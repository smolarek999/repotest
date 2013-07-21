/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest;

import edu.agh.repotest.rest.model.SimpleUser;
import edu.agh.repotest.session.TestExecutionFacade;
import edu.agh.repotest.session.UsersFacade;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author pawel
 */
@Stateless
@Path("/users")
public class Users {

    @EJB
    TestExecutionFacade executionFacade;
    @EJB
    UsersFacade usersFacade;

    @GET
    public List<SimpleUser> showAll() {
        return SimpleUser.fromUsers(usersFacade.findAll());

    }

    @GET
    @Path("{username}")
    @Produces({MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    public SimpleUser showUser(@PathParam("username") String username) {
        return new SimpleUser(usersFacade.getByName(username));
    }

    @GET
    @Path("{username}/testsExecutions")
    @Produces({MediaType.APPLICATION_ATOM_XML, MediaType.APPLICATION_JSON})
    public List<edu.agh.repotest.dao.TestExecution> showTestsForUser(@PathParam("username") String username) {
        final edu.agh.repotest.dao.Users user = usersFacade.getByName(username);
        if (user != null) {
            return executionFacade.getTakenByUser(user);
        } else {
            return new LinkedList<edu.agh.repotest.dao.TestExecution>();
        }
    }
}
