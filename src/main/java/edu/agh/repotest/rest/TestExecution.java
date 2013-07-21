/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest;

import edu.agh.repotest.dao.Users;
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
@Path("/testExecutions")
@Stateless
public class TestExecution {
    @EJB
    TestExecutionFacade executionFacade;
    @EJB
    UsersFacade usersFacade;

    @GET
    @Produces({MediaType.APPLICATION_ATOM_XML,MediaType.APPLICATION_JSON})
    @Path("{id}")
    public edu.agh.repotest.dao.TestExecution byId(@PathParam("id") String id) {
            return executionFacade.find(id);
    }

}
