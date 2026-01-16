package com.gym.edition1.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
  private String username;
  private String password;
  private String type;
  private Long gymId;
  private Long modifiedBy;
}
