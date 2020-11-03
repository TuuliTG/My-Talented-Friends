/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tgtuuli
 */
@Controller
public class UserController {
    
    @Autowired 
    private UserService userService;
    
    
    @GetMapping("/users")
    public String userpage(Model model, String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = this.userService.findByUsername(username);
        
        List<User> friends = user.getFriends();
        List<Long> sentRequests = this.userService.sentFriendRequestsTo(user.getId());
        
        if(keyword != null) {
            
            model.addAttribute("users", this.userService.findByKeyword(keyword.toLowerCase()));
        } else {
            List<User> users = this.userService.listAll();
            users.remove(user);
            model.addAttribute("users", users);
        }
        model.addAttribute("friends", friends);
        model.addAttribute("sentrequests", sentRequests);
        return "users";
    }
    @PostMapping("/description")
    public String addADescription(@RequestParam String description){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        this.userService.addADescription(description, username);
        return "redirect:/userHomePage/" + username;
    }
    
    @GetMapping("/description")
    public String description(){
        return "description";
    }
}
