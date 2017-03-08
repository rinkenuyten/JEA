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
public class UserDAO {
    
    @PersistenceContext
    EntityManager em;
    
    public void save(User user){
        em.persist(user);
    }
    
    public void delete(User user){
        em.remove(user);
    }
    
    public List<User> allUser(){
        return em.createNamedQuery("user.all").getResultList();
    }
    
    public List<User> getUserByName(String name){
        return em.createNamedQuery("findAllCustomersWithName")
        .setParameter("userName", name)
        .getResultList();
    }
    
    /*public List<Tweet> getTweetByUser(User user){
        return em.
    }*/
}
