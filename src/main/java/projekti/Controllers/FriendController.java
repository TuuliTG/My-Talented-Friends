/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.User;
import projekti.service.UserService;

/**
 *
 * @author tgtuuli
 */
@Controller
public class FriendController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/userHomePage/{username}/sendAFriendRequest")
    public String sendAFriendRequest(@PathVariable String username, @RequestParam String friendUsername){
        
        this.userService.sendAFriendRequest(username, friendUsername);
        
        return "redirect:/userHomePage/" + username + "/?requestsent=true";
    }
    
    @PostMapping("/accept/{username}")
    public String acceptFriendRequest(@PathVariable String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String activeUsername = auth.getName();
        
        this.userService.acceptARequest(username, activeUsername);
        return "redirect:/userHomePage/" + activeUsername;
    }
    
    @PostMapping("/reject/{username}")
    public String rejectFriendRequest(@PathVariable String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String activeUsername = auth.getName();
        
        this.userService.reject(username, activeUsername);
        return "redirect:/userHomePage/" + activeUsername;
    }
    
    
}
