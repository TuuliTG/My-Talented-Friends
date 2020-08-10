/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    
    
    public void saveMessage(String content, String ownerUsername, String writerUsername, LocalDateTime writtenAt){
        if (content != null && !content.trim().isEmpty()) {
            Message msg = new Message();
            msg.setContent(content.trim());

            
            User owner = this.userRepository.findByUsername(ownerUsername);

            msg.setWriter(userRepository.findByUsername(writerUsername));
            msg.setOwner(owner);
            //msg.setWrittenAtDate(writtenAtDate);
            //msg.setWrittenAtTime(writtenAtTime);
            msg.setWrittenAt(writtenAt);
            //System.out.println("written at " + writtenAt);
            messageRepository.save(msg);
            
            
        }
    }
    
    public List<Message> listAllWrittenBy(String username) {
        return this.userRepository.findByUsername(username).getMessagesWritten();
    }
    
    public List<Message> listAllOwnedBy(String username) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("writtenAt").descending());
        User u = this.userRepository.findByUsername(username);
        return this.messageRepository.findByOwnerId(u.getId(), pageable);
    }        
    
}
