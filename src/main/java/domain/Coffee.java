/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author 878550
 */
@Entity
@NamedQuery(name = "coffee.all", query ="SELECT c FROM Coffee c")
public class Coffee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String heavy;

    public Coffee() {
    }

    public Coffee(String brand, String heavy) {
        this.brand = brand;
        this.heavy = heavy;
    }

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getHeavy() {
        return heavy;
    }

    public void setHeavy(String heavy) {
        this.heavy = heavy;
    }
    
}
