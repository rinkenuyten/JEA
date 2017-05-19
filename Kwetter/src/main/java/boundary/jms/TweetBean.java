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
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
            final ConnectionFactory connectionFactory = lookup(ConnectionFactory.class, "jms/__defaultConnectionFactory");
            String DLQ_TOPIC = "jms/KwetterGo/DLQ";
            final Queue DLQ = lookup(Queue.class, DLQ_TOPIC);
            
            JMSContext jmsContext = connectionFactory.createContext();
            final JMSProducer producer = jmsContext.createProducer();
            
            Message text = message;
            try{
                producer.send(DLQ, text.getBody(String.class));
            }
            catch(Exception exc){
                Logger.getLogger(TweetBean.class.getName()).log(Level.SEVERE, null, exc);
            }
           
       }
      
    }
    
    private static <T> T lookup(Class<T> retvalClass, String jndi) {
        try {
            return retvalClass.cast(InitialContext.doLookup(jndi));
        } catch (NamingException ex) {
            throw new IllegalArgumentException("failed to lookup instance of " + retvalClass + " at " + jndi, ex);
        }
    }
   
   
   
}
