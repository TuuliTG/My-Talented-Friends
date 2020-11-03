/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author tgtuuli
 */

@Controller
public class FriendController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/friendrequest/{username}")
    public String sendAFriendRequest(@PathVariable String username){
        String activeUsername = this.getActiveUser();
        this.userService.sendAFriendRequest(activeUsername, username);
        
        return "redirect:/users";
    }
    
    @PostMapping("/accept/{username}")
    public String acceptFriendRequest(@PathVariable String username) {
        String activeUsername = this.getActiveUser();
        
        this.userService.acceptARequest(username, activeUsername);
        return "redirect:/userHomePage/" + activeUsername;
    }
    
    @PostMapping("/reject/{username}")
    public String rejectFriendRequest(@PathVariable String username) {
        String activeUsername = this.getActiveUser();
        
        this.userService.reject(username, activeUsername);
        return "redirect:/userHomePage/" + activeUsername;
    }
    
    @PostMapping("/delete/{username}")
    public String deleteFromFriends(@PathVariable String username){
        String activeUsername = this.getActiveUser();
        this.userService.deleteFromFriends(activeUsername, username);
        return "redirect:/users";
    }
    
    @GetMapping("{username}/friends")
    public String seeFriends(@PathVariable String username, Model model){
        User u = this.userService.findByUsername(username);
        List<Long> sentRequests = this.userService.sentFriendRequestsTo(u.getId());
        List<User> friends = u.getFriends();
        model.addAttribute("friends", friends);
        model.addAttribute("sentrequests", sentRequests);
        return "friends";
    
    }
    
    private String getActiveUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String activeUsername = auth.getName();
        return activeUsername;
    }
    
    
}
