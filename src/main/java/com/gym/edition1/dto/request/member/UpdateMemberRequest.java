package com.gym.edition1.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberRequest {
  private Long gymId;
  private String name;
  private String phoneNumber;
  private String gender;
  private Integer age;
  private String isDeleted;
  private Long modifiedBy;
}
