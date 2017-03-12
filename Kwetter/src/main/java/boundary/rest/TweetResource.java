/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import service.TweetService;

/**
 *
 * @author rinke
 */
@Stateless
@Path("tweet")
public class TweetResource {
    @Inject
    TweetService ts;
    
    @GET
    public List<Tweet> allTweet(){
        return ts.allTweet();
    }
    
    @GET
    @Path("/{id}")
    public Tweet getTweet(@PathParam("id") long id){
        Tweet tweet = ts.getTweetById(id);
        return tweet;
    }
    
    @GET
    @Path("/user/{id}")
    public List<Tweet> getTweetByUser(@PathParam("id") long id){
        List<Tweet> tweets = ts.getTweetByUserId(id);
        return tweets;
    }
}
