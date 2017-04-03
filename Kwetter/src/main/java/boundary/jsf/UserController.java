/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.jsf;

import domain.User;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.*;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import service.UserService;


/**
 *
 * @author rinke
 */
@Named
@RequestScoped
public class UserController implements Serializable {
    
    @Inject
    UserService US;
    
    private String userName;
    private String welcome = "hello skrub";
    private User user;
    private List<User> followers = new ArrayList<>();
    private List<User> following = new ArrayList<>();
    
    public UserController(){
        
    }


    public String fillFollowers(){
        followers = US.getFollowers(user.getId());
        followers.size();
        user.getFollowers().size();
        return null;
    }
    
    public String fillFollowing(){
        following = US.getFollowing(user.getId());
        following.size();
        return null;
    }
    
    public void check(){
        Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        setUserName(user.getName());
        setUser(US.getUserByName(user.getName()).get(0));
    }
    
    public void setUserByURL() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        setUserName(username);
        setUser(US.getUserByName(username).get(0));
    }
    
    public void getUser(String name){
        
    }
    
    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFollowers() {
        return followers;
    } 

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
    
    public void redirectToUserPage(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("UserPage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String logOut(){
           ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
     return "Login.xhtml";
    }
}
