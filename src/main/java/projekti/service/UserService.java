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
import projekti.domain.FriendRequest;
import projekti.domain.User;
import projekti.repositories.FriendRequestRepository;
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
    
    @Autowired
    private FriendRequestRepository frp;
    
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
    
    public void sendAFriendRequest(String usernameFrom, String usernameTo) {
        User u = this.userRepo.findByUsername(usernameFrom);
        User f = this.userRepo.findByUsername(usernameTo);
        
        boolean areFriends = this.areFriends(u, f);
        
        if(areFriends == false) {
            FriendRequest r = new FriendRequest(u, f);
            this.frp.save(r);
        }
        
    }
    
    @Transactional
    public void acceptARequest(String usernameFrom, String usernameTo){
        User u = this.userRepo.findByUsername(usernameFrom);
        User f = this.userRepo.findByUsername(usernameTo);
        
        boolean areFriends = this.areFriends(u, f);
        
        if(areFriends == false) {
            u.getFriends().add(f);
            u.getFriendOf().add(f);
        }
        System.out.println("accept a request from id " + u.getId());
        this.frp.deleteByRequestFromAndRequestTo(u.getId(), f.getId());
    }
    
    public void reject(String usernameFrom, String usernameTo){
        User u = this.userRepo.findByUsername(usernameFrom);
        User f = this.userRepo.findByUsername(usernameTo);
        this.frp.deleteByRequestFromAndRequestTo(u.getId(), f.getId());
    }
    
    private boolean areFriends(User u, User f) {
        boolean areFriends = false;
        if(u.getFriends().contains(f)){
            areFriends=true;
            System.out.println("already friends");
            
        }
        return areFriends;
    }
    
    public void save(User u) {
        this.userRepo.save(u);
    }
    
    
}
