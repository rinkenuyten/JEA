/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author rinke
 */
@Entity
@Table(name = "KwetterUser")
@NamedQueries({
    @NamedQuery(name = "user.all", query ="SELECT u FROM User u"),
    @NamedQuery(name = "user.name", query ="SELECT u FROM User u WHERE u.userName LIKE :userName")
})

public class User implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    
    private String userName;
    private String location;
    private String website;
    private String bio;
    private String password;
    
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    @ManyToMany
    private List<User> followers;
    
    @OneToMany(mappedBy = "tweetOwner")
    private List<Tweet> tweets;
    
    @ManyToMany(mappedBy = "followers")
    private List<User> following;
    
    public User(){
        
    }

    public User(String name, String location, String website, String bio, String password){
        if(name == null || password == null){
            try {
                throw new Exception("Password or name is null (not allowed)");
            } catch (Exception ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.userName = name;
        this.location = location;
        this.website = website;
        this.bio = bio;
        this.password = password;
        
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.groups = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroup(){
        return groups;
    }
    
    public void addGroup(Group group){
        groups.add(group);
        group.addUser(this);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<User> getFollowers() {
        return followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public List<User> getFollowing() {
        return following;
    }
    
    public void addFollower(User user){
        this.followers.add(user);
    }
    
    public void addFollowing(User user){
        this.following.add(user);
        user.addFollower(this);
    }
    
    public void createTweet(Tweet tweet){
       if (tweets == null)
           tweets = new ArrayList<>();
       tweets.add(tweet);
    }
}
