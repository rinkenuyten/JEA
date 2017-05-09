/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.jms;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import service.TweetService;

/**
 *
 * @author rinke
 */
@MessageDriven(mappedName = "jms/KwetterGo/queue", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TweetBean implements MessageListener{
   @Inject
   TweetService TS;
   
   public TweetBean(){
       
   }

    @Override
    public void onMessage(Message message) { 
       try {
           Message text = message;
           TS.CreateTweetByUserName(text.getBody(String.class), "Fatih");
       } catch (JMSException ex) {
           Logger.getLogger(TweetBean.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }
   
   
   
}
