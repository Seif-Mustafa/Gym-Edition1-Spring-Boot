package com.gym.edition1.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.member.CreateMemberRequest;
import com.gym.edition1.dto.request.member.UpdateMemberRequest;
import com.gym.edition1.model.Member;

@Component
public class MemberMapper {
  public Member toEntity(CreateMemberRequest createMemberRequest) {
    Member member = new Member();

    member.setGymId(createMemberRequest.getGymId());
    member.setName(createMemberRequest.getName());
    member.setPhoneNumber(createMemberRequest.getPhoneNumber());
    member.setGender(createMemberRequest.getGender());
    member.setAge(createMemberRequest.getAge());
    member.setIsDeleted("N");
    member.setCreatedBy(createMemberRequest.getCreatedBy());
    member.setCreatedOn(LocalDateTime.now());
    return member;
  }

  public Member toEntity(Member existingMember, UpdateMemberRequest updateMemberRequest) {

    if (updateMemberRequest.getGymId() != null) {
      existingMember.setGymId(updateMemberRequest.getGymId());
    }

    if (updateMemberRequest.getName() != null) {
      existingMember.setName(updateMemberRequest.getName());
    }

    if (updateMemberRequest.getPhoneNumber() != null) {
      existingMember.setPhoneNumber(updateMemberRequest.getPhoneNumber());
    }

    if (updateMemberRequest.getGender() != null) {
      existingMember.setGender(updateMemberRequest.getGender());
    }

    if (updateMemberRequest.getAge() != null) {
      existingMember.setAge(updateMemberRequest.getAge());
    }

    if (updateMemberRequest.getIsDeleted() != null) {
      existingMember.setIsDeleted(updateMemberRequest.getIsDeleted());
    }

    existingMember.setModifiedBy(updateMemberRequest.getModifiedBy());
    existingMember.setModifiedOn(LocalDateTime.now());

    return existingMember;
  }

}
