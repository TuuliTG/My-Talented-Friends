/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author tgtuuli
 */
@Configuration
@ComponentScan({ "projekti", "projekti.Controllers", 
    "projekti.domain", "projekti.repositories", "projekti.securityConfiguration", "projekti.service" })
public class TestConfig {
    
}
