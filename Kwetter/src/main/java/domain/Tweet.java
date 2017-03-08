/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author rinke
 */
@Entity
@NamedQuery(name = "tweet.all", query ="SELECT t FROM Tweet t")
public class Tweet {
    
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
    }
    
    public void setText(String text){
        tweetText = text;
    }
    
    public String getText(){
        return tweetText;
    }
        
    public void setTimestamp(){
        tweetTimestamp = new Date();
    }
    
    public String getTimestamp(){
        return sdf.format(tweetTimestamp);
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
