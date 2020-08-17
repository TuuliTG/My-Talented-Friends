 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message extends AbstractPersistable<Long> {

    @ManyToOne   
    private User writer;
    
    private String content;
    
    private long likes;
    @ManyToMany
    private List<User> usersWhoLiked;
    
    @Column(name="timestamp") //this is for h2 -> change for postgresql???
    private LocalDateTime writtenAt;
    
    /*
    @OneToMany
    List<Message> comments;
*/
    public Message(User writer, String content, LocalDateTime writtenAt) {
        this.writer = writer;
        this.content = content;
        this.likes = 0;
        this.writtenAt = writtenAt;
    }

    

}
