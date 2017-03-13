/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author rinke
 */
@Entity
@Table(name = "SecurityGroup")
public class Group implements Serializable {
    
    @Id
    private String groupName;
    
    @ManyToMany
    @JoinTable(name="USER_GROUP",
    joinColumns = @JoinColumn(name = "groupName",
    referencedColumnName = "groupName"),
    inverseJoinColumns = @JoinColumn(name = "userName",
    referencedColumnName = "userName"))
    private List<User> users;

    public Group(){    
    
    }
    
    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        this.users.add(user);
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
}
