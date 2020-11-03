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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        /*if(file.getSize()>5000000){
            System.out.println("file is too large");
            return "redirect:/filetoolarge";
        }
*/
        User u = userService.findByUsername(username);
        
        FileObject fo = new FileObject();

        fo.setName(file.getOriginalFilename());
        fo.setMediaType(file.getContentType());
        fo.setFileSize(file.getSize());

        fo.setContent(file.getBytes());
        fo.setUser(u);
        fileObjectRepository.save(fo);
        u.setProfilePicture(fo);
        this.userService.save(u);


        return "redirect:/userHomePage/" + username; 
    }
    
    @GetMapping("/profilepicture")
    public String changeProfilePicture(){
        return "profilepicture";
    }
}