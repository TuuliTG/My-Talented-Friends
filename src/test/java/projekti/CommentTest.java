/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.context.ActiveProfiles;


/**
 *
 * @author tgtuuli
 */

@ActiveProfiles("test")
public class CommentTest {
    
    @Test
    public void commentIsCreated() {
        Comment c = new Comment();
        User u = new User("a", "a", "a", "abc");
        c.setWriter(u);
        c.setContent("abcabc");
        assertNotNull(c);
        assertEquals(u, c.getWriter());
        assertEquals("abcabc", c.getContent());
    }
}
