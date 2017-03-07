/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.UserDAO;
import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author rinke
 */
@Singleton
@Startup
public class InitUser {
    
    @Inject
    UserDAO ud;
    
    @PostConstruct
    public void init(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits");
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "turkish");
        ud.save(user);
        ud.save(user2);
    }
}
