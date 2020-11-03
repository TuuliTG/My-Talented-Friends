/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

/**
 *
 * @author tgtuuli
 */
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projekti.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    @Query(value="SELECT m FROM Message m WHERE writer.id IN :writers")
    List<Message> findAllByWriterList(@Param("writers") Collection<Long> writers, Pageable pageable);
}
