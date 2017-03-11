/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import domain.User;
import java.util.List;
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
}
