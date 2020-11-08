
package projekti;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * 
 */
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class FriendRequest extends AbstractPersistable<Long> {
    
    @ManyToOne
    public User requestFrom;
    @ManyToOne
    public User requestTo;
    
}
