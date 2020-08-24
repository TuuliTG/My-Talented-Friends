/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author tgtuuli
 */
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Skill extends AbstractPersistable<Long>{
    
  
    private String skillDescription;
    
    private long likes;
    @ManyToOne
    private User owner;

    public Skill(String skillDescription, User owner) {
        this.skillDescription = skillDescription;
        this.owner = owner;
        this.likes = 0;
    }
    
    
}
