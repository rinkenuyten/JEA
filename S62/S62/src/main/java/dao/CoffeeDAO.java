/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Coffee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 878550
 */
@Stateless
public class CoffeeDAO {

    @PersistenceContext
    EntityManager em;

    public void save(Coffee coffee) {
        em.persist(coffee);
    }

    public List<Coffee> allCoffee() {
        return em.createNamedQuery("coffee.all").getResultList();
    }
}
