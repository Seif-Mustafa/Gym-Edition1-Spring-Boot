package com.gym.edition1.repository;

import com.gym.edition1.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query("""
        SELECT m FROM Member m
        JOIN Gym g
        ON m.gymId = g.gymId
        WHERE (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', LOWER(:gymName), '%'))
        AND (:gymId IS NULL OR g.gymId = :gymId)
        AND (:memberName IS NULL OR LOWER(m.name) LIKE CONCAT('%', LOWER(:memberName), '%'))
        AND (:phoneNumber IS NULL OR LOWER(m.phoneNumber) LIKE CONCAT('%', LOWER(:phoneNumber), '%'))
        AND (:gender IS NULL OR m.gender = :gender)
        AND (:age IS NULL OR m.age = :age)
        AND (:isDeleted IS NULL OR m.isDeleted = :isDeleted)
        AND (:createdBy IS NULL OR m.createdBy = :createdBy)
        AND (:modifiedBy IS NULL OR m.modifiedBy = :modifiedBy)
      """)
  List<Member> getMembersWithFilters(
      @Param("gymName") String gymName,
      @Param("gymId") Long gymId,
      @Param("memberName") String memberName,
      @Param("phoneNumber") String phoneNumber,
      @Param("gender") String gender,
      @Param("age") Long age,
      @Param("isDeleted") String isDeleted,
      @Param("createdBy") Long createdBy,
      @Param("modifiedBy") Long modifiedBy);
}