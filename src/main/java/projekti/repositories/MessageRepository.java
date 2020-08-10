/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.repositories;

/**
 *
 * @author tgtuuli
 */
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByOwnerId(Long id, Pageable pageable);
}
