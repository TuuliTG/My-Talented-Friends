/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tgtuuli
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractPersistable<Long>{
    @ManyToOne   
    private User writer;
    
    private String content;
    
    private long likes;
    
    @ManyToMany
    private List<User> usersWhoLiked;
    
    @Column(name="timestamp") //this is for h2 -> change for postgresql???
    private LocalDateTime writtenAt;
    
    @ManyToOne
    private Message message;
}
