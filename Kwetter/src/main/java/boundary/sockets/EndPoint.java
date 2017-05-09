/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.sockets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import service.TweetService;

/**
 *
 * @author rinke
 */

@ServerEndpoint( 
        value = "/socket",
        encoders = {MessageEncoder.class}, 
        decoders = {MessageDecoder.class},
        configurator = Configurator.class
)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
public class EndPoint {
    
    private static final Logger LOG = Logger.getLogger(EndPoint.class.getName());
    static {
        LOG.setLevel(Level.ALL);
    }
    
    Session session = null;
    
    @Inject
    TweetService TS;
    /**
     * Callback for 'open'-event.
     * @param session the websocket's session
     * @param conf get the encoders/decoders
     * @param pathParam for demo purposes only: a parameter obtained from the path
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("made socket");
        this.session = session;
        session.getUserProperties().put("user", session.getQueryString());
    }
    /**
     * Echoes back the message 5 times.
     * @param session
     * @param message 
     */
    @OnMessage
    public void onMessage(final Session session, final TweetMessage message) {
            System.out.println("got a message" + message);
            //session.getBasicRemote().sendObject(message);
            TS.CreateTweetByUserName(message.getText(), (String) session.getUserProperties().get("user"));

    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.log(Level.SEVERE, "an error occured in session " + session, error);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        LOG.log(Level.FINE, "closed session {0}", session);
    }
}
