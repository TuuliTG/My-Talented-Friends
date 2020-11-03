/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class signUpController {
    
    
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    
    
    @PostMapping("/signup")
    public String createANewUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        Boolean userCreated = this.userService.createANewUser(user);
        if(userCreated == true){
            return "redirect:/frontpage";
        }
        else {
            return "usernameNotAvailable";
        }
        
    }
    
    
    
    @GetMapping("/signup")
    public String signUp(@ModelAttribute User user){
        return "signup";
    }
    
    
}
