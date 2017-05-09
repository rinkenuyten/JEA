/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boundary.sockets;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decodes {@link Message}s from json
 * @author jgeenen
 */
public class MessageDecoder implements Decoder.Text<TweetMessage>{
    
    private final Gson gson = new Gson();
    
    private static final Logger LOG = Logger.getLogger(MessageDecoder.class.getName());
    static {
        LOG.setLevel(Level.ALL);
    }
    
    
    @Override
    public TweetMessage decode(String m) throws DecodeException {
        try{
           return new TweetMessage(m);
        } catch(Throwable t){
            throw new DecodeException(m, "json decoding error", t);
        }
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
        LOG.fine("created decoder");
    }

    @Override
    public void destroy() {
        LOG.fine("destroyed decoder");
    }
    
}
