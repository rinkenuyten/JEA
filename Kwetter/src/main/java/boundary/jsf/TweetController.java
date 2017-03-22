/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.jsf;

import domain.Tweet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.TweetService;

/**
 *
 * @author rinke
 */
@Named
@RequestScoped
public class TweetController implements Serializable {
    
    @Inject
    TweetService TS;
    
    private String welcome = "Teemo mains";
    private List<Tweet> tweetList = new ArrayList<>();

    public TweetController() {
        
    }  

    public String fillList(){
        tweetList.addAll(TS.allTweet());
        return null;
    }
    
    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }
    
    
}
