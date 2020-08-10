/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.domain;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    private User owner;
    
    private String content;
    //private LocalTime writtenAtTime;
    //private LocalDate writtenAtDate;
    //@Column(name="timestamp") //this is for h2 -> change for postgresql???
    private LocalDateTime writtenAt;

}
