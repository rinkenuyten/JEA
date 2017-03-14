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
    
    public void merge(User user){
        em.merge(user);
    }
    
    public List<User> allUser(){
        return em.createNamedQuery("user.all").getResultList();
    }
    
    public List<User> getUserByName(String name){
        return em.createNamedQuery("findAllCustomersWithName")
        .setParameter("userName", name)
        .getResultList();
    }
    
    public User getUserById(Long id){
        return em.find(User.class, id);
    }
    
    public boolean checkNameAvailability(String name){
        List<User> users = em.createNamedQuery("user.namecheck")
        .setParameter("userName", name)
        .getResultList();
        return users.isEmpty();
    }
}
