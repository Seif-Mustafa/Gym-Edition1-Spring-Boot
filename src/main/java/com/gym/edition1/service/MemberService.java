package com.gym.edition1.service;

import java.util.List;

import com.gym.edition1.dto.request.member.CreateMemberRequest;
import com.gym.edition1.dto.request.member.UpdateMemberRequest;
import com.gym.edition1.model.Member;

public interface MemberService {
  Member createMember(CreateMemberRequest createMemberRequest);

  Member getMemberById(Long memberId);

  Member updateMember(Long memberId, UpdateMemberRequest updateMemberRequest);

  void deleteMemberById(Long memberId);

  List<Member> getMembersWithFilters(
      String gymName,
      Long gymId,
      String memberName,
      String phoneNumber,
      String Gender,
      Long age,
      String isDeleted,
      Long createdBy,
      Long modifiedBy);

}
