/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rinke
 */
@Stateless
public class TweetService {
    
    @Inject
    TweetDAO td;
    
    @Inject
    UserDAO ud;
    
    public void save(Tweet tweet){
        td.save(tweet);
    }
    
    public List<Tweet> allTweet(){
        return td.allTweet();
    }
    
    public Tweet getTweetById(Long id){
        return td.getTweetById(id);
    }
    
    public List<Tweet> getTweetByUserId(Long id){
        return td.getTweetByUserId(id);
    }
    
    public void setDAO(TweetDAO dao){
        td = dao;
    }
    
    public Tweet createTweet(String text, User user){
        Tweet tweet = new Tweet(text, user);
        td.save(tweet);
        return tweet;
    }
    
    public List<Tweet> getFeed(String username){
        List<Tweet> feedlist = new ArrayList<>();
        User u = ud.getUserByName(username).get(0);
            for (User user : u.getFollowing()) {
                List<Tweet> result = this.getTweetByUserId(user.getId());
                feedlist.addAll(result);
            }
        return feedlist;
    }
}

