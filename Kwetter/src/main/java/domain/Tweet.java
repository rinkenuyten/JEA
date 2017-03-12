/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author rinke
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "tweet.all", query ="SELECT t FROM Tweet t"),
    @NamedQuery(name = "tweet.user", query ="SELECT t FROM Tweet t WHERE t.tweetOwner.id LIKE :tweetOwner") //Get tweet based on userid
})

public class Tweet implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    private String tweetText;
    private Date tweetTimestamp;
    private int tweetHearts;
    
    @ManyToOne
    private User tweetOwner;
    
    //@OneToMany(mappedBy = "tweet")
    //private List<User> hearters;
    
    public Tweet(){
        
    }
    
    public Tweet(String text, User owner){
        this.tweetText = text;
        this.tweetOwner = owner;
        tweetTimestamp = new Date();
        owner.createTweet(this);
    }
    
    public void setText(String text){
        tweetText = text;
    }
    
    public String getText(){
        return tweetText;
    }
    
    public Date getTweetTimestamp() {
        return tweetTimestamp;
    }

    public void setTweetTimestamp(Date tweetTimestamp) {
        this.tweetTimestamp = tweetTimestamp;
    }
        
    public void setHearts(int heart){
        tweetHearts =+ heart;
    }
    
    public int getHearts(){
        return tweetHearts;
    }

    public User getOwner(){
        return tweetOwner;
    }
    
    public void setOwner(User owner){
        this.tweetOwner = owner;
    }
  /*  
    public void addHeart(User hearter){
        if(hearters.contains(hearter)){
            
        }else{
            hearters.add(hearter);
        }

    }
*/
}
