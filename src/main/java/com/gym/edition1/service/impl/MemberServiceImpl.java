package com.gym.edition1.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import com.gym.edition1.dto.request.member.CreateMemberRequest;
import com.gym.edition1.dto.request.member.UpdateMemberRequest;
import com.gym.edition1.mapper.MemberMapper;
import com.gym.edition1.model.Member;
import com.gym.edition1.repository.MemberRepository;
import com.gym.edition1.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final MemberMapper memberMapper;

  @Override
  public Member createMember(CreateMemberRequest createMemberRequest) {
    Member saved = memberRepository.save(memberMapper.toEntity(createMemberRequest));
    return saved;
  }

  @Override
  public Member getMemberById(Long memberId) {
    Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
    return member;
  }

  @Override
  public Member updateMember(Long memberId, UpdateMemberRequest updadMemberRequest) {
    Member existingMember = getMemberById(memberId);
    Member updatedMember = memberRepository.save(memberMapper.toEntity(existingMember, updadMemberRequest));
    return updatedMember;
  }

  @Override
  public void deleteMemberById(Long memberId) {
    Member existingMember = getMemberById(memberId);
    existingMember.setIsDeleted("Y");
    memberRepository.save(existingMember);
  }

  public List<Member> getMembersWithFilters(
      String gymName,
      Long gymId,
      String memberName,
      String phoneNumber,
      String gender,
      Long age, String isDeleted,
      Long createdBy,
      Long modifiedBy) {
    return memberRepository.getMembersWithFilters(gymName, gymId, memberName, phoneNumber, gender, age, isDeleted,
        createdBy,
        modifiedBy);
  }

}