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
public class UserTest {
    
    @Test
    public void UserIsCreated() {
        User u = new User("a", "ab", "abc", "abcabc");
        assertEquals("a", u.firstName);
        assertEquals("ab", u.lastName);
        assertEquals("abc", u.getUsername());
        
    }
    
    
}
