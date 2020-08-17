/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.Controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.service.UserService;

/**
 *
 * @author tgtuuli
 */
@Controller
public class FrontPageController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("*")
    public String frontpage() {
        return "/etusivu";
    }
    @GetMapping("/frontpage")
    public String showUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        boolean isLoggedIn = auth.isAuthenticated();
        if(auth.getName().equals("anonymousUser")) {
            isLoggedIn = false;
        }

        
        //System.out.println("isloggedIn " + isLoggedIn);
        model.addAttribute("users", this.userService.listAll());
        if(!isLoggedIn){
            
            model.addAttribute("loggedIn", false);
        } else {
            
            String username = auth.getName();
            model.addAttribute("loggedIn", true);
            model.addAttribute("currentUser", username);
        }
        
        
        return "frontpage";
    }
    
    
}
