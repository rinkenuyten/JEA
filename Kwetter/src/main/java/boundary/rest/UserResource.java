/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import boundary.rest.parameters.UserBean;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import service.UserService;
import util.PasswordHash;

/**
 *
 * @author rinke
 */
@Stateless
@Path("user")
public class UserResource {
    @Inject
    UserService us;
    
    /**
     * Get all users
     * @return 
     */
    @GET
    public List<User> allUser(){
       List<User> result = us.allUser();
       return result;
    }
   
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") long id){
       User result = us.getUserById(id);
       return result;
    }
    
    /**
     * Get all followers based on user ID
     * @param id id of the user whom's followers you want
     * @return the users the user are following
     */
    @GET
    @Path("/followers/{id}")
    public List<User> getFollowers(@PathParam("id") long id){
       List<User> result = us.getFollowers(id);
       return result;
    }
    
    /**
     * Get all the users the user is following
     * @param id id of the user
     * @return the users the user is following
     */
    @GET
    @Path("/following/{id}")
    public List<User> getFollowing(@PathParam("id") long id){
        List<User> result = us.getFollowing(id);
        return result;
    }
    
    @POST
    @Consumes("application/json")
    @Path("/newUser")
    @Produces(MediaType.TEXT_PLAIN)
    public void newUser(final UserBean userbean){
        User user = new User(userbean.name, userbean.location, userbean.website, userbean.bio, PasswordHash.stringToHash(userbean.password));
        us.newUser(user);
    }
}
