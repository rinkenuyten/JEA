/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 *
 * @author rinke
 */
@Stateless
public class TweetDAO {
    
    @PersistenceContext
    EntityManager em;
    
    public void save(Tweet tweet){
        em.persist(tweet);
    }
    
    public void delete(Tweet tweet){
        em.remove(tweet);
    }
    
    public List<Tweet> allTweet(){
        return em.createNamedQuery("tweet.all").getResultList();
    }
   
    public Tweet getTweetById(Long id){
        return em.find(Tweet.class, id);
    }
    
    public List<Tweet> getTweetByUserId(Long id){
        User u = em.find(User.class, id);
        return em.createNamedQuery("tweet.user")
        .setParameter("tweetOwner", id)
        .getResultList();
    }
}
