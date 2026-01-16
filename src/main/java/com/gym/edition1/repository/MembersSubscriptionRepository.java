package com.gym.edition1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.edition1.model.MembersSubscription;

@Repository
public interface MembersSubscriptionRepository extends JpaRepository<MembersSubscription, Long> {

  @Query("""
      SELECT ms FROM MembersSubscription ms 
      WHERE ms.member.memberId = :memberId
      AND ms.member.gymId = :gymId
      AND (SYSDATE BETWEEN ms.dateFrom and ms.dateTo)
      """)
      MembersSubscription getMemberCurrentSubscription(@Param("memberId") Long memberId, @Param("gymId") Long gymId);


  @Query(value = "SELECT ms.* FROM members_subscription ms " +
      "JOIN member m ON ms.member_id = m.member_id " +
      "WHERE m.gym_id = :gymId", nativeQuery = true)
  List<MembersSubscription> findByGymId(Long gymId);

  @Query("SELECT ms FROM MembersSubscription ms JOIN FETCH ms.member m WHERE m.gymId = :gymId")
  List<MembersSubscription> findByGymIdWithMember(Long gymId);

  @Query("SELECT ms FROM MembersSubscription ms JOIN FETCH ms.member")
  List<MembersSubscription> findAllWithMember();

  @Query("SELECT ms FROM MembersSubscription ms JOIN FETCH ms.member WHERE ms.mem_sub_id = :id")
  MembersSubscription findByIdWithMember(Long id);

}
