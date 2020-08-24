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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projekti.domain.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long>{
    @Modifying
    @Query("delete from FriendRequest fr where fr.requestFrom.id =:requestFrom AND fr.requestTo.id=:requestTo")
    void deleteByRequestFromAndRequestTo(@Param("requestFrom") long requestFrom, @Param("requestTo") long requestTo);
    
    @Query("Select fr.requestTo.id from FriendRequest fr where fr.requestFrom.id =:requestFrom")
    List<Long> findSentRequests(@Param("requestFrom") long requestFrom);
}
