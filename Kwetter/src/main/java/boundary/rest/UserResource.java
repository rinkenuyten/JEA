/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import service.UserService;

/**
 *
 * @author rinke
 */
@Stateless
@Path("user")
public class UserResource {
    @Inject
    UserService us;
    
    /***
     * Get all users
     * @return 
     */
    @GET
    public List<User> allUser(){
       List<User> result = us.allUser();
       return result;
    }
   
    @GET
    @Path("/first")
    public User allUsers(){
       List<User> result = us.allUser();
       return result.get(0);
    }
    
    /***
     * Get all followers based on user ID
     * @return 
     */
    /*
    @GET
    @Path("/followers/{id}")
    public List<User> getFollowers(){
       List<User> result = us.allUser();
       return result.get(0).getFollowers();
    }
    /*
    @GET
    @Path("/following/{id}")
    public List<User> getFollowing(){
        List<User> result = us.
    }
*/
}
