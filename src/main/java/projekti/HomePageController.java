/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return "redirect:/userHomePage/" + username;
    }
    
    
    
    @GetMapping("/userHomePage/{username}")
    public String getUserHomePage(@PathVariable String username, Model model){
        boolean isAVisitor = false;
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();
        if(!loggedInUsername.equals(username)) {
            isAVisitor = true;
            
        }
        
        model.addAttribute("isAVisitor",isAVisitor);
        //System.out.println("username " + username);
        User u = this.userService.findByUsername(username);
        //System.out.println("user:" + u);
        List<Skill> skills = this.userService.getSkillsSortedByPraises(u);
        
        List<User> allUsers = this.userService.listAll();
        allUsers.remove(u);
        model.addAttribute("user", u);
        model.addAttribute("allUsers", allUsers);
        List<User> friends = u.getFriends();
        //if(friends.isEmpty()){
          //  friends = null;
        //}
        //System.out.println("friends = " + friends);
        model.addAttribute("friends", friends);
        model.addAttribute("skills", skills);
        return "userHomePage";
    }
    
    @PostMapping("/usernameNotAvailable")
    public String goBack() {
        return "redirect:/frontpage";
    }
    
    
   
}
