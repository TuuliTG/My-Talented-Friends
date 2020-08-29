/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.domain.FriendRequest;
import projekti.domain.Skill;
import projekti.domain.User;
import projekti.repositories.FriendRequestRepository;
import projekti.repositories.SkillRepository;
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
    
    @Autowired
    private SkillRepository skillRepository;
    
    public List<User> listAll() {
        return userRepo.findAll();
    }
    
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    public Boolean createANewUser(User u) {
        if(userRepo.findByUsername(u.getUsername())==null){
            u.setPassword(passwordEncoder.encode(u.getPassword()));
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
    
    public boolean areFriends(User u, User f) {
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
    
    public List<User> findByKeyword(String keyword) {
        return this.userRepo.findByKeyword(keyword);
    }
    
    public void deleteFromFriends(String username, String friendUsername){
        User u = this.userRepo.findByUsername(username);
        User friend = this.userRepo.findByUsername(friendUsername);
        List<User> friends = u.getFriends();
        List<User> friendOf = u.getFriendOf();
        friends.remove(friend);
        friendOf.remove(friend);
        u.setFriends(friends);
        u.setFriendOf(friendOf);
        this.userRepo.save(u);
    }
    
    public List<Long> sentFriendRequestsTo(Long usernameFrom) {
        return this.frp.findSentRequests(usernameFrom);
    }
    
    public void addASkill(String skillDescription, String username){
        if(skillDescription.length() < 50 && !skillDescription.isEmpty()) {
            User u = this.userRepo.findByUsername(username);
            Skill skill = new Skill(skillDescription, u);
            this.skillRepository.save(skill);
        }
        
    }
    
    public void addADescription(String description, String username){
        if(description.length() <= 200 && !description.isEmpty()) {
            User u = this.userRepo.findByUsername(username);
            u.setDescription(description);
            this.userRepo.save(u);
        }
    }
    
    public List<Skill> getSkillsSortedByPraises(User u){
        Pageable pageable = PageRequest.of(0, 25, Sort.by("likes").descending());
        return skillRepository.FindByOwnerId(u.getId(), pageable);
    }
    
    
}
