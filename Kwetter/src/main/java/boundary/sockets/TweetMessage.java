/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.sockets;

import java.io.Serializable;

/**
 *
 * @author rinke
 */
public class TweetMessage implements Serializable{
    
    private String text;

    
    public TweetMessage(){}

    public TweetMessage(String text) {
        this.text=text;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
