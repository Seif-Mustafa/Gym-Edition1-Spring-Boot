package com.gym.edition1.dto.request.memberSubscription;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberSubscriptionRequest {
  private Long memberId;
  private Long gymId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private Integer numOfSessions;
  private Double paidAmount;
  private String receiptNumber;
  private Long createdBy;
}
