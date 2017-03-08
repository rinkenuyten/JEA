/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @NamedQuery(name = "tweet.all", query ="SELECT t FROM Tweet t")
})
public class Tweet {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    
    private String text;
    private Date timestamp;
    private int hearts;
    
    @ManyToOne
    private User tweetOwner;
    
    @OneToMany(mappedBy = "tweet")
    private List<User> hearters;
    
    public Tweet(){
        
    }
    
    public Tweet(String text, User owner){
        this.text = text;
        this.tweetOwner = owner;
        timestamp = new Date();
    }
    
    public String getText(){
        return text;
    }
        
    public String getTimestamp(){
        return sdf.format(timestamp);
    }
        
    public int getHearts(){
        return hearts;
    }

    public User getOwner(){
        return tweetOwner;
    }
    
    public void setOwner(User owner){
        this.tweetOwner = owner;
    }
    
    public void addHeart(User hearter){
        if(hearters.contains(hearter)){
            
        }else{
            hearters.add(hearter);
        }

    }
}
