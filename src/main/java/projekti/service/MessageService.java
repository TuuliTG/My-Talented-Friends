/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projekti.domain.Message;
import projekti.domain.User;
import projekti.repositories.MessageRepository;
import projekti.repositories.UserRepository;

/**
 *
 * @author tgtuuli
 */
@Service
public class MessageService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private MessageRepository messageRepository;
    
    
    public void saveMessage(String content, String writerUsername, LocalDateTime writtenAt){
        if (content != null && !content.trim().isEmpty()) {
            Message msg = new Message();
            msg.setContent(content.trim());

            msg.setWriter(userRepository.findByUsername(writerUsername));
            msg.setWrittenAt(writtenAt);
            messageRepository.save(msg);
            
        }
    }
    
    public List<Message> listAll() {
        return this.messageRepository.findAll();
    }
    
    public List<Message> listAllWrittenBy(String username) {
        return this.userRepository.findByUsername(username).getMessagesWritten();
    }
    /*
    public List<Message> listAllOwnedBy(String username) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("writtenAt").descending());
        User u = this.userRepository.findByUsername(username);
        return this.messageRepository.findByOwnerId(u.getId(), pageable);
    }   
    */
    public List<Message> listAllOwnedByFriendsAndUser(String username) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("writtenAt").descending());
        
        User u = this.userRepository.findByUsername(username);
        List<Long> ids = new ArrayList<>();
        for(User friend : u.getFriends()) {
            ids.add(friend.getId());
        }
        ids.add(u.getId());
        Collection writers = ids;
        return this.messageRepository.findAllByWriterList(writers, pageable);
    }
    
    public void like(Long messageId, String user) {
        Message m = this.messageRepository.getOne(messageId);
        User u = this.userRepository.findByUsername(user);
        boolean liked = false;
        if(m.getLikes()> 0) {
            List<User> users = m.getUsersWhoLiked();
            for(User liker : users) {
                if (liker.getId().equals(u.getId())) {
                    System.out.println("already liked");
                    liked= true;
                }                    
            }
        }
        if (liked == false){
            
            m.getUsersWhoLiked().add(u);
            m.setLikes(m.getLikes()+ 1);
        }
        messageRepository.save(m);
    }
    
    
    
    
    
}
