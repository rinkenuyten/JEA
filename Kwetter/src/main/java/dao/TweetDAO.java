/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
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
   
}
