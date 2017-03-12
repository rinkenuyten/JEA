package DomainTests;


import domain.Tweet;
import domain.User;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import util.PasswordHash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rinke
 */
public class DomainTests {
    
    @Test
    public void GetUserTestsCorrect(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        assertEquals("user.getbio is not correct", "2manybits", user.getBio());
        assertEquals("user.getlocation is not correct", "Nederland", user.getLocation());
        assertEquals("user.password is not correct", "114b25448d0a392be6f3f78d7f0f1f46fcb301244088ad2a229b685604606ff6", user.getPassword());
        assertEquals("user.getusername is not correct", "Lorenzo", user.getUserName());
        assertEquals("user.getwebsite is not correct", "www.lorenzosuiker.nl", user.getWebsite());
    }
    
    @Test
    public void SetUserTestsCorrect(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        user.setUserName("Test");
        assertEquals("user.setusername is not correct", "Test", user.getUserName());
        
        user.setBio("bio");
        assertEquals("user.setbio is not correct", "bio", user.getBio());
        
        user.setLocation("Duitsland");
        assertEquals("user.setLocation is not correct", "Duitsland", user.getLocation());
        
        user.setPassword("Pass");
        assertEquals("user.setPassword is not correct", "Pass", user.getPassword());
        
        user.setWebsite("website");
        assertEquals("user.setwebsite is not correct", "website", user.getWebsite());
    }
    
    @Test
    public void GetTweetTestsCorrect(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        Tweet tweet = new Tweet("TestTweet2", user);
        
        assertEquals("tweet.getOwner is not correct", user, tweet.getOwner());
        assertEquals("tweet.getText is not correct", "TestTweet2", tweet.getText());
        
        Date tweetTimestamp = new Date();
        assertEquals("tweet.getTweet is not correct", tweetTimestamp, tweet.getTweetTimestamp());
    }
    
    @Test
    public void SetTweetsTests(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        Tweet tweet = new Tweet("TestTweet2", user);
        
        User user2 = new User("Rinke", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Rinke"));
        tweet.setOwner(user2);
        assertEquals("user.setowner is not correct", user2, tweet.getOwner());
        
        tweet.setText("test");
        assertEquals("user.settext is not correct", "test", tweet.getText());
    }
}
