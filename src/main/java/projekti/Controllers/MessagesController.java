/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.Controllers;

import static java.lang.Long.parseLong;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Message;
import projekti.domain.User;
import projekti.repositories.MessageRepository;
import projekti.repositories.UserRepository;
import projekti.service.MessageService;

/**
 *
 * @author tgtuuli
 */
@Controller
public class MessagesController {
    
    @Autowired
    private MessageService messageService;
    
    /*
    @PostMapping("/userHomePage/{username}/messages")
    public String add(@RequestParam String content,
            @PathVariable String username) {
        //LocalTime writtenAtTime = LocalTime.now();
        //LocalDate writtenAtDate = LocalDate.now();
        LocalDateTime writtenAt = LocalDateTime.now();
        
        //writtenAtDate.format(DateTimeFormatter.ISO_DATE);
        writtenAt.format(DateTimeFormatter.ISO_DATE);
        
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String writerUsername = auth.getName();
        //System.out.println("written at " + written + " at " + writtenAtTime );
        messageService.saveMessage(content, writerUsername, writtenAt);
        return "redirect:/userHomePage/" + username;
    }
    */
    
    /*@PostMapping("/userHomePage/{username}/{messageid}")
    public String like(@PathVariable String username, @PathVariable String messageid){
        
        this.messageService.like(parseLong(messageid));
        return "redirect:/userHomePage/" + username;
    }
    */
    @GetMapping("/messageboard")
    public String messages(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        
        model.addAttribute("messages", this.messageService.listAllOwnedByFriendsAndUser(username));
        return "messageboard";
    }
    @PostMapping("/messageboard/like/{messageid}")
    public String likeMessage(@PathVariable String messageid){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        this.messageService.like(parseLong(messageid), username);
        return "redirect:/messageboard";
    }
    
    @PostMapping("/messageboard/likecomment/{commentid}")
    public String likeComment(@PathVariable String commentid){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        this.messageService.likeComment(parseLong(commentid), username);
        return "redirect:/messageboard";
    }
    
    @PostMapping("/messageboard/post")
    public String post(@RequestParam String content){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String writerUsername = auth.getName();
        LocalDateTime writtenAt = LocalDateTime.now();
        writtenAt.format(DateTimeFormatter.ISO_DATE);
        
        this.messageService.saveMessage(content, writerUsername, writtenAt);
        return "redirect:/messageboard";
        
    }
    
    @PostMapping("/messageboard/comment/{messageid}")
    public String comment(@PathVariable String messageid, @RequestParam String content){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String writerUsername = auth.getName();
        LocalDateTime writtenAt = LocalDateTime.now();
        writtenAt.format(DateTimeFormatter.ISO_DATE);
        
        this.messageService.comment(parseLong(messageid), writerUsername, content, writtenAt);
        return "redirect:/messageboard";
    }
    
    
}
