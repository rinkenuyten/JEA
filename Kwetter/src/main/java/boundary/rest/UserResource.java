/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import boundary.rest.parameters.UserBean;
import domain.User;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.UserService;
import util.PasswordHash;

/**
 *
 * @author rinke
 */
@Stateless
@Path("user")
@DeclareRoles({"admin", "pleb", "moderator"})
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
    @RolesAllowed({"pleb"})
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
    @RolesAllowed({"admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public boolean newUser(final UserBean userbean){
        boolean check = us.checkNameAvailability(userbean.name);
        if(check){
            User user = new User(userbean.name, userbean.location, userbean.website, userbean.bio, PasswordHash.stringToHash(userbean.password));
            us.newUser(user);
        }
        return check;
    }
    
    @POST
    @Consumes("application/json")
    @Path("/editUser/{id}")
    public boolean changeUserSettings(final UserBean userbean, @PathParam("id") long id){
        User result = us.getUserById(id);
        if(result != null){
            if(!userbean.name.equals("")){
                boolean check = us.checkNameAvailability(userbean.name);
                if(check){
                    result.setUserName(userbean.name);
                }
            }
            if(!userbean.location.equals("")){
                result.setLocation(userbean.location);
            }
            if(!userbean.bio.equals("")){
                result.setBio(userbean.bio);
            }
            if(!userbean.website.equals("")){
                result.setWebsite(userbean.website);
            }
            us.changeUserSettings(result);
        }else{
            return false;
        }
        return true;
    }
    
    @POST
    @Consumes("application/json")
    @Path("/Follow/{id}/{id2}")
    public void followUser(@PathParam("id") long id, @PathParam("id2") long id2){
        User user = us.getUserById(id);
        User user2 = us.getUserById(id2);
        us.followUser(user, user2);
    }
}
