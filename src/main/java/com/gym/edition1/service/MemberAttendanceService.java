package com.gym.edition1.service;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.dto.request.memberattendance.CreateMemberAttendRequest;
import com.gym.edition1.model.MemberAttendance;

public interface MemberAttendanceService {

  MemberAttendance createAttendance(CreateMemberAttendRequest memberAttendRequest);

  MemberAttendance getMemberAttendanceById(Long attendId);

  List<MemberAttendance> getMemberAttendanceWithFilters(
      Long memberId,
      String memberName,
      Long gymId,
      String gymName,
      String attendType,
      LocalDate attendDate,
      Long createdBy,
      Long modifiedBy);
}
