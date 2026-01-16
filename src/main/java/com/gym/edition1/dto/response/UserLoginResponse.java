package com.gym.edition1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
  private Long userId;
  private Long gymId;
  private String username;
  private String type; // admin, staff, member
  private String token;
}
