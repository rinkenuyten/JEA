/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.GroupDAO;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Group;
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
    
    @Inject
    GroupDAO gd;
    
    @PostConstruct
    public void init(){
        Group adminGroup = new Group("admin");
        Group userGroup = new Group("pleb");
        Group moderatorGroup = new Group("moderator");

        
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));
        User user3 = new User("Rinke", "Nederland", "www.google.nl", "mijn bio", PasswordHash.stringToHash("Rinke"));
        
        
        user.addGroup(userGroup);
        user2.addGroup(adminGroup);
        user3.addGroup(userGroup);
        Tweet tweet2 = new Tweet("TestTweet2", user2);
        Tweet tweet = new Tweet("TestTweet", user);

        user.addFollowing(user2); //Lorenzo following Fatih
        
        gd.save(adminGroup);
        gd.save(userGroup);
        ud.save(user);
        ud.save(user2);
        ud.save(user3);
        td.save(tweet);
        td.save(tweet2);
    }
}
