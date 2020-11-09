/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projekti.Skill;

/**
 *
 * @author tgtuuli
 */
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query(value="SELECT s FROM Skill s WHERE owner.id = ?1")
    List<Skill> FindByOwnerId(Long id, Pageable pageable);
    
}
