package com.gym.edition1.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.gym.edition1.dto.request.MemberAttendRequest;
import com.gym.edition1.model.MemberAttendance;

@Component
public class MemberAttendanceMapper {
  public MemberAttendance mapToEntity(MemberAttendRequest memberAttendRequest){
    MemberAttendance memberAttendance = new MemberAttendance();

    memberAttendance.setGymId(memberAttendRequest.getGymId());
    memberAttendance.setMemberId(memberAttendRequest.getMemberId());
    memberAttendance.setAttendTime(memberAttendRequest.getAttendTime());
    memberAttendance.setAttendType(memberAttendRequest.getAttendType());
    memberAttendance.setCreatedBy(memberAttendRequest.getCreatedBy());
    memberAttendance.setCreatedOn( LocalDateTime.now());

    return memberAttendance;

  }

}
