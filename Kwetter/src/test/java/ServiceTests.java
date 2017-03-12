
import dao.TweetDAO;
import dao.UserDAO;
import domain.Tweet;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import service.TweetService;
import service.UserService;
import util.PasswordHash;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rinke
 */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    UserService US;
    TweetService TS;

    @Mock
    UserDAO ud;
    
    @Mock
    TweetDAO td;

    @Before
    public void setUp() {
        US = new UserService();
        US.setDAO(ud);
        
        TS = new TweetService();
        TS.setDAO(td);
    }

    @Test
    public void TestUserService() {
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));

        ud.save(user);
        ud.save(user2);

        US.allUser();
        verify(ud, Mockito.times(1)).allUser();

        US.getFollowers((long) 2);
        verify(ud, Mockito.times(1)).getUserById((long) 1);
        
        US.getFollowing((long) 1);
        verify(ud, Mockito.times(1)).getUserById((long) 1);
        
        US.getUserById((long) 1);
        verify(ud, Mockito.times(1)).getUserById((long) 1);
    }

    @Test
    public void TestTweetService() {
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));
        
        Tweet tweet2 = new Tweet("TestTweet2", user2);
        Tweet tweet = new Tweet("TestTweet", user);
        td.save(tweet);
        td.save(tweet2);
        
        TS.allTweet();
        verify(td, Mockito.times(1)).allTweet();
        
        TS.getTweetById((long) 1);
        verify(td, Mockito.times(1)).getTweetById((long) 1);
        
        TS.getTweetByUserId((long) 1);
        verify(td, Mockito.times(1)).getTweetByUserId((long) 1);
    }

}
