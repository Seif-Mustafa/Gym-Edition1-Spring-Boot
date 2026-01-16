package com.gym.edition1.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.gym.edition1.dto.MemberAttendanceDto;
import com.gym.edition1.dto.request.MemberAttendRequest;
import com.gym.edition1.mapper.MemberAttendanceMapper;
import com.gym.edition1.model.MemberAttendance;
import com.gym.edition1.model.MembersSubscription;
import com.gym.edition1.repository.GymRepository;
import com.gym.edition1.repository.MemberAttendanceRepository;
import com.gym.edition1.repository.MembersSubscriptionRepository;

import lombok.RequiredArgsConstructor;

import com.gym.edition1.repository.MemberRepository;

import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import com.gym.edition1.model.Member;

@Service
@RequiredArgsConstructor
public class MemberAttendanceServiceImpl {

  private final MemberRepository memberRepository;
  private final GymRepository gymRepository;
  private final MembersSubscriptionRepository membersSubscriptionRepository;
  private final MemberAttendanceRepository memberAttendanceRepository;

  private final MemberAttendanceMapper memberAttendanceMapper;

  public MemberAttendance createAttendance(MemberAttendRequest memberAttendRequest) {

    memberRepository.findById(memberAttendRequest.getMemberId())
        .orElseThrow(() -> new RuntimeException("Member not found"));

    gymRepository.findById(memberAttendRequest.getGymId())
        .orElseThrow(() -> new RuntimeException("Gym not found"));

    MembersSubscription memberSubscription = membersSubscriptionRepository
        .getMemberCurrentSubscription(memberAttendRequest.getMemberId(), memberAttendRequest.getGymId());

    if (memberSubscription == null) {
      throw new RuntimeException("Member have no valid Subscription");
    } else {
      Long numOfAttendedSessions = memberAttendanceRepository.getMemberAttendedSessionsBetween(
          memberAttendRequest.getMemberId(), memberAttendRequest.getGymId(), memberSubscription.getDateFrom(),
          memberSubscription.getDateTo());

          if(numOfAttendedSessions>=memberSubscription.getNumOfSessions()){
            throw new RuntimeException("Member used all available sessions");
          }
    }

    MemberAttendance newAttendance = memberAttendanceRepository
        .save(memberAttendanceMapper.mapToEntity(memberAttendRequest));

    return newAttendance;
  }

  public MemberAttendanceDto getAttendanceById(Long id) {
    MemberAttendance attendance = memberAttendanceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
    return mapToDto(attendance);
  }

  public List<MemberAttendanceDto> getAllAttendances() {
    List<MemberAttendance> attendances = memberAttendanceRepository.findAll();
    return attendances.stream().map(this::mapToDtoWithMemberName).collect(Collectors.toList());
  }

  public List<MemberAttendanceDto> getAttendancesByMemberId(Long memberId) {
    List<MemberAttendance> attendances = memberAttendanceRepository.findByMemberId(memberId);
    return attendances.stream().map(this::mapToDtoWithMemberName).collect(Collectors.toList());
  }

  public List<MemberAttendanceDto> getAttendancesByGymId(Long gymId) {
    List<MemberAttendance> attendances = memberAttendanceRepository.findByGymId(gymId);
    return attendances.stream().map(this::mapToDtoWithMemberName).collect(Collectors.toList());
  }

  public void deleteAttendance(Long id) {
    MemberAttendance attendance = memberAttendanceRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
    memberAttendanceRepository.delete(attendance);
  }

  private MemberAttendanceDto mapToDto(MemberAttendance attendance) {
    return MemberAttendanceDto.builder()
        .attendId(attendance.getAttend_id())
        .gymId(attendance.getGymId())
        .memberId(attendance.getMemberId())
        // .memberName(attendance.getMemberName())
        .attendType(attendance.getAttendType())
        .attendTime(attendance.getAttendTime())
        // Audit fields
        .createdBy(attendance.getCreatedBy())
        .createdOn(attendance.getCreatedOn())
        .modifiedBy(attendance.getModifiedBy())
        .modifiedOn(attendance.getModifiedOn())
        .build();
  }

  private MemberAttendanceDto mapToDtoWithMemberName(MemberAttendance attendance) {
    MemberAttendanceDto dto = mapToDto(attendance);

    // Fetch member name from Member entity
    if (attendance.getMemberId() != null) {
      Member member = memberRepository.findById(attendance.getMemberId())
          .orElse(null);
      if (member != null) {
        dto.setMemberName(member.getName()); // Assuming Member entity has a getName() method
      }
    }

    return dto;
  }
  // private MemberAttendance mapToEntity(MemberAttendanceDto attendanceDto) {
  // return MemberAttendance.builder()
  // .attend_id(attendanceDto.getAttendId())
  // .gymId(attendanceDto.getGymId())
  // .memberId(attendanceDto.getMemberId())
  // // .memberName(attendanceDto.getMemberName())
  // .attendType(attendanceDto.getAttendType())
  // .attendTime(attendanceDto.getAttendTime())
  // .build();
  // }
}