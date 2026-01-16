package com.gym.edition1.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAttendanceDto {
  private Long attendId;
  private Long gymId;
  private Long memberId;
  private String memberName;
  private String attendType;
  private LocalDateTime attendTime;
  // Audit fields
  private Long createdBy;
  private java.time.LocalDateTime createdOn;
  private Long modifiedBy;
  private java.time.LocalDateTime modifiedOn;
}
