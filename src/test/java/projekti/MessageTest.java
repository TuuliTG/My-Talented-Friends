/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class MessageTest {
    
    @Test
    public void messageIsCreated() {
        Message m = new Message(new User("a", "a", "a", "a"), "abcabc", LocalDateTime.now());
        assertEquals("abcabc", m.getContent());
    }
    
    @Test
    public void newMessageHasZeroLikes() {
        Message m = new Message(new User("a", "a", "a", "a"), "abcabc", LocalDateTime.now());
        assertEquals(0, m.getLikes());

    }
    
}
