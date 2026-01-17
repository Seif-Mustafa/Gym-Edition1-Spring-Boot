package com.gym.edition1.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.dto.request.memberattendance.CreateMemberAttendRequest;
import com.gym.edition1.mapper.MemberAttendanceMapper;
import com.gym.edition1.model.MemberAttendance;
import com.gym.edition1.model.MemberSubscription;
import com.gym.edition1.repository.MemberAttendanceRepository;
import com.gym.edition1.service.GymService;
import com.gym.edition1.service.MemberAttendanceService;
import com.gym.edition1.service.MemberService;
import com.gym.edition1.service.MemberSubscriptionService;

import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MemberAttendanceServiceImpl implements MemberAttendanceService {

  private final MemberAttendanceRepository memberAttendanceRepository;
  private final MemberService memberService;
  private final GymService gymService;
  private final MemberSubscriptionService memberSubscriptionService;

  private final MemberAttendanceMapper memberAttendanceMapper;

  @Override
  public MemberAttendance createAttendance(CreateMemberAttendRequest memberAttendRequest) {

    memberService.getMemberById(memberAttendRequest.getMemberId());

    gymService.getGymById(memberAttendRequest.getGymId());

    MemberSubscription memberSubscription = memberSubscriptionService
        .getMemberCurrentSubscription(memberAttendRequest.getMemberId(), memberAttendRequest.getGymId());

    if (memberSubscription == null) {
      throw new RuntimeException("Member Have No Valid Subscription");
    } else {
      Long numOfAttendedSessions = memberAttendanceRepository.getMemberAttendedSessionsBetween(
          memberAttendRequest.getMemberId(), memberAttendRequest.getGymId(), memberSubscription.getDateFrom(),
          memberSubscription.getDateTo());

      if (numOfAttendedSessions >= memberSubscription.getNumOfSessions()) {
        throw new RuntimeException("Member Used All Available Sessions");
      }
    }

    MemberAttendance newAttendance = memberAttendanceRepository
        .save(memberAttendanceMapper.mapToEntity(memberAttendRequest));

    return newAttendance;
  }

  @Override
  public MemberAttendance getMemberAttendanceById(Long attendId) {
    return memberAttendanceRepository.findById(attendId)
        .orElseThrow(() -> new RuntimeException("Member Attendance not found"));
  }

  @Override
  public List<MemberAttendance> getMemberAttendanceWithFilters(
      Long memberId,
      String memberName,
      Long gymId,
      String gymName,
      String attendType,
      LocalDate attendDate,
      Long createdBy,
      Long modifiedBy) {
    return memberAttendanceRepository.getMemberAttendanceWithFilters(memberId, memberName, gymId, gymName, attendType,
        attendDate, createdBy, modifiedBy);
  }

}