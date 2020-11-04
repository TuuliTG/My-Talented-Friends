/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class FriendRequestTest {
    
    @Test
    public void friendRequestIsMade() {
        FriendRequest fr = new FriendRequest(new User("a", "a", "a", "a"), new User("b", "b", "b", "b"));
        assertEquals("a", fr.requestFrom.firstName);
    }
            
    
}
