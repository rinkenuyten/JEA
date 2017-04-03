/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.jsf;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;
import service.UserService;

/**
 *
 * @author rinke
 */
@Named
@RequestScoped
public class TweetController implements Serializable {
    
    @Inject
    TweetService TS;
    
    @Inject
    UserService US;
    
    private List<Tweet> tweetList = new ArrayList<>();
    private List<Tweet> feedList = new ArrayList<>();
    private String tweettext;
    private User user;

    public TweetController() {
        
    }  

    public String fillListOwner(Long id ){
        tweetList.addAll(TS.getTweetByUserId(id));
        return null;
    }
    
    public User getUser(){

        Principal u = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        this.user = US.getUserByName(u.getName()).get(0);
        
        System.out.print(this.user);
        System.out.print("setted user");
        return this.user;
    }
    
    public void checkUser(){
        getUser();
    }
    public String fillList(){
        tweetList.addAll(TS.allTweet());
        return null;
    }
    
    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public List<Tweet> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Tweet> feedList) {
        this.feedList = feedList;
    }
    
    public void getFeed(String username){
        this.feedList = TS.getFeed(username);
    }
    
    public void makeTweet(){
       System.out.print("maketweet");

       User u = null;
       if(this.user == null){
           u = getUser();
       }
       System.out.print(u);
       TS.createTweet(tweettext, u);
       
    }

    public String getTweettext() {
        return tweettext;
    }

    public void setTweettext(String tweettext) {
        this.tweettext = tweettext;
    }

    public User getUser2() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
