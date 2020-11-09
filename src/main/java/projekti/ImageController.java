/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tgtuuli
 */
@Controller
public class ImageController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    @Transactional
    @GetMapping(path="/image/{username}",produces = "image/*")
    @ResponseBody
    public byte[] getContent(@PathVariable String username) {
        User u = this.userService.findByUsername(username);
        FileObject fo = u.getProfilePicture();
        
        return fo.getContent();
    }
    @Transactional
    @PostMapping("/image")
    public String saveImage(@RequestParam("file") MultipartFile file) throws IOException {
       
        User u = getAuthenticatedUser();
        
        FileObject fo = new FileObject();

        fo.setName(file.getOriginalFilename());
        fo.setMediaType(file.getContentType());
        fo.setFileSize(file.getSize());

        fo.setContent(file.getBytes());
        fo.setUser(u);
        fileObjectRepository.save(fo);
        u.setProfilePicture(fo);
        this.userService.save(u);


        return "redirect:/userHomePage/" + u.getUsername(); 
    }
    
    @PostMapping("/deleteimage")
    public String deleteImage() {
        
        User u = getAuthenticatedUser();
        FileObject fo = u.getProfilePicture();
        u.setProfilePicture(null);
        fileObjectRepository.deleteById(fo.getId());
        
        return "redirect:/userHomePage/" + u.getUsername();
    }
    
    @GetMapping("/profilepicture")
    public String changeProfilePicture(Model model){
        User u = getAuthenticatedUser();
        model.addAttribute("user", u);
        return "profilepicture";
    }
    
    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User u = userService.findByUsername(username);
        return u;
    }
}
