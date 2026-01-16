package com.gym.edition1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.edition1.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  @Query("""
        SELECT s FROM Subscription s
        JOIN Gym g
        ON s.gymId = g.gymId
        WHERE (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', LOWER(:gymName), '%'))
        AND (:gymId IS NULL OR s.gymId = :gymId)
        AND (:subscriptionName IS NULL OR LOWER(s.subscriptionName) LIKE CONCAT('%', LOWER(:subscriptionName), '%'))
        AND (:createdBy IS NULL OR s.createdBy = :createdBy)
        AND (:modifiedBy IS NULL OR s.modifiedBy = :modifiedBy)
      """)
  List<Subscription> getSubscriptionsWithFilters(
      @Param("gymName") String gymName,
      @Param("gymId") Long gymId,
      @Param("subscriptionName") String subscriptionName,
      @Param("createdBy") Long createdBy,
      @Param("modifiedBy") Long modifiedBy);
}
