package com.gym.edition1.dto.request.subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubscriptionRequest {
  private String subscriptionName;
  private Long gymId;
  private Long numOfDays;
  private Long numOfSessions;
  private Double price;
  private String isActive;
  private Long modifiedBy;
}
