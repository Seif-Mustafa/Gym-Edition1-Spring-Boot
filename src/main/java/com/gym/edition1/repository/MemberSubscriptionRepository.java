package com.gym.edition1.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.edition1.model.MemberSubscription;

@Repository
public interface MemberSubscriptionRepository extends JpaRepository<MemberSubscription, Long> {

  @Query("""
      SELECT ms FROM MemberSubscription ms
      WHERE ms.memberId = :memberId
      AND ms.gymId = :gymId
      AND (SYSDATE BETWEEN ms.dateFrom and ms.dateTo)
      """)
  MemberSubscription getMemberCurrentSubscription(@Param("memberId") Long memberId, @Param("gymId") Long gymId);

  @Query("""
      SELECT ms FROM MemberSubscription ms
      JOIN Gym g
      ON g.gymId = ms.gymId
      JOIN Member m
      ON m.memberId = ms.memberId
      WHERE (:gymId IS NULL OR ms.gymId = :gymId)
      AND (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', LOWER(:gymName), '%'))
      AND (:memberName IS NULL OR LOWER(m.name) LIKE CONCAT('%', LOWER(:memberName), '%'))
      AND (:receiptNumber IS NULL OR LOWER(ms.receiptNumber) LIKE CONCAT('%', LOWER(:receiptNumber), '%'))
      AND (:dateFrom IS NULL OR :dateTo IS NULL 
           OR (
              ms.dateFrom <= :dateTo AND ms.dateTo >= :dateFrom
              )
          )
      AND (:createdBy IS NULL OR ms.createdBy = :createdBy)
      AND (:modifiedBy IS NULL OR ms.modifiedBy = :modifiedBy)  
    """)
  List<MemberSubscription> getMemberSubscriptionsWithFilters(
      @Param("gymId") Long gymId,
      @Param("gymName") String gymName,
      @Param("memberName") String memberName,
      @Param("receiptNumber") String receiptNumber,
      @Param("dateFrom") LocalDate dateFrom, // yyyy-MM-dd
      @Param("dateTo") LocalDate dateTo, // yyyy-MM-dd
      @Param("createdBy") Long createdBy,
      @Param("modifiedBy") Long modifiedBy);

  // @Query(value = "SELECT ms.* FROM member_subscription ms " +
  // "JOIN member m ON ms.member_id = m.member_id " +
  // "WHERE m.gym_id = :gymId", nativeQuery = true)
  // List<MemberSubscription> findByGymId(Long gymId);

  // @Query("SELECT ms FROM MemberSubscription ms JOIN FETCH ms.member m WHERE
  // m.gymId = :gymId")
  // List<MemberSubscription> findByGymIdWithMember(Long gymId);

  // @Query("SELECT ms FROM MemberSubscription ms JOIN FETCH ms.member")
  // List<MemberSubscription> findAllWithMember();

  // @Query("SELECT ms FROM MemberSubscription ms JOIN FETCH ms.member WHERE
  // ms.mem_sub_id = :id")
  // MemberSubscription findByIdWithMember(Long id);

}
