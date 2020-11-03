/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.fluentlenium.adapter.junit.FluentTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


/**
 *
 * @author tgtuuli
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("default")
public class Testi1 extends FluentTest {
    
    
    
@LocalServerPort
    private Integer port;
    
    @Test
    public void loginPageTest() {

        goTo("http://localhost:" + port + "/login");

        assertThat(pageSource()).contains("Username");
        assertThat(pageSource()).contains("Password");
    }
    
    
    
    
}
