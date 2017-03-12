/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TweetDAO;
import domain.Tweet;
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
@DeclareRoles({"regular_role", "admin_role"})
public class TweetService {
    
    @Inject
    TweetDAO td;
    
    @RolesAllowed({"admin_role"})
    public List<Tweet> allTweet(){
        return td.allTweet();
    }
    
    public Tweet getTweetById(Long id){
        return td.getTweetById(id);
    }
    
    public List<Tweet> getTweetByUserId(Long id){
        return td.getTweetByUserId(id);
    }
    
    public void setDAO(TweetDAO dao){
        td = dao;
    }
}

