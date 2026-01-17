package com.gym.edition1.dto.request.memberattendance;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberAttendRequest {
  private Long gymId;
  private Long memberId;
  private String attendType;
  private LocalDateTime attendTime;
  private Long createdBy;
}
