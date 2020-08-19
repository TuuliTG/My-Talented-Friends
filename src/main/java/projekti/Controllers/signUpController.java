/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.Controllers;

/**
 *
 * @author tgtuuli
 */
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import projekti.domain.User;
import projekti.service.UserService;

@Controller
public class signUpController {
    
    
    //@Autowired
    //private FileObjectRepository fileObjectRepo;
    
    
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/signup")
    public String createANewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String password) {
        
        Boolean userCreated = this.userService.createANewUser(firstName, lastName, username, password);
        if(userCreated == true){
            return "redirect:/frontpage";
        }
        else {
            return "usernameNotAvailable";
        }
        
    }
    
    
    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }
    /*
    
    
    @GetMapping(path="/userHomePage/{username}", produces = "image/jpg")
    @ResponseBody
    public byte[] getImage(@PathVariable String username) {
        System.out.println("haetaan kuva");
        FileObject fo = userRepo.findByUsername(username).getProfilePictures().get(0);
        System.out.println("näytetään kuva " + fo.getName());
        return fo.getContent();
    }
    */
    
}
