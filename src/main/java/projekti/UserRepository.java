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

import java.util.List;
import projekti.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
    
    @Query("select u from User u WHERE lower(u.firstName) like %:keyword%"
            + " or lower(u.lastName) like %:keyword% or lower(u.username) like %:keyword%")
    List<User> findByKeyword(@Param("keyword") String keyword);
    
    @Query("DELETE FROM User u WHERE u.username = ?1")
    User deleteByUsername(String username);
}
