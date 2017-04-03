/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest.parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rinke
 */
@XmlRootElement
public class TweetBean {
    
    @XmlElement public Long userId;
    @XmlElement public String text; 
}
