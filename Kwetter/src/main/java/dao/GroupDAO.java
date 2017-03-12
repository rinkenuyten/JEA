/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rinke
 */
@Stateless
public class GroupDAO {
    
    @PersistenceContext
    EntityManager em;
    
    public void save(Group group){
        em.persist(group);
    }
    
    public void delete(Group group){
        em.remove(group);
    }
}
