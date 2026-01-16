package com.gym.edition1.dto.request.gym;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGymRequest {
  private String gymName;
  private Long modifiedBy;
}
