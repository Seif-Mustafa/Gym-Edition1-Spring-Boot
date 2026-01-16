package com.gym.edition1.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest {
  private Long gymId;
  private String name;
  private String phoneNumber;
  private String gender;
  private Integer age;
  private Long createdBy;
}
