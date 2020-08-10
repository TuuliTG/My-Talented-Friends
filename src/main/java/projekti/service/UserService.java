/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.domain.User;
import projekti.repositories.UserRepository;

/**
 *
 * @author tgtuuli
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<User> listAll() {
        return userRepo.findAll();
    }
    
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    public Boolean createANewUser(String firstname, String lastname, String username,String password) {
        if(userRepo.findByUsername(username)==null){
            User u = new User(firstname, lastname, username,this.passwordEncoder.encode(password));
            this.userRepo.save(u);
            return true;
        } else {
            return false;
        }
     
    }
    
    @Transactional
    public void addAFriend(String username, String friendUsername){
        User u = this.userRepo.findByUsername(username);
        User f = this.userRepo.findByUsername(friendUsername);
        
        Long id = u.getId();
        boolean areFriends = false;
        if(u.getFriends().contains(f)){
            areFriends=true;
            System.out.println("already friends");
            
        }
        if(areFriends == false) {
            u.getFriends().add(f);
            u.getFriendOf().add(f);
        }
    }
    
    
}
