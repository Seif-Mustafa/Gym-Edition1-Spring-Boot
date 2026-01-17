package com.gym.edition1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import com.gym.edition1.model.MemberAttendance;
import java.util.List;

@Repository
public interface MemberAttendanceRepository extends JpaRepository<MemberAttendance, Long> {
  List<MemberAttendance> findByMemberId(Long memberId);

  List<MemberAttendance> findByGymId(Long gymId);

  @Query(value = """
      SELECT COUNT(*) FROM MemberAttendance ma
      WHERE ma.memberId = :memberId
      AND ma.gymId = :gymId
      AND (FUNCTION('DATE', ma.attendTime) BETWEEN :dateFrom AND :dateTo)
      """)
  Long getMemberAttendedSessionsBetween(@Param("memberId") Long memberId,
      @Param("gymId") Long gymId,
      @Param("dateFrom") LocalDate dateFrom,
      @Param("dateTo") LocalDate dateTo);

  @Query("""
        SELECT ma FROM MemberAttendance ma
        JOIN Member m
        ON m.memberId = ma.memberId
        JOIN Gym g
        ON g.gymId = ma.gymId
        WHERE (:memberId IS NULL OR ma.memberId = :memberId)
        AND (:memberName IS NULL OR LOWER(m.name) LIKE CONCAT('%', :memberName, '%'))
        AND (:gymId IS NULL OR ma.gymId = :gymId)
        AND (:gymName IS NULL OR LOWER(g.gymName) LIKE CONCAT('%', :gymName, '%'))
        AND (:attendType IS NULL OR ma.attendType = :attendType)
        AND (:attendDate IS NULL OR FUNCTION('DATE', ma.attendTime) = :attendDate)
        AND (:createdBy IS NULL OR ma.createdBy = :createdBy)
        AND (:modifiedBy IS NULL OR ma.modifiedBy = :modifiedBy)
      """)
  List<MemberAttendance> getMemberAttendanceWithFilters(
      @Param("memberId") Long memberId,
      @Param("memberName") String memberName,
      @Param("gymId") Long gymId,
      @Param("gymName") String gymName,
      @Param("attendType") String attendType,
      @Param("attendDate") LocalDate attendDate,
      @Param("createdBy") Long createdBy,
      @Param("modifiedBy") Long modifiedBy);
}
