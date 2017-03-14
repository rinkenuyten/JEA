/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import domain.User;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author rinke
 */
@Stateless
public class UserService {
    
    @Inject
    UserDAO ud;
    
    public List<User> allUser(){
        return ud.allUser();
    }
    
    public List<User> getUserByName(String name){
        return ud.getUserByName(name);
    }
    
    public User getUserById(Long id){
        return ud.getUserById(id);
    }
    
    public List<User> getFollowing(Long id){
       return ud.getUserById(id).getFollowing();
    }
    
    public List<User> getFollowers(Long id){
        return ud.getUserById(id).getFollowers();
    }
    
    public void newUser(User user){
        ud.save(user);
    }
    
    public void setDAO(UserDAO dao){
        ud = dao;
    }
    
    public boolean checkNameAvailability(String name){
        return ud.checkNameAvailability(name);
    }
    
    public void changeUserSettings(User user){
        ud.merge(user);
    }
    
    public void followUser(User user, User user2){
        user.addFollowing(user2);
        ud.merge(user);
        ud.merge(user2);
    }
}
