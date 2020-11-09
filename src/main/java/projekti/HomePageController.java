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
public class HomePageController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/userHomePage")
    public String homepage() {
        User u = getAuthenticatedUser();
        return "redirect:/userHomePage/" + u.getUsername();
    }
    
    @GetMapping("/userHomePage/{username}")
    public String getUserHomePage(@PathVariable String username, Model model){
        boolean isAVisitor = false;
        User u = getAuthenticatedUser();
        String loggedInUsername = u.getUsername();
        if(!loggedInUsername.equals(username)) {
            isAVisitor = true;
            
        }
        
        model.addAttribute("isAVisitor",isAVisitor);
        //System.out.println("username " + username);
        User homepageOwner = this.userService.findByUsername(username);
        //System.out.println("user:" + u);
        List<Skill> skills = this.userService.getSkillsSortedByPraises(homepageOwner);
        
        List<User> allUsers = this.userService.listAll();
        allUsers.remove(homepageOwner);
        model.addAttribute("user", homepageOwner);
        model.addAttribute("allUsers", allUsers);
        List<User> friends = homepageOwner.getFriends();
        
        model.addAttribute("friends", friends);
        model.addAttribute("skills", skills);
        return "userHomePage";
    }
    
    @PostMapping("/usernameNotAvailable")
    public String goBack() {
        return "redirect:/frontpage";
    }
    
    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User u = userService.findByUsername(username);
        return u;
    }
   
}
