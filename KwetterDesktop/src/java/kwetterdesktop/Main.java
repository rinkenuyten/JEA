/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetterdesktop;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rinke
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    /**
     * the preconfigured GlassFish-default connection factory
     */
    private static final String JNDI_CONNECTION_FACTORY = "jms/__defaultConnectionFactory";
    /**
     * the JNDI name under which your {@link Topic} should be: you have to
     * create the topic before running this class
     */
    private static final String JNDI_TOPIC = "jms/KwetterGo/queue";

    /**
     * @param <T> the return type
     * @param retvalClass the returned value's {@link Class}
     * @param jndi the JNDI path to the resource
     * @return the resource at the specified {@code jndi} path
     */
    private static <T> T lookup(Class<T> retvalClass, String jndi) {
        try {
            return retvalClass.cast(InitialContext.doLookup(jndi));
        } catch (NamingException ex) {
            throw new IllegalArgumentException("failed to lookup instance of " + retvalClass + " at " + jndi, ex);
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final ConnectionFactory connectionFactory = lookup(ConnectionFactory.class, JNDI_CONNECTION_FACTORY);
        final Queue queue = lookup(Queue.class, JNDI_TOPIC);
        //JMSContext implements AutoClosable: let us try 'try-with-resources'
        //see http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (JMSContext jmsContext = connectionFactory.createContext()) {

            final JMSProducer producer = jmsContext.createProducer();
            String text = "TestTweet from desktop app";
            producer.send(queue, text);
            LOG.log(Level.INFO, "sent {0} to {1}", new Object[]{text, JNDI_TOPIC});    
        }
    }
    
}
