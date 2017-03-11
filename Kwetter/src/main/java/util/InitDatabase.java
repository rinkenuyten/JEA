/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author rinke
 */
@Singleton
@Startup
public class InitDatabase {
    
    @Inject
    UserDAO ud;
    
    @Inject
    TweetDAO td;
    
    @PostConstruct
    public void init(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits");
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "turkish");
        Tweet tweet2 = new Tweet("TestTweet2", user2);
        Tweet tweet = new Tweet("TestTweet", user);

        user.addFollowing(user2); //Lorenzo following Fatih
        
        
        ud.save(user);
        ud.save(user2);
        td.save(tweet);
        td.save(tweet2);
    }
}
