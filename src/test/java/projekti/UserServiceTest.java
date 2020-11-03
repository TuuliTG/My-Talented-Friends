/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author tgtuuli
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testNewUserCreated() throws Exception {
        User u = new User("a", "aa", "aaa", "aaaa");
        userService.createANewUser(u);
        User found = this.userService.findByUsername("aaa");
        assertTrue(found.getFirstName().equals("a"));
    }
    
}
