package com.gym.edition1.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSubscriptionDto {
    private Long memSubId;
    private Long memberId;
    private String memberName;
    private java.time.LocalDate dateFrom;
    private java.time.LocalDate dateTo;
    private Integer numOfSessions;
    private Double paidAmount;
    private String receiptNumber;
    
    // Audit fields (read-only, populated by system)
    private Long createdBy;
    private java.time.LocalDateTime createdOn;
    private Long modifiedBy;
    private java.time.LocalDateTime modifiedOn;
}
