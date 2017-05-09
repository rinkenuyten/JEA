/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dao.KweetDAO;
import domain.Follow;
import domain.Kweet;
import domain.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.PathParam;
import service.FollowService;
import service.KweetService;
import service.SessionService;
import service.UserService;

/**
 *
 * @author 878550
 */
@Stateless
@Path("kweet")
@ServerEndpoint(value = "/kweetsocket")
public class KweetResource {

    @Inject
    KweetService ks;

    @Inject
    SessionService ss;

    @Inject
    UserService us;

    @Inject
    KweetDAO kd;

    Session userSession = null;

    @Context
    UriInfo uriInfo;

    /*
     * Ook een dingetje
     * All kweets
     */
    
    @Inject
    FollowService fs;
    
    ObjectMapper mapper = new ObjectMapper();

    @GET
    public List<Kweet> allKweets() {
        return ks.allKweets();
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {

        System.out.println("New onopen websocket" + session);
        User user = us.getUser(Integer.parseInt(session.getQueryString()));
        session.getUserProperties().put("user", user);

    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("New message websocket");
        User sender = (User) session.getUserProperties().get("user");
        Kweet kweet = new Kweet(sender, message);
        String kweetJson = "<tr data-ri=\"0\" class=\"ui-widget-content ui-datatable-even\" role=\"row\"><td role=\"gridcell\"><a href=\"http://localhost:8080/Kwetter/faces/UserView.xhtml?id=" + sender.getId() + "\">"+ sender.getUsername() +"</a></td><td role=\"gridcell\">"+ kweet.getContent() +"</td><td role=\"gridcell\">"+ kweet.getPrettyTimeAgo() +"</td></tr>";
        /*
        try {
            kweetJson = mapper.writeValueAsString(kweet);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(KweetResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
*/
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen()) {
                User user = (User) s.getUserProperties().get("user");
                List<Follow> follows = fs.getFollowing(user);
                for (Follow f : follows) {
                    if (f.getFollows().getId() == sender.getId()) {
                        try {
                            s.getBasicRemote().sendText(kweetJson);
                        } catch (IOException ex) {
                            Logger.getLogger(KweetResource.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (user.getId() == sender.getId()) {
                        try {
                            s.getBasicRemote().sendText(kweetJson);
                        } catch (IOException ex) {
                            Logger.getLogger(KweetResource.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return null;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("closed websocket");
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        System.out.println("error websocket");
    }

    @POST
    @Path("addkweet")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/html")
    public Response addKweet(@QueryParam("id") int id,
            @QueryParam("content") String summary) {

        Kweet kweet = ks.newKweet(id, summary);
        if (kweet != null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("searchKweet")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("Application/json")
    public String searchKweet(@QueryParam("query") String query) {
        return gson.toJson(ks.searchKweets(query));
    }

    @POST
    @Path("getLatestKweet")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/html")
    public String getLatestKweets(@QueryParam("username") String username) {
        User user = us.getUserByUsername(username);
        List<Kweet> kweets = kd.getLatestFromUser(user);
        return gson.toJson(kweets);
    }

    @POST
    @Path("modRemoveKweet")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("text/html")
    public Response modRemoveKweet(@QueryParam("userid") int userId,
            @QueryParam("kweetid") int kweetid) {
        if (ks.modRemoveKweet(userId, kweetid)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }



}
