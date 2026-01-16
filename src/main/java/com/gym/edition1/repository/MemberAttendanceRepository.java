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

  @Query(value="""
      SELECT COUNT(*) FROM member_attendance ma
      WHERE ma.member_id = :memberId
      AND ma.gym_id = :gymId
      AND (ma.attend_time BETWEEN :dateFrom AND :dateTo)
      """,nativeQuery=true)
  Long getMemberAttendedSessionsBetween(@Param("memberId") Long memberId,
      @Param("gymId") Long gymId,
      @Param("dateFrom") LocalDate dateFrom,
      @Param("dateTo") LocalDate dateTo);
}
