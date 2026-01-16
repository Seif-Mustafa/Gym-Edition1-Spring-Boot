package com.gym.edition1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gym.edition1.model.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

  @Query("""
          SELECT g FROM Gym g
          WHERE (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', LOWER(:gymName), '%'))
          AND (:createdBy IS NULL OR g.createdBy = :createdBy)
          AND (:modifiedBy IS NULL OR g.modifiedBy = :modifiedBy)
      """)
  List<Gym> getGymsByFilters(@Param("gymName") String gymName, @Param("createdBy") Long createdBy,
      @Param("modifiedBy") Long modifiedBy);
}
