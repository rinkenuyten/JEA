/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import boundary.rest.parameters.TweetBean;
import domain.Tweet;
import domain.User;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import service.TweetService;
import service.UserService;

/**
 *
 * @author rinke
 */
@Stateless
@Path("tweet")
public class TweetResource {
    @Inject
    TweetService ts;
    
    @Inject
    UserService us;
    
    @Context
    UriInfo uriInfo;
    
    @GET
    public List<Tweet> allTweet(){
        return ts.allTweet();
    }
    
    @GET
    @Path("{id}")
    public Tweet getTweet(@PathParam("id") long id){
        Tweet tweet = ts.getTweetById(id);
        return tweet;
    }
    
    @GET
    @Path("{id}/user")
    public List<Tweet> getTweetByUser(@PathParam("id") long id){
        List<Tweet> tweets = ts.getTweetByUserId(id);
        return tweets;
    }
    
    @POST
    @Path("{id}/post")
    public void add(@PathParam("id") long id, final TweetBean tweetbean){
        User result = us.getUserById(id);
        ts.createTweet(tweetbean.text, result);
    }
    
    @POST
    public Response postTweet(final TweetBean tweetbean) {
        User result = us.getUserById(tweetbean.userId);
        
        Tweet tweet = ts.createTweet(tweetbean.text, result);
        URI uri = null;
        if (tweet != null) {
            uri = uriInfo.getAbsolutePathBuilder().
                    path(tweet.getId().toString()).
                    build();
        }
        return Response.created(uri).build();
    }
}
