/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.time.LocalDateTime;
import java.util.List;
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
public class MessageServiceTest {
    
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    
    @Test
    public void MessageIsCreated() {
        userService.createANewUser(new User("Maija", "Meikäläinen", "Maikku", "123"));
        messageService.saveMessage("Hello World", "Maikku", LocalDateTime.now());
        
        List<Message> lista = messageService.listAll();
        assertEquals(1, lista.size());
        assertEquals("Hello world", lista.get(0).getContent());
        
    }
    
 
}
