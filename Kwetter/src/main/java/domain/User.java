/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 *
 * @author rinke
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "user.all", query ="SELECT u FROM User u"),
    @NamedQuery(name = "user.name", query ="SELECT u FROM User u WHERE u.name LIKE :userName"),
    @NamedQuery(name = "user.tweets", query ="SELECT t FROM Tweet WHERE t.tweetOwner = :OwnerID")
})

public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    private String name;
    private String location;
    private String website;
    private String bio;
    
    @OneToMany(mappedBy = "user")
    private List<User> followers;
    @OneToMany(mappedBy = "user")
    private List<User> following;
    @OneToMany(mappedBy = "tweetOwner")
    private List<Tweet> tweets;
    
    @ManyToOne
    private User user;
    @ManyToOne
    private Tweet tweet;
    
    public User(){
        
    }

    public User(String name, String location, String website, String bio){
        this.name = name;
        this.location = location;
        this.website = website;
        this.bio = bio;
    }
    
    public String getname(){
        return name;
    }
        
    public void setName(String name){
        this.name = name;
    }
    
    public String getLocation(){
        return location;
    }
        
    public void setLocation(String location){
        this.location = location;
    }
    
    public String getWebsite(){
        return website;
    }
        
    public void setWebsite(String website){
        this.website = website;
    }
    
    public String getBio(){
        return bio;
    }
    
    public void setBio(String bio){
        this.bio = bio;
    }
    
    public List<User> getFollowers(){
        return followers;
    }
    
    public List<User> getFollowing(){
        return following;
    }
    
    public List<Tweet> getTweets(){
        return tweets;
    }
    
    public void createTweet(Tweet tweet){
        tweets.add(tweet);
    }
}
