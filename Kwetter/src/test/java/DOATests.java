
import dao.GroupDAO;
import dao.TweetDAO;
import dao.UserDAO;
import domain.Group;
import domain.Tweet;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.PasswordHash;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author rinke
 */
@RunWith(MockitoJUnitRunner.class)
public class DOATests {
    @Mock
    UserDAO ud;
    
    @Mock
    TweetDAO td;
    
    @Mock
    GroupDAO gd;
         
    @Test
    public void TestUser(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));
        
        ud.save(user);
        ud.save(user2);
        	
	verify(ud, Mockito.times(1)).save(user);
        verify(ud, Mockito.times(1)).save(user2);
        
        ud.delete(user);
        verify(ud, Mockito.times(1)).delete(user);
        
        ud.allUser();
        verify(ud, Mockito.times(1)).allUser();
        
        ud.getUserById((long)1);
        verify(ud, Mockito.times(1)).getUserById((long)1);
    }
     
    @Test
    public void TestTweet(){
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));
        Tweet tweet2 = new Tweet("TestTweet2", user2);
        Tweet tweet = new Tweet("TestTweet", user);
        
        td.save(tweet);
        td.save(tweet2);
        verify(td, Mockito.times(1)).save(tweet);
        verify(td, Mockito.times(1)).save(tweet2);
        
        td.delete(tweet2);
        verify(td, Mockito.times(1)).delete(tweet2);
        
        td.allTweet();
        verify(td, Mockito.times(1)).allTweet();
        
        td.getTweetById((long)1);
        verify(td, Mockito.times(1)).getTweetById((long)1);
        
        td.getTweetByUserId((long) 1);
        verify(td, Mockito.times(1)).getTweetByUserId((long)1);
    }
    
    @Test
    public void TestGroup(){
        Group adminGroup = new Group("admins");
        Group userGroup = new Group("regulars");
        
        User user = new User("Lorenzo", "Nederland", "www.lorenzosuiker.nl", "2manybits", PasswordHash.stringToHash("Lorenzo"));
        User user2 = new User("Fatih", "Eindhoven", "www.google.nl", "hoi", PasswordHash.stringToHash("Fatih"));
        
        user.addGroup(userGroup);
        user2.addGroup(adminGroup);
        
        gd.save(adminGroup);
        gd.save(userGroup);
        verify(gd, Mockito.times(1)).save(userGroup);
        verify(gd, Mockito.times(1)).save(adminGroup);
        
        gd.delete(userGroup);
        verify(gd, Mockito.times(1)).delete(userGroup);
    }
    

    
}
