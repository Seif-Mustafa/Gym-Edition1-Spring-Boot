package com.gym.edition1.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "subscription_cancellation")
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCancellation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cancellationId;

  @Column(name = "mem_sub_id")
  private Long memSubId;

  @Column(name="member_id")
  private Long memberId;

  @Column(name = "gym_id")
  private Long gymId;

  @Column(name = "returned_amount")
  private Double returnedAmount;

  @CreatedBy
  @Column(name = "created_by", nullable = false, updatable = false)
  private Long createdBy;

  @CreatedDate
  @Column(name = "created_on", nullable = false, updatable = false)
  private LocalDateTime createdOn;

  @LastModifiedBy
  @Column(name = "modified_by")
  private Long modifiedBy;

  @LastModifiedDate
  @Column(name = "modified_on")
  private LocalDateTime modifiedOn;  
}
