/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.domain;

/**
 *
 * @author tgtuuli
 */
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import projekti.repositories.MessageRepository;

@Entity
@Table(name="users")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User extends AbstractPersistable<Long> {
    
    @NotEmpty
    @Size(max = 10)
    public String firstName;
    @NotEmpty
    @Size(max = 25)
    public String lastName;
    @NotEmpty
    @Size(max = 10)
    public String username;
    @NotEmpty
    
    public String password;
    
    @OneToMany(mappedBy="owner")
    private List<Skill> skills;
    
   

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    
    @ManyToMany
    List<Message> likedMessages;
    
    @ManyToMany
    @JoinTable(name="tbl_friends", 
            joinColumns=@JoinColumn(name="personId"),
            inverseJoinColumns=@JoinColumn(name="friendId"))
    private List<User> friends;
    
    @ManyToMany
    @JoinTable(name="tbl_friends", 
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="personId"))
    private List<User> friendOf;
    
    
    @OneToMany(mappedBy="writer")
    private List<Message> messagesWritten;
   
    
    @OneToMany(mappedBy="requestTo")
    private List<FriendRequest> comingRequests;
    @OneToMany(mappedBy="requestFrom")
    private List<FriendRequest> sentRequests;
    
    @OneToOne
    private FileObject profilePicture;

}