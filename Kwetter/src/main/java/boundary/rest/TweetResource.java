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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Tweet getTweet(@PathParam("id") long id){
        Tweet tweet = ts.getTweetById(id);
        
        User user = tweet.getOwner();
        /*tweet.setSelf(
                Link.fromUri(uriInfo.getBaseUriBuilder()
                    .path(getClass()).path(getClass(), "getOwner")
                    .build(tweet.getId())).rel("tweet").type("GET").build());
        */
        URI uri = null;
        if (tweet != null) {
            uri = UriBuilder.fromUri("http://localhost:8080/Kwetter/api/user/")
                    .path(tweet.getOwner().getUserName().toString())
                    .build();
            /*uri = uriInfo.getAbsolutePathBuilder().
                    path(tweet.getOwner().getId().toString()).
                    build();*/
        }
        tweet.setOwnerLink(uri);
        
        return tweet;

       // return Response.accepted(tweet).build();
    }
    
    
    
    @GET
    @Path("{id}/user")
    public List<Tweet> getTweetByUser(@PathParam("id") long id){
        List<Tweet> tweets = ts.getTweetByUserId(id);
        return tweets;
    }
    
    @GET
    @Path("user/{username}")
    public List<Tweet> getTweetByUserName(@PathParam("name") String name){
        List<User> result = us.getUserByName(name);
        User user = result.get(0);
        List<Tweet> tweets = ts.getTweetByUserId(user.getId());
        return tweets;
    }
    
    
    @POST
    @Path("{id}/{text}")
    public Response add(@PathParam("id") long id, @PathParam("text") String texttweet){
        User result = us.getUserById(id);
        System.out.println(texttweet);
        Tweet tweet = ts.createTweet(texttweet, result);
        
        URI uri = null;
        if (tweet != null) {
            uri = uriInfo.getAbsolutePathBuilder().
                    path(tweet.getId().toString()).
                    build();
        }
        return Response.created(uri).build();
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
